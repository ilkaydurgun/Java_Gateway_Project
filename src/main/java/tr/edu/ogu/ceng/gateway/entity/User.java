package tr.edu.ogu.ceng.gateway.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class User {
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

    @OneToMany(mappedBy = "user")
    private List<ApiKey> apiKeys;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    // Getters and Setters
}
