package tr.edu.ogu.ceng.gateway.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuthenticationTokenDto {
    private Long id;
    private Long userId; // user_id foreign key
    private String token;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
}
