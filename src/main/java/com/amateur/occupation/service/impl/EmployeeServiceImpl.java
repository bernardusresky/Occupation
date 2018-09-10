package com.amateur.occupation.service.impl;

import com.amateur.occupation.entity.Employee;
import com.amateur.occupation.entity.User;
import com.amateur.occupation.mapper.EmployeeMapper;
import com.amateur.occupation.mapper.UserMapper;
import com.amateur.occupation.service.EmployeeService;
import com.amateur.occupation.util.TResult;
import com.amateur.occupation.util.TResultCode;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final UserMapper userMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper, UserMapper userMapper) {
        this.employeeMapper = employeeMapper;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public TResult add(Employee employee) {
        int result1 = userMapper.insert(new User(employee.getEmail(), employee.getPassword(), employee.getUserType()));
        log.debug("user insert result:" + result1);
        if (result1 > 0) {
            int result2 = employeeMapper.insert(employee);
            log.debug("employee insert result:" + result1);
            if (result2 > 0) {
                return TResult.success(employee.getEmail(), employee);
            }
        }
        return TResult.failure(TResultCode.BUSINESS_ERROR);

    }

    @Override
    public TResult get(String email) {
        if (email == null || email.isEmpty()) {
            return TResult.failure(TResultCode.PARAM_IS_INVALID);
        }
        Employee employee = employeeMapper.selectById(email);
        if (employee == null) {
            return TResult.failure(TResultCode.USER_NOT_EXIST);
        } else {
            return TResult.success(employee);
        }
    }
}
