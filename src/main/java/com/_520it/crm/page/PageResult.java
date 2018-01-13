package com._520it.crm.page;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PageResult {
	private Long total;
	private List rows;
	public static final PageResult EMPTY = new PageResult(0l,Collections.EMPTY_LIST);
	
	
	public PageResult(Long totalCount, List data) {
		this.total = totalCount;
		this.rows = data;
	}
	
	
	
	
}
