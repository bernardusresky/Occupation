package com.amateur.occupation.service;

import com.amateur.occupation.entity.User;
import com.amateur.occupation.util.TResult;
import com.baomidou.mybatisplus.service.IService;


public interface UserService extends IService<User> {
    /**
     * user login
     *
     * @param email
     * @param password
     * @return
     */
    TResult login(String email, String password);

    TResult add(User user);

    TResult updatePassword(String email, String password);
}
