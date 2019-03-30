package com.yxr.service.Impl;

import com.winter.mapper.EmployeeMapper;
import com.winter.model.Employee;
import com.yxr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Transactional
    @Override
    public int addEmployee(Employee record) {
        int delResult = employeeMapper.deleteByPrimaryKey(11);
        int addResult = employeeMapper.insert(record);
        if (delResult == 0 || addResult == 0) {
            //手动回滚，上层无需要处理异常。
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return addResult;
    }


}
