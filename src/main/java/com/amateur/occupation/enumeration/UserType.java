package com.amateur.occupation.enumeration;

import com.baomidou.mybatisplus.enums.IEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public enum UserType implements IEnum {
    ADMIN(0),
    EMPLOYER(1),
    EMPLOYEE(2);
    private int code;

    UserType(int code) {
        this.code = code;
    }

    public static UserType getType(int code) {
        UserType type = null;
        switch (code) {
            case 0:
                type = UserType.ADMIN;
                break;
            case 1:
                type = UserType.EMPLOYER;
                break;
            case 2:
                type = UserType.EMPLOYEE;
                break;
            default:
                log.error("get UserType error");
                type = UserType.ADMIN;
                break;
        }
        return type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }


}
