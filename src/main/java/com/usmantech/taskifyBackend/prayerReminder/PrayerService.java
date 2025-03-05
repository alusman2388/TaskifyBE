package com.usmantech.taskifyBackend.prayerReminder;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrayerService {
	@Autowired
	private PrayerRepo repo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	 private final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
	    private ScheduledFuture<?> futureTask;

	    public PrayerService() {
	        taskScheduler.initialize();
	    }
	    @PostConstruct
	    public void scheduleAllPrayerEmails() {
	        List<PrayerEntity> prayers = repo.findAll();
	        for (PrayerEntity prayer : prayers) {
	            scheduleEmailTask(prayer);
	        }
	    }

	    private void scheduleEmailTask(PrayerEntity prayer) {
	        LocalTime prayerTime = prayer.getPrayerTime();
	        LocalTime now = LocalTime.now();
	        
	        long delay = Duration.between(now, prayerTime).getSeconds();
	        if (delay < 0) {
	            delay += 24 * 60 * 60; // Schedule for the next day if time has passed
	        }

	        System.out.println("⏳ Email for " + prayer.getPrayerName() + " will be sent in " + delay + " seconds");

	        futureTask = taskScheduler.schedule(() -> sendEmail(prayer), Instant.now().plusSeconds(delay));
	    }
	
	    public void sendEmail(PrayerEntity prayer) {
	        System.out.println("📧 Sending email for " + prayer.getPrayerName());

	        try {
	            SimpleMailMessage message = new SimpleMailMessage();
	            message.setFrom("mohammedusmansha@gmail.com");
	            message.setTo("usmanpersonal0@gmail.com");
	            message.setSubject("Prayer Reminder: " + prayer.getPrayerName());
	            message.setText(prayer.getEmailMessage());

	            mailSender.send(message);
	            System.out.println("✅ Email Sent for " + prayer.getPrayerName());
	        } catch (Exception e) {
	            System.out.println("❌ Error sending email: " + e.getMessage());
	        }
	    }

}
