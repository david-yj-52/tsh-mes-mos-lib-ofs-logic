package com.tsh.mes.mos.lib.ofs.logic.provider;

import org.springframework.stereotype.Component;

import com.tsh.mes.mos.lib.data.orm.movement.transferJob.MnTransferJobReader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@Getter
public class MovementDataProvider {

	private final WipDataProvider wipDataProvider;
	private final MnTransferJobReader mnTransferJobReader;

}
