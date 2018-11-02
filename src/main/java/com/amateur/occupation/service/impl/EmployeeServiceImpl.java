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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    @Override
    public List<Employee> list() {
        List<Employee> employeeList = new ArrayList<>();
        List<Map<Integer, Object>> list = employeeMapper.list();
        if (list != null && list.size() > 0) {
            for (Map<Integer, Object> map : list) {
                if (map != null) {
                    Employee employee = new Employee();
                    String email = (String) map.get("email");
                    employee.setEmail(email);
                    String name = (String) map.get("name");
                    employee.setName(name);
                    String phone = (String) map.get("phone");
                    employee.setPhone(phone);
                    String birthday = (String) map.get("birthday");
                    employee.setBirthday(birthday);
                    int gender = (int) map.get("gender");
                    employee.setGender(gender);
                    String eduBackground = (String) map.get("edu_background");
                    employee.setEduBackground(eduBackground);
                    String workExperience = (String) map.get("work_experience");
                    employee.setWorkExperience(workExperience);
                    String expectSalary = (String) map.get("expect_salary");
                    employee.setExpectSalary(expectSalary);
                    String expectCity = (String) map.get("expect_city");
                    employee.setExpectCity(expectCity);
                    String address = (String) map.get("address");
                    employee.setAddress(address);
                    String description = (String) map.get("description");
                    employee.setDescription(description);
                    String password = (String) map.get("password");
                    employee.setPassword(password);
                    int userType = (int) map.get("user_type");
                    employee.setUserType(userType);
                    int isForbidden = (int) map.get("is_forbidden");
                    employee.setIsForbidden(isForbidden);
                    int reportedNum = (int) map.get("reported_num");
                    employee.setReportedNum(reportedNum);

                    employeeList.add(employee);
                }
            }
        }
        return employeeList;
    }

    @Override
    public Employee getEmployee(String email) {
        Employee employee = new Employee();
        List<Map<Integer, Object>> list = employeeMapper.getEmployee(email);
        if (list != null && list.size() > 0) {
            for (Map<Integer, Object> map : list) {
                if (map != null) {
                    String email1 = (String) map.get("email");
                    employee.setEmail(email1);
                    String name = (String) map.get("name");
                    employee.setName(name);
                    String phone = (String) map.get("phone");
                    employee.setPhone(phone);
                    String birthday = (String) map.get("birthday");
                    employee.setBirthday(birthday);
                    int gender = (int) map.get("gender");
                    employee.setGender(gender);
                    String eduBackground = (String) map.get("edu_background");
                    employee.setEduBackground(eduBackground);
                    String workExperience = (String) map.get("work_experience");
                    employee.setWorkExperience(workExperience);
                    String expectSalary = (String) map.get("expect_salary");
                    employee.setExpectSalary(expectSalary);
                    String expectCity = (String) map.get("expect_city");
                    employee.setExpectCity(expectCity);
                    String address = (String) map.get("address");
                    employee.setAddress(address);
                    String description = (String) map.get("description");
                    employee.setDescription(description);
                    String password = (String) map.get("password");
                    employee.setPassword(password);
                    int userType = (int) map.get("user_type");
                    employee.setUserType(userType);
                    int isForbidden = (int) map.get("is_forbidden");
                    employee.setIsForbidden(isForbidden);
                    int reportedNum = (int) map.get("reported_num");
                    employee.setReportedNum(reportedNum);
                }
            }
        }
        return employee;
    }
}
