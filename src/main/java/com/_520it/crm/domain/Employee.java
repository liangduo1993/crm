package com._520it.crm.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
@Alias("Employee")
public class Employee {
    private Long id;

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;

    private Department dept;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT-8")
    private Date inputtime;

    private Boolean state = true;

    private Boolean admin = false;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", realname=" + realname + ", password=" + password
				+ ", tel=" + tel + ", email=" + email + ", inputtime=" + inputtime + ", state=" + state + ", admin="
				+ admin + "]";
	}

   
}