package com.amateur.occupation.controller;

import com.amateur.occupation.constant.Const;
import com.amateur.occupation.entity.Notification;
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
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;
    private final HttpSession session;

    @Autowired
    public NotificationController(NotificationService notificationService, HttpSession session) {
        this.notificationService = notificationService;
        this.session = session;
    }

    @PostMapping
    public TResult add(@RequestBody Notification notification) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) == 0) {
            notification.setCreateTime(TimeUtil.getCurrentTime());
            System.out.println(notification.getContent());
            System.out.println(notification.getCreateTime());
            System.out.println(notification.getCrtEmail());
            System.out.println(notification.getNoteId());
            System.out.println(notification.getTargetEmail());
            boolean result = notificationService.insert(notification);
            if (result) {
                return TResult.success("insert notification success,noteId:" + notification.getNoteId());
            } else {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }
    }

    @GetMapping("/{noteId}")
    public TResult get(@PathVariable("noteId") int noteId) {
        Notification notification = notificationService.selectById(noteId);
        if (notification == null) {
            return TResult.failure(TResultCode.RESULE_DATA_NONE);
        } else {
            return TResult.success(notification);
        }
    }


    /**
     * to be tested
     *
     * @return
     */
    @GetMapping("/list")
    public TResult list() {
        String email = (String) session.getAttribute(Const.ID_KEY);
        EntityWrapper<Notification> ew = new EntityWrapper<>();
        List<Notification> notificationList = notificationService.selectList(
                ew.eq("target_email", email).or().eq("crt_email", email));
        if (notificationList == null) {
            return TResult.failure(TResultCode.RESULE_DATA_NONE);
        } else {
            return TResult.success(notificationList);
        }
    }


    @DeleteMapping
    public TResult delete(@RequestParam("noteId") int noteId) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) == 0) {
            boolean result = notificationService.deleteById(noteId);
            if (result) {
                return TResult.success("delete notification success,noteId:" + noteId);
            } else {
                return TResult.failure(TResultCode.BUSINESS_ERROR);
            }
        } else {
            return TResult.failure(TResultCode.PERMISSION_NO_ACCESS);
        }
    }
}
