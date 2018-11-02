package com.amateur.occupation.mapper;

import com.amateur.occupation.entity.Employer;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface EmployerMapper extends BaseMapper<Employer> {

    @Select(" select r.email,r.name,r.phone,r.address,r.description,r.domain,r.scale," +
            "u.`password`,u.user_type,u.is_forbidden,u.reported_num " +
            " from employer as r,`user` as u " +
            " where r.email=u.email ;")
    List<Map<Integer, Object>> list();

    @Select(" select r.email,r.name,r.phone,r.address,r.description,r.domain,r.scale," +
            "u.`password`,u.user_type,u.is_forbidden,u.reported_num " +
            " from employer as r,`user` as u " +
            " where r.email=u.email and r.email=#{email} ;")
    List<Map<Integer, Object>> getEmployer(@Param("email") String email);
}
