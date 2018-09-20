package com.amateur.occupation.service.impl;

import com.amateur.occupation.entity.Employer;
import com.amateur.occupation.entity.Job;
import com.amateur.occupation.mapper.JobMapper;
import com.amateur.occupation.service.JobService;
import com.amateur.occupation.vo.JobVO;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {
    private JobMapper jobMapper;

    public JobServiceImpl(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Override
    public List<JobVO> getAllJob() {
        List<JobVO> jobVOList = new ArrayList<>();
        List<Map<Integer, Object>> list = jobMapper.selectAllJob();
        if (list != null && list.size() > 0) {
            for (Map<Integer, Object> map : list) {
                if (map != null) {
                    JobVO jobVO = new JobVO();
                    jobVO.setJobId((int) map.get("job_id"));
                    jobVO.setNumber((int) map.get("number"));
                    jobVO.setSalary((String) map.get("salary"));
                    jobVO.setExpectWorkExperience((String) map.get("expect_work_experience"));
                    jobVO.setExpectEduBackground((String) map.get("expect_edu_background"));
                    jobVO.setDescription((String) map.get("description"));
                    jobVO.setJobRequirement((String) map.get("job_requirement"));
                    jobVO.setCreateTime((String) map.get("create_time"));
                    String email = (String) map.get("email");
                    String name = (String) map.get("name");
                    String phone = (String) map.get("phone");
                    String address = (String) map.get("address");
                    String description = (String) map.get("rdescription");
                    String domain = (String) map.get("domain");
                    String scale = (String) map.get("scale");
                    Employer employer = new Employer(email, name, phone, address, description, domain, scale);
                    jobVO.setEmployer(employer);
                    jobVOList.add(jobVO);
                }
            }
        }
        return jobVOList;
    }
}