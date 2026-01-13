package tech.sangdang.todocrud.modules.tasks.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@DynamoDbBean
public class TaskEntity {
    @Getter(onMethod_ = {@DynamoDbPartitionKey})
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @Builder.Default
    private String createdAt = LocalDateTime.now().toString();

    @Builder.Default
    private String updatedAt =  LocalDateTime.now().toString();

    private String title;
    private String description;

    public TaskEntity newUpdatedAt() {
        this.updatedAt = LocalDateTime.now().toString();
        return this;
    }
}
