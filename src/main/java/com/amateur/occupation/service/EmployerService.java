package com.amateur.occupation.service;

import com.amateur.occupation.entity.Employer;
import com.amateur.occupation.util.TResult;
import com.baomidou.mybatisplus.service.IService;


public interface EmployerService extends IService<Employer> {
    TResult add(Employer employer);
    TResult get(String email);
}
