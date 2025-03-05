package com.usmantech.taskifyBackend.prayerReminder;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "prayer_times")
@Data
public class PrayerEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;

    private String prayerName;

    @Column(columnDefinition = "TIME")
    private LocalTime prayerTime; // Ensure this matches SQL Server's TIME type

    private String emailMessage;
}
