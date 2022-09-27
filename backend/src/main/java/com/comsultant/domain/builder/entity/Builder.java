package com.comsultant.domain.builder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@lombok.Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "builders")
public class Builder {
    @Id
    private String _id;

    private String name;

    @Column(name = "create_date", updatable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createDate;
}
