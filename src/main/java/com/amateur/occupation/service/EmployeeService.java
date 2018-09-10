package com.amateur.occupation.service;

import com.amateur.occupation.entity.Employee;
import com.amateur.occupation.util.TResult;
import com.baomidou.mybatisplus.service.IService;


public interface EmployeeService extends IService<Employee> {
    TResult add(Employee employee);

    TResult get(String email);
}
