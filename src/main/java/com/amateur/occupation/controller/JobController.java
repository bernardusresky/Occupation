package com.amateur.occupation.controller;

import com.amateur.occupation.constant.Const;
import com.amateur.occupation.entity.Job;
import com.amateur.occupation.service.JobService;
import com.amateur.occupation.util.TResult;
import com.amateur.occupation.util.TResultCode;
import com.amateur.occupation.util.TimeUtil;
import com.amateur.occupation.vo.JobVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequestMapping("/job")
@RestController
@Slf4j
public class JobController {

    private final JobService jobService;
    private final HttpSession session;

    @Autowired
    public JobController(JobService jobService, HttpSession session) {
        this.jobService = jobService;
        this.session = session;
    }

    
    @PostMapping
    public TResult add(@RequestBody Job job) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) == 1) {
            job.setCreateTime(TimeUtil.getCurrentTime());
            boolean result = jobService.insert(job);
            if (result) {
                return TResult.success("insert Job success,jobId:" + job.getJobId());
            } else {
                return TResult.failure(TResultCode.BUSINESS_ERROR);

            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }
    }

    @DeleteMapping("/{jobId}")
    public TResult delete(@PathVariable("jobId") int jobId) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) == 1) {
            TResult getResult = get(jobId);
            if (!getResult.getCode().equals(TResultCode.SUCCESS.getCode())) {
                return TResult.failure(TResultCode.RESULE_DATA_NONE);
            }
            Job job = ((Job) getResult.getData());
            if (!job.getCreateEmployerEmail().equals(session.getAttribute(Const.ID_KEY))) {
                return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
            }
            boolean result = jobService.deleteById(jobId);
            if (result) {
                return TResult.success("delete Job success,jobId:" + jobId);
            } else {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }

    }

    @PutMapping
    public TResult update(@RequestBody Job job) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) == 1
                && job.getCreateEmployerEmail().equals(session.getAttribute(Const.ID_KEY))) {
            EntityWrapper<Job> ew = new EntityWrapper<>();
            boolean result = jobService.update(job, ew.eq("job_id", job.getJobId()));
            if (result) {
                return TResult.success(job);
            } else {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }
    }

    @GetMapping("/{jobId}")
    public TResult get(@PathVariable("jobId") int jobId) {
        Job Job = jobService.selectById(jobId);
        if (Job == null) {
            return TResult.failure(TResultCode.USER_NOT_EXIST);
        } else {
            return TResult.success(Job);
        }
    }

    @GetMapping("/list")
    public TResult getAll() {
        List<JobVO> jobVOList = jobService.getAllJob();
        if (jobVOList == null || jobVOList.size() == 0) {
            return TResult.failure(TResultCode.RESULE_DATA_NONE);
        } else {
            return TResult.success(jobVOList);
        }
    }

    @GetMapping("/search/{content}")
    public TResult search(@PathVariable("content") String content) {
        log.debug("job search:" + content);
        List<JobVO> jobVOList = jobService.searchJob(content);
        if (jobVOList == null || jobVOList.size() == 0) {
            return TResult.failure(TResultCode.RESULE_DATA_NONE);
        } else {
            return TResult.success(jobVOList);
        }
    }
}
