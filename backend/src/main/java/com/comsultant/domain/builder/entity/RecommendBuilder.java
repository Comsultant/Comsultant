package com.comsultant.domain.builder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RecommendBuilder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", columnDefinition = "BIGINT(20) UNSIGNED")
    private long idx;

    @Column(name = "builder_id", columnDefinition = "VARCHAR(255)")
    private String builderId;

    @Column(name = "create_date", updatable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "price",  columnDefinition = "INT")
    private int price;

    @Column(name = "cpu_corp", columnDefinition = "VARCHAR(20)")
    private String cpuCorp;

    @Column(name = "vga_corp", columnDefinition = "VARCHAR(20)")
    private String vgaCorp;
}
