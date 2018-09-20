package com.amateur.occupation.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
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
     * create notification's user email
     */
    private String email;
    /**
     * notification creat time (milliseconds from 1970.01.01)
     */
    private long createTime;

}
