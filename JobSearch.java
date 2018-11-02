import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class JobSearch {
	
	public Connection getConnection()
    {
        Connection con = null;
        
        try{
        	//Change this connection to the job database
            con = DriverManager.getConnection("jdbc:mysql://localhost/job.db","root","");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return con;
    }
	
	public void JobElement()
    {
        ArrayList<Job> jobList = new ArrayList<Job>();
        
        Statement stm;
        ResultSet rslt;
        
        try{
            Connection con = getConnection();
            stm = con.createStatement();
            //change this too
            String searchQuery = "SELECT * "
            		+ " FROM `job` ";
            rslt = stm.executeQuery(searchQuery);
            
            Job job;
            
            while(rslt.next())
            {
                job = new Job(
                                 rslt.getString("salary"),
                                 rslt.getString("expectWorkExperience"),
                                 rslt.getString("expectEduBackground"),
                                 rslt.getString("jobRequirement")
                                 
                                );
                jobList.add(job);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
   
	public void matchmakeJob()
	{
		
	}
}
