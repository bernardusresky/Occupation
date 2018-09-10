package com.amateur.occupation.service.impl;

import com.amateur.occupation.entity.User;
import com.amateur.occupation.mapper.UserMapper;
import com.amateur.occupation.service.UserService;
import com.amateur.occupation.util.TResult;
import com.amateur.occupation.util.TResultCode;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public TResult login(String email, String password) {
        log.debug("email,password:" + email + "," + password);
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return TResult.failure(TResultCode.PARAM_IS_INVALID);
        } else {
//            User u = new User();
//            u.setEmail(email);
//            User user = userMapper.selectOne(u);
            User user = userMapper.selectById(email);
            if (user == null) {
                return TResult.failure(TResultCode.USER_NOT_EXIST);
            } else {
                log.info(user.toString());
                TResult result = TResult.success(user.getEmail());
                result.setData(user);
                return result;
            }
        }

    }

    @Override
    public TResult add(User user) {
        int result = userMapper.insert(user);
        log.debug("insert user result:" + result);
        if (result > 0) {
            return TResult.success(user.getEmail(), user);
        } else {
            return TResult.failure(TResultCode.BUSINESS_ERROR);
        }
    }

    @Override
    public TResult updatePassword(String email, String password) {
        EntityWrapper<User> ew = new EntityWrapper<>();
        int result = userMapper.updateForSet(" password='" + password + "'", ew.eq("email", email));
        if (result > 0) {
            return TResult.success(email);
        } else {
            log.error("update password fail.email,password:", email, password);
            return TResult.failure(TResultCode.BUSINESS_ERROR);
        }
    }

}
