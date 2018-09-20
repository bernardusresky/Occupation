package com.amateur.occupation.mapper;

import com.amateur.occupation.entity.Job;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
}