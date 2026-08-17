package com.tsh.mes.mos.lib.ofs.logic.exec.movement;

import com.tsh.starter.befw.lib.core.lifecycle.RequestLifeCycleVo;

public abstract class MovementLogicLayer {

	protected final RequestLifeCycleVo lifeCycleVo;

	protected MovementLogicLayer(RequestLifeCycleVo lifeCycleVo) {
		this.lifeCycleVo = lifeCycleVo;
	}

	public final void execute() {

		try {
			pre();

			main();

			post();

		} catch (Exception e) {
			error(e);
			throw e;
		}
	}

	protected void pre() {
		commonPre();

		customPre();
	}

	protected abstract void main();

	protected void post() {
		commonPost();

		customPost();
	}

	protected void error(Exception e) {
		// 공통 에러 처리
	}

	private void commonPre() {
		// 공통 처리
	}

	private void commonPost() {
		// 공통 처리
	}

	protected void customPre() {
		// 기본적으로 아무것도 하지 않음
	}

	protected void customPost() {
		// 기본적으로 아무것도 하지 않음
	}
}
