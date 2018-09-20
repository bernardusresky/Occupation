
package com.amateur.occupation.entity;

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
@TableName("job")
public class Job implements Serializable {
    @TableId("job_id")
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


	    public Job(String salary, String expectWorkExperience, String expectEduBackground, String jobRequirement) {
	     this.salary = salary;
	     this.expectWorkExperience = expectWorkExperience;
	     this.expectEduBackground = expectEduBackground;
	     this.jobRequirement = jobRequirement;
	    }
	    
	    public String getSalary()
	    {
	    	return salary;
		       
	    }
	    
	    public String getExpectWorkExperience() {
	    	return expectWorkExperience;
	    }
	    
	    public String getExpectEduBackground() {
	    	return expectEduBackground;
	    }
	    
	    public String getJobRequirement() {
	    	return jobRequirement;
	    }
	          
}    
	 