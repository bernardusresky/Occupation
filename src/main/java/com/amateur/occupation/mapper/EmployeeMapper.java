package com.amateur.occupation.mapper;

import com.amateur.occupation.entity.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select(" select e.email,e.`name`,e.phone,e.birthday,e.gender,e.edu_background,e.work_experience,e.expect_salary," +
            "e.expect_city,e.address,e.description,u.`password`,u.user_type,u.is_forbidden,u.reported_num " +
            " from employee as e,`user` as u " +
            " where e.email=u.email ;")
    List<Map<Integer, Object>> list();
}
