package tech.sangdang.todocrud.modules.tasks.app.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
public class UpdateTaskDto {
    @JsonIgnore
    String id;
    String title;
    String description;
}
