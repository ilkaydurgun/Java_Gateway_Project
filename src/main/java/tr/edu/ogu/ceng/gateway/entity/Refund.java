package tr.edu.ogu.ceng.gateway.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Refund {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "transaction_id", nullable = false)
	    private Transaction transaction;

	    @Column(nullable = false)
	    private BigDecimal amount;

	    @Column(nullable = false)
	    private String status;

	    @Column(nullable = false)
	    private LocalDateTime createdAt;

}