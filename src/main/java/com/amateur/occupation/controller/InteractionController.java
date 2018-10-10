package com.amateur.occupation.controller;

import com.amateur.occupation.constant.Const;
import com.amateur.occupation.entity.Interaction;
import com.amateur.occupation.entity.Notification;
import com.amateur.occupation.service.InteractionService;
import com.amateur.occupation.service.NotificationService;
import com.amateur.occupation.util.TResult;
import com.amateur.occupation.util.TResultCode;
import com.amateur.occupation.util.TimeUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/interaction")
public class InteractionController {
    private final InteractionService interactionService;
    private final NotificationService notificationService;
    private final HttpSession session;

    @Autowired
    public InteractionController(InteractionService interactionService, NotificationService notificationService,
                                 HttpSession session) {
        this.interactionService = interactionService;
        this.notificationService = notificationService;
        this.session = session;
    }

    //to be tested
    @PostMapping
    public TResult add(@RequestBody Interaction interaction) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) > 0) {
            interaction.setCreateTime(TimeUtil.getCurrentTime());
            boolean result = interactionService.insert(interaction);
            String email = (String) session.getAttribute(Const.ID_KEY);
            if (result) {
                boolean res = false;
                if ((int) session.getAttribute(Const.USER_TYPE_KEY) == 1) {
                    res = notificationService.insert(new Notification(0,
                            "a new offer from " + email, interaction.getEmployeeEmail(), email,
                            interaction.getCreateTime()));
                } else if ((int) session.getAttribute(Const.USER_TYPE_KEY) == 2) {
                    res = notificationService.insert(new Notification(0,
                            "a new application from " + email, interaction.getEmployerEmail(), email,
                            interaction.getCreateTime()));
                }
                return TResult.success("insert Interaction success,interactionId:" + interaction.getInterId()
                        + ",send notification:" + res);
            } else {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }
    }

    @DeleteMapping("/{interId}")
    public TResult delete(@PathVariable("interId") int interId) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) > 0) {
            TResult getResult = get(interId);
            if (!getResult.getCode().equals(TResultCode.SUCCESS.getCode())) {
                return TResult.failure(TResultCode.RESULE_DATA_NONE);
            }
            Interaction interaction = ((Interaction) getResult.getData());
//            权限判断，仅能删除自身的创建的ineraction
            if (interaction.getCheckStatus() > 0) {
                if (!interaction.getEmployerEmail().equals(session.getAttribute(Const.ID_KEY))) {
                    return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
                }
            } else {
                if (!interaction.getEmployeeEmail().equals(session.getAttribute(Const.ID_KEY))) {
                    return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
                }
            }
            boolean result = interactionService.deleteById(interId);
            if (result) {
                return TResult.success("delete Interaction success,interactionId:" + interId);
            } else {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }
    }

    @PutMapping
    public TResult update(@RequestBody Interaction interaction) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) > 0
                && (interaction.getEmployeeEmail().equals(session.getAttribute(Const.ID_KEY))
                || interaction.getEmployerEmail().equals(session.getAttribute(Const.ID_KEY)))
                ) {
            EntityWrapper<Interaction> ew = new EntityWrapper<>();
            boolean result = interactionService.update(interaction, ew.eq("inter_id", interaction.getInterId()));
            if (result) {
                return TResult.success(interaction);
            } else {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }
    }


    @GetMapping("/{interId}")
    public TResult get(@PathVariable("interId") int interactionId) {
        Interaction interaction = interactionService.selectById(interactionId);
        if (interaction == null) {
            return TResult.failure(TResultCode.USER_NOT_EXIST);
        } else {
            return TResult.success(interaction);
        }
    }

    @PutMapping("/accept/{interId}/{isAccept}")
    public TResult accept(@PathVariable("interId") int interId, @PathVariable("isAccept") int isAccept) {
        if (!(((int) session.getAttribute(Const.USER_TYPE_KEY)) > 0)) {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }
        Interaction interaction = interactionService.selectById(interId);
        if (interaction.getCheckStatus() > 0 && interaction.getEmployeeEmail().equals(session.getAttribute(Const.ID_KEY))) {
            if (interaction.getCheckStatus() == 1) {
                if (isAccept == 1) {
                    interaction.setCheckStatus(3);
                } else {
                    interaction.setCheckStatus(2);
                }
            }
            return update(interaction);
        } else if (interaction.getCheckStatus() < 0 && interaction.getEmployerEmail().equals(session.getAttribute(Const.ID_KEY))) {
            if (interaction.getCheckStatus() == -1) {
                if (isAccept == 1) {
                    interaction.setCheckStatus(-3);
                } else {
                    interaction.setCheckStatus(-2);
                }
            }
            return update(interaction);
        }
        return TResult.failure(TResultCode.BUSINESS_ERROR);
    }

    @GetMapping("/list")
    public TResult list() {
        int userType = (int) session.getAttribute(Const.USER_TYPE_KEY);
        String userEmail = (String) session.getAttribute(Const.ID_KEY);
        if (userType == Const.ADMIN_TYPE) {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        } else if (userType == Const.EMPLOYER_TYPE) {
            EntityWrapper<Interaction> ew = new EntityWrapper<>();

            List<Interaction> list = interactionService.selectList(
                    ew.eq("employer_email", userEmail));
            if (list == null) {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            } else {
                return TResult.success(list);
            }
        } else if (userType == Const.EMPLOYEE_TYPE) {
            EntityWrapper<Interaction> ew = new EntityWrapper<>();
            List<Interaction> list = interactionService.selectList(
                    ew.eq("employee_email", userEmail));
            if (list == null) {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            } else {
                return TResult.success(list);
            }
        }
        return TResult.failure(TResultCode.BUSINESS_ERROR);
    }
}


