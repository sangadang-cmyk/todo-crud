package tech.sangdang.todocrud.modules.tasks.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tech.sangdang.todocrud.modules.tasks.app.dto.response.TaskEntityDto;
import tech.sangdang.todocrud.modules.tasks.app.service.TaskQueryService;
import tech.sangdang.todocrud.modules.tasks.domain.TaskEntity;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskQueryServiceImpl implements TaskQueryService {
    private final DynamoDbTemplate dynamoDbTemplate;

    @Override
    public List<TaskEntityDto> getAllTasks() {
        return dynamoDbTemplate.scanAll(TaskEntity.class)
                .items().stream().map((task) -> TaskEntityDto.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .createdAt(task.getCreatedAt())
                        .updatedAt(task.getUpdatedAt())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public TaskEntityDto getTaskById(String id) {
        Key key = Key.builder()
                .partitionValue(id)
                .build();

        if (key == null) {
            throw new RuntimeException("Task not found with id: " + id);
        }

        TaskEntity task = dynamoDbTemplate.load(key, TaskEntity.class);

        if (task == null) {
            throw new RuntimeException("Task not found with id: " + id);
        }

        return TaskEntityDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}
