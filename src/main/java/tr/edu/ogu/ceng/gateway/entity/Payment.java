package tr.edu.ogu.ceng.gateway.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payments") // Tablonuzun adı ile eşleştirilmesi için eklenmiştir.
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User ile ManyToOne ilişki
    @ManyToOne(fetch = FetchType.LAZY) // Performans için LAZY yükleme önerilir.
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    // Order ID alanı
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    // Ödeme miktarı
    @Column(name = "amount", nullable = false, precision = 10, scale = 2) // Ondalık için precision ve scale
    private BigDecimal amount;

    // Para birimi
    @Column(name = "currency", nullable = false, length = 10)
    private String currency;

    // Ödeme yöntemi
    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    // Ödeme durumu
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    // Diğer izleme sütunları
    @Column(name = "created_by", length = 255)
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // Varsayılan olarak şimdi

    @Column(name = "updated_by", length = 255)
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_by", length = 255)
    private String deletedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Versiyonlama
    @Version
    @Column(name = "version")
    private Integer version;

    // Transactions ile ilişki
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true) // Cascade ve orphan removal
    private List<Transaction> transactions;

    // PaymentLog ile ilişki
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentLog> paymentLogs;
}
