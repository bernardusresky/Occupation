package com.amateur.occupation.service.impl;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    @Override
    public List<Employer> list() {
        List<Employer> employerList = new ArrayList<>();
        List<Map<Integer, Object>> list = employerMapper.list();
        if (list != null && list.size() > 0) {
            for (Map<Integer, Object> map : list) {
                if (map != null) {
                    String email = (String) map.get("email");
                    String name = (String) map.get("name");
                    String phone = (String) map.get("phone");
                    String address = (String) map.get("address");
                    String description = (String) map.get("description");
                    String domain = (String) map.get("domain");
                    String scale = (String) map.get("scale");
                    String password = (String) map.get("password");
                    int userType = (int) map.get("user_type");
                    int isForbidden = (int) map.get("is_forbidden");
                    int reportedNum = (int) map.get("reported_num");
                    Employer employer = new Employer(email, name, phone, address, description, domain, scale,
                            password, userType, isForbidden, reportedNum);
                    employerList.add(employer);
                }
            }
        }
        return employerList;
    }

    @Override
    public Employer getEmployer(String email) {
        List<Map<Integer, Object>> list = employerMapper.getEmployer(email);
        Employer employer = null;
        if (list != null && list.size() > 0) {
            for (Map<Integer, Object> map : list) {
                if (map != null) {
                    String email1 = (String) map.get("email");
                    String name = (String) map.get("name");
                    String phone = (String) map.get("phone");
                    String address = (String) map.get("address");
                    String description = (String) map.get("description");
                    String domain = (String) map.get("domain");
                    String scale = (String) map.get("scale");
                    String password = (String) map.get("password");
                    int userType = (int) map.get("user_type");
                    int isForbidden = (int) map.get("is_forbidden");
                    int reportedNum = (int) map.get("reported_num");
                    employer = new Employer(email1, name, phone, address, description, domain, scale,
                            password, userType, isForbidden, reportedNum);

                }
            }
        }
        return employer;
    }
}
