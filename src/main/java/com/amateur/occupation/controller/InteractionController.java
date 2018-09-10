package com.amateur.occupation.controller;

import com.amateur.occupation.entity.Interaction;
import com.amateur.occupation.util.TResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/interaction")
public class InteractionController {
    @PostMapping
    public TResult add(@RequestBody Interaction interaction) {
        return TResult.success(interaction);
    }

    @DeleteMapping("/{jobId}/{createTime}")
    public TResult delete(@PathVariable("jobId") int jobId, String createTime) {
        return TResult.success("jobId:{},createTime:{}" + jobId + "," + createTime);
    }

    @PutMapping
    public TResult update(@RequestBody Interaction interaction) {
        return TResult.success(interaction);
    }

    @GetMapping("/{interactionId}")
    public TResult get(@PathVariable("interactionId") int interactionId) {
        return TResult.success(new Interaction(1, "2018-08-21 18:08:08",
                "123456@gmail.com", "874855266@qq.com", 1));
    }
}
