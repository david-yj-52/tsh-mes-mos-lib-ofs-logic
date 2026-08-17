package com.tsh.mes.mos.lib.ofs.logic.provider;

import org.springframework.stereotype.Component;

import com.tsh.mes.mos.lib.data.combination.CarrLotStatResult;
import com.tsh.mes.mos.lib.data.orm.carrier.carrLotRel.MnCarrLotRel;
import com.tsh.mes.mos.lib.data.orm.carrier.carrLotRel.MnCarrLotRelReader;
import com.tsh.mes.mos.lib.data.orm.carrier.carrLotRel.MnCarrLotRelWriter;
import com.tsh.mes.mos.lib.data.orm.carrier.carrStat.MnCarrStatReader;
import com.tsh.mes.mos.lib.data.orm.carrier.carrStat.MnCarrStatWriter;
import com.tsh.mes.mos.lib.data.orm.lot.MnLotStatReader;
import com.tsh.mes.mos.lib.data.orm.lot.MnLotStatWriter;
import com.tsh.starter.befw.lib.core.lifecycle.RequestLifeCycleVo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@Getter
public class WipDataProvider {

	private final MnCarrLotRelReader mnCarrLotRelReader;
	private final MnCarrLotRelWriter mnCarrLotRelWriter;

	private final MnCarrStatReader mnCarrStatReader;
	private final MnCarrStatWriter mnCarrStatWriter;

	private final MnLotStatReader mnLotStatReader;
	private final MnLotStatWriter mnLotStatWriter;

	public boolean isEmptyCarr(RequestLifeCycleVo vo, String carrId) {
		return this.mnCarrLotRelReader.getCarrLotRelByCarr(vo, carrId) == null;
	}

	private CarrLotStatResult getCarrLotData(RequestLifeCycleVo vo, MnCarrLotRel rel) {
		return new CarrLotStatResult(
			this.mnCarrStatReader.getCarrStat(vo, rel.getCarrId()),
			this.mnLotStatReader.getLotStat(vo, rel.getLotId())
		);

	}

}
