package com.amateur.occupation.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("user")
public class User  implements Serializable{
    /**
     * email (primary key)
     */
    @TableId("email")
    private String email;
    @TableField("password")
    private String password;
    /**
     * user_type,to judge admin employer employee
     */
    @TableField("user_type")
    private int userType;
}
