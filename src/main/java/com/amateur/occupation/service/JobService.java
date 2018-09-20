package com.amateur.occupation.service;

import com.amateur.occupation.entity.Job;
import com.amateur.occupation.vo.JobVO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;


public interface JobService extends IService<Job> {
    List<JobVO> getAllJob();
}
