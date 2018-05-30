package com.gaofen.mapper;

import com.gaofen.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(String loginname);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String loginname);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}