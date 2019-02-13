package com.run.mapper;

import com.run.model.Department;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {

    /**
     * 根据部门编号查询部门信息
     * @param deptId
     * @return
     */
    public Department selectDepartmentByDeptId(@Param("deptId") int deptId);


}
