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
@TableName("employee")
public class Employee implements Serializable {
    @TableId("email")
    private String email;
    private String name;
    private String phone;
    /**
     * yyyy-MM-dd,like 1970-01-01
     */
    private String birthday;
    /**
     * 1 male,0 female
     */
    private int gender;
    private String eduBackground;
    private String workExperience;
    private String expectSalary;
    private String expectCity;
    private String address;
    /**
     * self description
     */
    private String description;
    @TableField(exist = false)
    private String password;
    @TableField(exist = false)
    private int userType;


    public Employee(String email, String name, String phone, String birthday,
                    int gender, String eduBackground, String workExperience, String expectSalary,
                    String expectCity, String address, String description, String password, int userType) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.eduBackground = eduBackground;
        this.workExperience = workExperience;
        this.expectSalary = expectSalary;
        this.expectCity = expectCity;
        this.address = address;
        this.description = description;
        this.password = password;
        this.userType = userType;
    }

    public Employee(String email, String name, String phone, String birthday, int gender, String eduBackground,
                    String workExperience, String expectSalary, String expectCity,
                    String address, String description) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.eduBackground = eduBackground;
        this.workExperience = workExperience;
        this.expectSalary = expectSalary;
        this.expectCity = expectCity;
        this.address = address;
        this.description = description;
    }

    public Employee() {
    }
}
