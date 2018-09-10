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
@TableName("employer")
public class Employer implements Serializable {
    @TableId("email")
    private String email;
    private String name;
    private String phone;
    private String address;
    /**
     * company description
     */
    private String description;
    /**
     * business domain,like internet retailer
     */
    private String domain;
    /**
     * people scale,like less 50 persons
     */
    private String scale;
    @TableField(exist = false)
    private String password;
    /**
     * user_type,to judge admin employer employee
     */
    @TableField(exist = false)
    private int userType;

    public Employer(String email, String name, String phone, String address, String description, String domain, String scale) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.description = description;
        this.domain = domain;
        this.scale = scale;
    }

    public Employer(String email, String name, String phone, String address, String description, String domain, String scale,
                    String password, int userType) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.description = description;
        this.domain = domain;
        this.scale = scale;
        this.password = password;
        this.userType = userType;
    }

    public Employer() {
    }
}
