package tech.sangdang.todocrud.modules.tasks.app.service.impl;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import tech.sangdang.todocrud.modules.tasks.app.dto.request.CreateTaskDto;
import tech.sangdang.todocrud.modules.tasks.app.dto.request.DeleteTaskDto;
import tech.sangdang.todocrud.modules.tasks.app.dto.request.UpdateTaskDto;
import tech.sangdang.todocrud.modules.tasks.app.dto.response.TaskEntityDto;
import tech.sangdang.todocrud.modules.tasks.app.service.TaskManagementService;
import tech.sangdang.todocrud.modules.tasks.domain.TaskEntity;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskManagementServiceImpl implements TaskManagementService {
    private final DynamoDbTemplate dynamoDbTemplate;

    @Override
    public TaskEntityDto createTask(CreateTaskDto dto) {
        TaskEntity task = TaskEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();

        System.out.println(task.getId());

        TaskEntity savedTask = dynamoDbTemplate.save(task);

        return TaskEntityDto.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    @Override
    public TaskEntityDto updateTask(UpdateTaskDto dto) {
        TaskEntity task = TaskEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();

        task.newUpdatedAt();

        dynamoDbTemplate.update(task);

        return TaskEntityDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteTask(DeleteTaskDto dto) {
        var partitionKey = Key.builder().partitionValue(dto.getId()).build();
        if(partitionKey == null) {
            throw new RuntimeException("Task not found with id: " + dto.getId());
        }
        dynamoDbTemplate.delete(partitionKey, TaskEntity.class);
    }
}
