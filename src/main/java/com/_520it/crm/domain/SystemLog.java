package com._520it.crm.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;


@Alias("SystemLog")
@Setter@Getter
public class SystemLog {
    private Long id;

    private Employee user;

    private Date optime;

    private String opip;

    private String function;

    private String params;

}