package com.amateur.occupation.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Data
@XmlAccessorType
@XmlRootElement
@TableName("notification")
public class Notification implements Serializable {
    /**
     * notification id
     */
    @TableId(value = "note_id", type = IdType.AUTO)
    private int noteId;
    /**
     * notification content
     */
    private String content;

    /**
     * accept email,ALL is for all email
     */
    private String targetEmail;
    /**
     * create notification's user email
     */
    private String crtEmail;
    /**
     * timestamp,like yyyy-MM-dd HH:MM:SS
     */
    private String createTime;

    public Notification() {
    }

    public Notification(int noteId, String content, String targetEmail, String crtEmail) {
        this.noteId = noteId;
        this.content = content;
        this.targetEmail = targetEmail;
        this.crtEmail = crtEmail;
    }

    public Notification(int noteId, String content, String targetEmail, String crtEmail, String createTime) {
        this.noteId = noteId;
        this.content = content;
        this.targetEmail = targetEmail;
        this.crtEmail = crtEmail;
        this.createTime = createTime;
    }
}
