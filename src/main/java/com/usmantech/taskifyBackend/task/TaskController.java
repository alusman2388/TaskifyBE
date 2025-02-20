package com.usmantech.taskifyBackend.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usmantech.taskifyBackend.exception.TaskAlreadyExistsException;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:5173")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/add-task")
    public ResponseEntity<?> createTask(@RequestBody TaskEntity task) 
    { 
    	//return taskService.createTask(task); 
    	 try {
             TaskEntity savedTask = taskService.createTask(task);
             return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
         } catch (TaskAlreadyExistsException e) {
             return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // 409 Conflict
         }
    	}

    @GetMapping("/get-all-tasks")
    public List<TaskEntity> getAllTasks() { return taskService.getAllTasks(); }
    
    @GetMapping("/get-task/{id}")
    private TaskEntity getTaskById(@PathVariable Long id) {
    	return taskService.getTaskById(id);
	}

    @PutMapping("/update-task/{id}")
    public TaskEntity updateTask(@PathVariable Long id, @RequestBody TaskEntity task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/del-task/{id}")
    public void deleteTask(@PathVariable Long id) { taskService.deleteTask(id); }

    @GetMapping("/category/{category}")
    public List<TaskEntity> getTasksByCategory(@PathVariable String category) {
        return taskService.getTasksByCategory(category);
    }

    @GetMapping("/status/{completed}")
    public List<TaskEntity> getTasksByStatus(@PathVariable boolean completed) {
        return taskService.getTasksByStatus(completed);
    }

    @GetMapping("/search")
    public List<TaskEntity> searchTasksByTitle(@RequestParam String title) {
        return taskService.searchTasksByTitle(title);
    }
    @GetMapping("/completed")
    public ResponseEntity<Long> getTotalCompletedTasks() {
        long count = taskService.getTotalCompletedTasks();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/pending")
    public ResponseEntity<Long> getTotalPendingTasks() {
        long count = taskService.getTotalPendingTasks();
        return ResponseEntity.ok(count);
    }
}

