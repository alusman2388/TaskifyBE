package com.usmantech.taskifyBackend.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.usmantech.taskifyBackend.email.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FixedRateScheduler {
	
	@Autowired
	private EmailService emailService;
	
	
	

}
