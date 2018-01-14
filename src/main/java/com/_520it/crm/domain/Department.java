package com._520it.crm.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("Department")
@Setter@Getter
public class Department {
    private Long id;

    private String sn;

    private String name;

    private Employee manager;

    private Department parentDept;

    private Boolean state;
    
}