package com.tsh.mes.mos.lib.ofs.logic.exec.movement.impl;

import java.util.List;

import com.tsh.mes.lib.spec.ofs.mtc.CarrMoveCompIvo;
import com.tsh.mes.mos.lib.data.orm.movement.transferJob.MnTransferJob;
import com.tsh.mes.mos.lib.data.orm.movement.transferJob.MnTransferJobReader;
import com.tsh.mes.mos.lib.ofs.core.lifecycle.OfsLifeCycleVo;
import com.tsh.mes.mos.lib.ofs.logic.exec.movement.MovementLogicLayer;
import com.tsh.mes.mos.lib.ofs.logic.provider.MovementDataProvider;
import com.tsh.mes.mos.lib.ofs.logic.provider.WipDataProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarrMoveComp extends MovementLogicLayer {

	private final String carrId;
	private final String jobId;
	private final CarrMoveCompIvo ivo;
	private final CarrMoveCompIvo.Body body;
	private final MovementDataProvider movementDataProvider;
	private final WipDataProvider wipDataProvider;

	private final MnTransferJobReader mnTransferJobReader;

	private boolean isEmptyCarr;
	private MnTransferJob mnTransferJob;

	public CarrMoveComp(OfsLifeCycleVo lifeCycleVo, CarrMoveCompIvo ivo,
		MovementDataProvider movementDataProvider) {
		super(lifeCycleVo);
		this.ivo = ivo;
		this.body = ivo.getBody();
		this.carrId = ivo.getBody().getCarrId();
		this.jobId = ivo.getBody().getJobId();
		this.movementDataProvider = movementDataProvider;
		this.mnTransferJobReader = movementDataProvider.getMnTransferJobReader();

		this.wipDataProvider = movementDataProvider.getWipDataProvider();
	}

	@Override
	protected void customPre() {

		log.info("check job exist");

		this.setTransferJob();

		log.info("check is carr empty");
		this.isEmptyCarr = this.wipDataProvider.isEmptyCarr(lifeCycleVo, carrId);
	}

	private void setTransferJob() {
		List<MnTransferJob> jobList = this.mnTransferJobReader.fetchJob(lifeCycleVo, jobId, carrId);
		if (jobList.size() > 1) {
			this.mnTransferJob = this.mnTransferJobReader.fetchJobMatchedWithDestInfo(lifeCycleVo, jobList,
				body.getEqpId(), body.getPortId());
		} else {
			this.mnTransferJob = jobList.getFirst();

		}
	}

	@Override
	protected void main() {
		this.updateWipStatCode();
		this.clearTransferJob();

	}

	private void clearTransferJob() {
		this.mnTransferJob.setJobStatCd("Comp");
		this.mnTransferJobReader.getRepo().save(this.mnTransferJob);
		this.mnTransferJobReader.getRepo().delete(this.mnTransferJob);

	}

	private void updateWipStatCode() {
		log.info("carr:{} isEmpty: {}.", carrId, isEmptyCarr);
		this.movementDataProvider.getWipDataProvider().getMnCarrLotRelWriter().updateWipStatCodeByCarr(
			lifeCycleVo, carrId, "Ready"
		);
	}
}
