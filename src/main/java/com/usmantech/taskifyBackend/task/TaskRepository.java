package com.usmantech.taskifyBackend.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
	  List<TaskEntity> findByCategory(String category);
	    List<TaskEntity> findByCompleted(boolean completed);
	    List<TaskEntity> findByTitleContainingIgnoreCase(String title);
	    long countByCompleted(boolean completed);
	    long countByCompletedFalse();
	    boolean existsByTitleIgnoreCase(String title);

}
