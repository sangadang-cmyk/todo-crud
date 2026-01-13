package tech.sangdang.todocrud.modules.tasks.app.service;

import java.util.List;

import tech.sangdang.todocrud.modules.tasks.app.dto.response.TaskEntityDto;

public interface TaskQueryService {
    List<TaskEntityDto> getAllTasks();
    TaskEntityDto getTaskById(String id);
}
