package com.amateur.occupation.service.impl;

import com.amateur.occupation.entity.Notification;
import com.amateur.occupation.mapper.NotificationMapper;
import com.amateur.occupation.service.NotificationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {
}
