package com.amateur.occupation.controller;

import com.amateur.occupation.constant.Const;
import com.amateur.occupation.entity.Notification;
import com.amateur.occupation.service.NotificationService;
import com.amateur.occupation.util.TResult;
import com.amateur.occupation.util.TResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public TResult add(Notification notification) {
        if (((int) session.getAttribute(Const.USER_TYPE_KEY)) == 0) {
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
            return TResult.failure(TResultCode.USER_NOT_EXIST);
        } else {
            return TResult.success(notification);
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
