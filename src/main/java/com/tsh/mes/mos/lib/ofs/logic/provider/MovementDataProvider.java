package com.tsh.mes.mos.lib.ofs.logic.provider;

import org.springframework.stereotype.Component;

import com.tsh.mes.mos.lib.data.movement.transferJob.MnTransferJobRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MovementDataProvider {

	private final MnTransferJobRepo mnTransferJobRepo;

	public MovementDataProvider(MnTransferJobRepo mnTransferJobRepo) {

		this.mnTransferJobRepo = mnTransferJobRepo;
	}

}
