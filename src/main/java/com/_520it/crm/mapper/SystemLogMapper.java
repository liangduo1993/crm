package com._520it.crm.mapper;

import com._520it.crm.domain.SystemLog;
import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog record);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog record);
}