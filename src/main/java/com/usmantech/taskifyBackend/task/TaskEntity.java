package com.usmantech.taskifyBackend.task;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "tasks")
@AllArgsConstructor
public class TaskEntity {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String title;
	    private String description;
	    private String priority;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
	    private LocalDateTime completedAt;
	    private boolean completed;
	    
	    public TaskEntity() {
	        this.createdAt = LocalDateTime.now();
	        this.completed = false;
	    }
	    
	  
}
