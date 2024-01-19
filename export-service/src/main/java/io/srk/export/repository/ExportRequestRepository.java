package io.srk.export.repository;

import io.srk.export.model.export.entity.ExportRequest;
import io.srk.export.model.export.enumeration.ExportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ExportRequestRepository extends JpaRepository<ExportRequest, Long> {

    @Query(
            nativeQuery = true,
            value = """
                        SELECT *
                        FROM export_schema.export_request
                        WHERE status = 'NEW'
                        ORDER BY created
                        LIMIT 1
                    """
    )
    Optional<ExportRequest> getRequestToProcess();

    @Modifying
    @Query("UPDATE ExportRequest er SET er.status = :status WHERE er.id = :id")
    void updateStatus(Long id, ExportStatus status);
}
