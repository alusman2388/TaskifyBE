package com.usmantech.taskifyBackend.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
	  List<TaskEntity> findByPriority(String priority);
	    List<TaskEntity> findByCompleted(boolean completed);
	    List<TaskEntity> findByTitleContainingIgnoreCase(String title);
	    long countByCompleted(boolean completed);
	    long countByCompletedFalse();
	    List<TaskEntity> findByCompletedFalse();

	    long count();  
	    boolean existsByTitleIgnoreCase(String title);

}
