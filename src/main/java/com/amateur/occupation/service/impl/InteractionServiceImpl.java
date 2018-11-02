package com.amateur.occupation.service.impl;

import com.amateur.occupation.entity.Interaction;
import com.amateur.occupation.mapper.InteractionMapper;
import com.amateur.occupation.service.InteractionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class InteractionServiceImpl extends ServiceImpl<InteractionMapper, Interaction> implements InteractionService {
}