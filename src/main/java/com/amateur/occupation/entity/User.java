package com.amateur.occupation.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlAccessorType
@XmlRootElement
@TableName("user")
public class User implements Serializable {
    /**
     * email (primary key)
     */
    @TableId("email")
    private String email;
    @TableField("password")
    private String password;
    /**
     * user_type,to judge admin employer employee
     * 0 is admin,1 is employer,2 is employee
     */
    @TableField("user_type")
    private int userType;

    /**
     * 1 is forbidden,0 is normal.
     */
    @TableField("is_forbidden")
    private int isForbidden;

    /**
     * the account was reported times,default is 0
     */
    private int reportedNum;

    public User(String email, String password, int userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.isForbidden = 0;
        this.reportedNum = 0;
    }

    public User(String email, String password, int userType, int isForbidden, int reportedNum) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.isForbidden = isForbidden;
        this.reportedNum = reportedNum;
    }

    public User() {
    }
}

