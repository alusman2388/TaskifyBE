package com.usmantech.taskifyBackend.task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmantech.taskifyBackend.exception.TaskAlreadyExistsException;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity createTask(TaskEntity task) { 
    	 if (taskRepository.existsByTitleIgnoreCase(task.getTitle())) {
             throw new TaskAlreadyExistsException("Task already exists.");
         }
    	return taskRepository.save(task); 
    	}
    
    public List<TaskEntity> getAllTasks() 
    {
    	return taskRepository.findAll();
    	}
    
    public TaskEntity getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
    
    public TaskEntity updateTask(Long id, TaskEntity task) 
    {
        TaskEntity existingTask = taskRepository.findById(id).orElseThrow();
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setPriority(task.getPriority());
        existingTask.setCompleted(task.isCompleted());
        existingTask.setUpdatedAt(LocalDateTime.now());
        if (task.isCompleted()) {
            existingTask.setCompletedAt(LocalDateTime.now());
        }

        return taskRepository.save(existingTask);
    }
    
    public void deleteTask(Long id) 
    { 
    	taskRepository.deleteById(id); 
    }
    public List<TaskEntity> getTasksByPriority(String priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<TaskEntity> getTasksByStatus(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }

    public List<TaskEntity> searchTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
    public long getTotalCompletedTasks() {
        return taskRepository.countByCompleted(true); // Count completed tasks
    }
    public List<TaskEntity> findByCompletedFalse() {
        return taskRepository.findByCompletedFalse();
    }

    public long getTotalPendingTasks() {
        return taskRepository.countByCompletedFalse(); // Count pending tasks
    }
}

