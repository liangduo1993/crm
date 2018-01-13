package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class QueryObject {
	private  Integer page = 1;
	private Integer rows = 10;
	
	public Integer getStart(){
		return (page - 1) * rows;
	}
	
	
}
