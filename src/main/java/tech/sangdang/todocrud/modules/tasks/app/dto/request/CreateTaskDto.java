package tech.sangdang.todocrud.modules.tasks.app.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
public class CreateTaskDto {
    String title;
    String description;
}
