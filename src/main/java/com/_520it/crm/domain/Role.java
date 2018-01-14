package com._520it.crm.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("Role")
@Setter@Getter@ToString
public class Role {
    private Long id;

    private String sn;

    private String name;
    
    private List<Permission> permissions = new ArrayList<>();

}