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
@TableName("interaction")
public class Interaction implements Serializable {
    @TableId(value = "inter_id", type = IdType.AUTO)
    private int interId;
    private int jobId;
    /**
     * timestamp,like yyyy-MM-dd HH:MM:SS
     */
    private String createTime;
    private String employeeEmail;
    private String employerEmail;
    /**
     * <0 is application,>0 is offer
     * |1| is waiting,|2| is rejected,|3| is accept
     */
    private int checkStatus;
}
