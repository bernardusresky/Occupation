package com.amateur.occupation.vo;

import com.amateur.occupation.entity.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType
@XmlRootElement
@Data
public class JobVO implements Serializable {
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
    /**
     * job status,1 is valid,0 is invalid
     */
    private int status;
    private Employer employer;
}