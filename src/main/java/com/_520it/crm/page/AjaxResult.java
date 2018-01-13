package com._520it.crm.page;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class AjaxResult {
	private boolean success = false;
	private String msg;
	
	public AjaxResult(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public AjaxResult(String msg) {
		this.msg = msg;
	}
	
	
	
}
