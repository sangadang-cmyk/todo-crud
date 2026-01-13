package tech.sangdang.todocrud.modules.tasks.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.sangdang.todocrud.modules.tasks.app.dto.request.CreateTaskDto;
import tech.sangdang.todocrud.modules.tasks.app.dto.request.DeleteTaskDto;
import tech.sangdang.todocrud.modules.tasks.app.dto.request.UpdateTaskDto;
import tech.sangdang.todocrud.modules.tasks.app.dto.response.TaskEntityDto;
import tech.sangdang.todocrud.modules.tasks.app.service.TaskManagementService;
import tech.sangdang.todocrud.modules.tasks.app.service.TaskQueryService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskManagementService taskManagementService;
    private final TaskQueryService taskQueryService;

    @PostMapping()
    public ResponseEntity<TaskEntityDto> createTask(@RequestBody CreateTaskDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskManagementService.createTask(dto));
    }

    @GetMapping("/batch")
    public ResponseEntity<List<TaskEntityDto>> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskQueryService.getAllTasks());
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskEntityDto> updateTask(@PathVariable("taskId") String taskId,
            @RequestBody UpdateTaskDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(taskManagementService.updateTask(
                dto.toBuilder().id(taskId).build()));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskEntityDto> getTaskById(@PathVariable("taskId") String taskId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskQueryService.getTaskById(taskId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") String taskId) {
        taskManagementService.deleteTask(DeleteTaskDto.builder().id(taskId).build());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
