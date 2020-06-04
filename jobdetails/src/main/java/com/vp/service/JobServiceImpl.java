 package com.vp.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vp.exception.JobNotFoundException;
import com.vp.exception.JobServiceException;
import com.vp.model.JobDetailsDto;
import com.vp.model.Jobdetails;
import com.vp.repository.JobRepository;




@Service
@Transactional
public class JobServiceImpl implements IJobService{
	

	@Autowired
	JobRepository jobRepository;
	
	Logger log = LoggerFactory.getLogger(JobServiceImpl.class);
	
	public List<Jobdetails> getAllJobdetails(){
	
		log.info("get jobgetails is invoked");
		
		List<Jobdetails> jobList;
		try
		{
			jobList = (List<Jobdetails>) jobRepository.findAll();
			
		}
		catch(JobServiceException ex)
		{
			log.error("service exception occured");
			throw new JobServiceException(" job Service is temporarily unavailable");
			
		}
		return jobList;
	}
	
	
	
	//saving a job to database
	
	public JobDetailsDto createJob(JobDetailsDto job) 
	
	{
		log.info("create job method called");
		
		ModelMapper maper=new ModelMapper();
		maper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Jobdetails job1=maper.map(job, Jobdetails.class);
		jobRepository.save(job1);
		return null;
	}
 
 
	
 //delete a job using id
	
	public String deletejob(Integer Jobid) 
	
	{
		//logging
		
		String methodName = "deletejob()";
		log.info(methodName+" called");
		
		Jobdetails existingJob=this.jobRepository.findById(Jobid)
				.orElseThrow(()-> new JobNotFoundException("job not found with id:" +Jobid));
	
			jobRepository.deleteById(Jobid);
			
			return "Jobdetails with id "+Jobid+" is deleted";
		
}
	
	//update a job by id
	
	@Override
	public String updateJob(Integer Jobid, Jobdetails jobdetails) 
	
	{
		Jobdetails existingJob=this.jobRepository.findById(Jobid)
				.orElseThrow(()-> new JobNotFoundException("job not found with id:" +Jobid));
							
		log.error("job id not found error");
		existingJob.setJobid(jobdetails.getJobid());
		existingJob.setJobname(jobdetails.getJobname());
		existingJob.setJobskills(jobdetails.getJobskills());
		existingJob.setCompanyname(jobdetails.getCompanyname());
		existingJob.setSalary(jobdetails.getSalary());
		
		Jobdetails updatedJob=jobRepository.save(existingJob);
		
		log.info("updatejob is completed");
		return "Jobdetails with id "+Jobid+" is updated";
	}

}



/*public boolean updatejobdetails(Jobdetails jobdetails) 
{
	return jobRepository.save(jobdetails)!=null;
}*/



/*public void saveJob(Jobdetails jobdetails)
{
	log.info(" insert a jobdetails");
	jobRepository.save(jobdetails);
	log.info("inserted successfully");
 
}*/