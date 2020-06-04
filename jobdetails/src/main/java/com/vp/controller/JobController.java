package com.vp.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vp.exception.JobNotFoundException;
import com.vp.model.JobDetailsDto;
import com.vp.model.Jobdetails;
import com.vp.service.IJobService;
import com.vp.service.JobServiceImpl;



@RestController
@RequestMapping("/jobapi")
public class JobController {
	
	Logger log = LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	IJobService jobServiceInterface;
	
	//get all jobs
	
	@GetMapping("/jobs") 
	public List<Jobdetails> getJobdetails()
	
	{
		//logging
		 
		log.info("get jobdetails method is called");
		
		return jobServiceInterface.getAllJobdetails();
	}
	
	// add a job
	
	@PostMapping("/addjob")
	public void insertJob(@Valid @RequestBody JobDetailsDto job)
	
	{
		//logging
		  log.info("insert Job method called");
		
		jobServiceInterface.createJob(job);
	}
	
	//delete job by id
	
	@RequestMapping(value = "/deletejob/{Jobid}", method = RequestMethod.DELETE)
	public ResponseEntity deleteJob(@PathVariable Integer Jobid) 
	
	{
		
		//loging
				String methodName = "deleteJob()";
				log.info(methodName+" called");
				
		jobServiceInterface.deletejob(Jobid);
		return new ResponseEntity<>(jobServiceInterface.deletejob(Jobid),HttpStatus.OK);
	}
	
	
	//update user by id
	
	@PutMapping("/update/{Jobid}")
	public ResponseEntity updateJob(@RequestBody Jobdetails jobdetails,@PathVariable Integer Jobid) 
	
	{
		//logging
		 
		log.info("updateJob method is called");
		
		jobServiceInterface.updateJob(Jobid,jobdetails);
		return new ResponseEntity<>( jobServiceInterface.updateJob(Jobid,jobdetails),HttpStatus.OK);
		
	}
	
	
	
}
	




