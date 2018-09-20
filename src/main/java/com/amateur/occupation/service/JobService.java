package com.amateur.occupation.controller;

import com.amateur.occupation.entity.Job;
import com.amateur.occupation.util.TResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/job")
@RestController
@Slf4j
public class JobController {
    @PostMapping
    public TResult add(@RequestBody Job job) {
        return TResult.success(job);
    }

    @DeleteMapping("/{jobId}")
    public TResult delete(@PathVariable("jobId") int jobId) {
        return TResult.success("jobId:{}", jobId);
    }

    @PutMapping
    public TResult update(@RequestBody Job job) {
        return TResult.success(job);
    }

    @GetMapping("/{jobId}")
    public TResult get(@PathVariable("jobId") int jobId) {
        return TResult.success(new Job(1, 10, "10k/month", "1 year",
                "master", "develop some software", "5 day a week",
                "2018-08-21 10:00:00", 1));
    }
}
