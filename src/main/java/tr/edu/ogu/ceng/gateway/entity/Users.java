package tr.edu.ogu.ceng.gateway.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true) // Uzak bilgisayardaki users mikroservisinde bizde bulunmayan değişkenler olduğu için herhangi bir hata almamak için yazıldı
public class Users {

	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Users users = (Users) o;
	        return Objects.equals(username, users.username);  // username'ı eşitlik için baz alabilirsiniz
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(username);  // Aynı şekilde hashCode'da da username'ı kullanabilirsiniz
	    }
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String roles;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<ApiKey> apiKeys;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Version
    @Column(name = "version")
    private Integer version;
}
