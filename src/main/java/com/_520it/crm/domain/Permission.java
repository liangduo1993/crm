package com._520it.crm.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("Permission")
@Setter@Getter@ToString
public class Permission {
    private Long id;

    private String name;

    private String resource;
    
}