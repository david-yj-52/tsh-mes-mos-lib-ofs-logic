package com.tsh.mes.mos.lib.ofs.logic.exec.movement.impl;

import com.tsh.mes.mos.lib.ofs.logic.exec.movement.MovementLogicLayer;
import com.tsh.mes.mos.lib.ofs.logic.provider.MovementDataProvider;
import com.tsh.starter.befw.lib.core.lifecycle.RequestLifeCycleVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarrMoveComp extends MovementLogicLayer {

	private final String carrId;
	private final String jobId;
	private final CarrMoveCompIvo ivo;
	private final MovementDataProvider movementDataProvider;

	public CarrMoveComp(RequestLifeCycleVo lifeCycleVo, CarrMoveCompIvo ivo,
		MovementDataProvider movementDataProvider) {
		super(lifeCycleVo);
		this.ivo = ivo;
		this.carrId = ivo.getBody().getCarrId();
		this.jobId = ivo.getBody().getJobId();
		this.movementDataProvider = movementDataProvider;

		// data CRUD 필요
	}

	@Override
	protected void customPre() {

		log.info("check job exist");

		boolean isJobExist = false;
		if (!isJobExist) {
			//            throw new Exception();
		}

	}

	@Override
	protected void main() {

	}
}
