package com.amateur.occupation.service.impl;

import com.amateur.occupation.entity.Employee;
import com.amateur.occupation.entity.Employer;
import com.amateur.occupation.entity.User;
import com.amateur.occupation.mapper.EmployerMapper;
import com.amateur.occupation.mapper.UserMapper;
import com.amateur.occupation.service.EmployerService;
import com.amateur.occupation.util.TResult;
import com.amateur.occupation.util.TResultCode;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class EmployerServiceImpl extends ServiceImpl<EmployerMapper, Employer> implements EmployerService {
    private final UserMapper userMapper;
    private final EmployerMapper employerMapper;

    @Autowired
    public EmployerServiceImpl(UserMapper userMapper, EmployerMapper employerMapper) {
        this.userMapper = userMapper;
        this.employerMapper = employerMapper;
    }

    @Override
    @Transactional
    public TResult add(Employer employer) {
        int result1 = userMapper.insert(new User(employer.getEmail(), employer.getPassword(), employer.getUserType()));
        log.debug("user insert result:" + result1);
        if (result1 > 0) {
            int result2 = employerMapper.insert(employer);
            log.debug("employer insert result:" + result1);
            if (result2 > 0) {
                return TResult.success(employer.getEmail(), employer);
            }
        }
        return TResult.failure(TResultCode.BUSINESS_ERROR);
    }

    @Override
    public TResult get(String email) {
        if (email == null || email.isEmpty()) {
            return TResult.failure(TResultCode.PARAM_IS_INVALID);
        }
        Employer employer = employerMapper.selectById(email);
        if (employer == null) {
            log.error("employer is null,email:" + email);
            return TResult.failure(TResultCode.USER_NOT_EXIST);
        } else {
            return TResult.success(employer);
        }
    }
}
