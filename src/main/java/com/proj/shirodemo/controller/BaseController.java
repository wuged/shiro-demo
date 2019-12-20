package com.proj.shirodemo.controller;

import com.proj.shirodemo.entity.ResponseEntity;

/**
 * 全局基础controller
 *
 * @author wuge
 * @date 2019/12/20
 */
public class BaseController {

	/**
	 * 默认返回信息
	 */
	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

	/**
	 * 成功返回
	 * @return
	 */
	public ResponseEntity successResult() {
		return new ResponseEntity().setCode(200).setMessage(DEFAULT_SUCCESS_MESSAGE);
	}

	/**
	 * 成功返回
	 * @param data
	 * @param <T>
	 * @return
	 */
	public <T> ResponseEntity<T> successResult(T data) {
		return new ResponseEntity().setCode(200).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
	}

}
