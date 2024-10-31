package tr.edu.ogu.ceng.gateway.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogDto {
    private Long id;
    private Long apiKeyId; // api_key_id foreign key
    private String endpoint;
    private LocalDateTime requestTime;
    private LocalDateTime responseTime;
    private Integer statusCode;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
}
