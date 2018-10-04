package com.amateur.occupation.mapper;

import com.amateur.occupation.entity.Job;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface JobMapper extends BaseMapper<Job> {

    @Select(" select j.job_id,j.number,j.salary,j.expect_work_experience,j.expect_edu_background,j.description,j.job_requirement," +
            " j.create_time,j.status,r.email,r.name,r.phone,r.address,r.description as rdescription,r.domain,r.scale" +
            " from job as j,employer as r " +
            " where j.create_employer_email=r.email  " +
            " order by j.job_id asc;")
    List<Map<Integer, Object>> selectAllJob();

    @Select("  select j.job_id,j.number,j.salary,j.expect_work_experience,j.expect_edu_background,j.description,j.job_requirement, j.create_time,j.status,r.email,r.name,r.phone,r.address,r.description as rdescription,r.domain,r.scale\n" +
            " from job as j,employer as r  " +
            " where j.create_employer_email=r.email   " +
            " or j.job_id like #{content} or j.number like #{content} " +
            " or j.salary like #{content} or j.expect_work_experience like #{content} " +
            " or j.expect_edu_background like #{content} or j.description like #{content} " +
            " or j.job_requirement like #{content} or j.create_time like #{content}  " +
            " or j.status like #{content} or r.email like #{content} " +
            " or r.name like #{content} or r.phone like #{content}  " +
            " or r.address like #{content} or r.description like #{content}  " +
            " order by j.job_id asc;")
    List<Map<Integer, Object>> searchJob(@Param("content") String content);
}
