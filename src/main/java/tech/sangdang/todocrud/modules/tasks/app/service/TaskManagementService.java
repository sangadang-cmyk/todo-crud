package tech.sangdang.todocrud.modules.tasks.app.service;

import tech.sangdang.todocrud.modules.tasks.app.dto.request.CreateTaskDto;
import tech.sangdang.todocrud.modules.tasks.app.dto.request.DeleteTaskDto;
import tech.sangdang.todocrud.modules.tasks.app.dto.request.UpdateTaskDto;
import tech.sangdang.todocrud.modules.tasks.app.dto.response.TaskEntityDto;

public interface TaskManagementService {
    TaskEntityDto createTask(CreateTaskDto dto);
    TaskEntityDto updateTask(UpdateTaskDto dto);
    void deleteTask(DeleteTaskDto dto);
}
