package io.srk.export.model.export.entity;

import io.srk.export.model.export.enumeration.ExportStatus;
import io.srk.export.model.export.enumeration.ExportType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Data
@Entity
@Table(schema = "export_schema", name = "export_request")
public class ExportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private ExportStatus status;

    private String failureReason;

    @Column(columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private ExportType type;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> data;

    private Long fileId;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant updated;
}
