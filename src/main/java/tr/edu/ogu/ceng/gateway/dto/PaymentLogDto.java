package tr.edu.ogu.ceng.gateway.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentLogDto {
    private Long id;
    private Long paymentId; // payment_id foreign key
    private String action;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
    private String details;
}
