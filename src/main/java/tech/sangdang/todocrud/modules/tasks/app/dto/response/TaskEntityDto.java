package tech.sangdang.todocrud.modules.tasks.app.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
public class TaskEntityDto {
    String id;
    String createdAt;
    String updatedAt;
    String title;
    String description;
}
