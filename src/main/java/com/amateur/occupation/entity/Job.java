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
@TableName("job")
public class Job implements Serializable {
    @TableId(value = "job_id", type = IdType.AUTO)
    private int jobId;
    /**
     * the number of needed person for this job
     */
    private int number;
    private String salary;
    private String expectWorkExperience;
    private String expectEduBackground;
    /**
     * job description
     */
    private String description;
    private String jobRequirement;
    /**
     * timestamp,like yyyy-MM-dd HH:MM:SS
     */
    private String createTime;
    private String createEmployerEmail;
    /**
     * job status,1 is valid,0 is invalid
     */
    private int status;

    public Job() {
    }

    public Job(int jobId, int number, String salary, String expectWorkExperience, String expectEduBackground,
               String description, String jobRequirement, String createEmployerEmail, int status) {
        this.jobId = jobId;
        this.number = number;
        this.salary = salary;
        this.expectWorkExperience = expectWorkExperience;
        this.expectEduBackground = expectEduBackground;
        this.description = description;
        this.jobRequirement = jobRequirement;
        this.createEmployerEmail = createEmployerEmail;
        this.status = status;
    }

    public Job(int jobId, int number, String salary, String expectWorkExperience, String expectEduBackground,
               String description, String jobRequirement, String createTime, String createEmployerEmail, int status) {
        this.jobId = jobId;
        this.number = number;
        this.salary = salary;
        this.expectWorkExperience = expectWorkExperience;
        this.expectEduBackground = expectEduBackground;
        this.description = description;
        this.jobRequirement = jobRequirement;
        this.createTime = createTime;
        this.createEmployerEmail = createEmployerEmail;
        this.status = status;
    }
}
