package tr.edu.ogu.ceng.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import tr.edu.ogu.ceng.gateway.entity.ApiKey;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.ApiKeyRepository;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTests {

    @Autowired
    private ApiKeyRepository apiKeyRepository;
    
    @Autowired
    private UsersRepository userRepository;

    @Test
    public void testApiKeyRepository() {
        // Given (Verilen)
        Users bUser = new Users();
        bUser.setUsername("Admin User " + System.currentTimeMillis()); // Eşsiz bir kullanıcı adı oluştur
        bUser.setCreatedAt(LocalDateTime.now());
        bUser.setEmail("ilerijava@phyton.com" + System.currentTimeMillis());
        bUser.setPassword("phyton");
        bUser.setRoles("Admin User");
        userRepository.save(bUser); // Kullanıcıyı veritabanına kaydet

        ApiKey apiKey = new ApiKey();
        apiKey.setUser(bUser);
        apiKey.setApiKey("test-api-key"+ System.currentTimeMillis());
        apiKey.setCreatedBy("test-creator");
        apiKey.setCreatedAt(LocalDateTime.now());
        apiKey.setUpdatedBy("test-updater");
        apiKey.setDeletedBy("test-deleter");
        apiKey.setVersion(1); // Versiyonu ayarla

     // When (İşlem)
        ApiKey savedApiKey = apiKeyRepository.save(apiKey);

        // Then (Doğrulamalar)
        assertNotNull(savedApiKey.getId()); // Id değerinin null olmadığını kontrol edin
        Optional<ApiKey> retrievedApiKey = apiKeyRepository.findById(savedApiKey.getId()); // Kaydedilen nesneyi bulun

        assertTrue(retrievedApiKey.isPresent(), "ApiKey bulunamadı!"); // ApiKey'in bulunduğunu kontrol edin
        ApiKey foundApiKey = retrievedApiKey.get(); // Bulunan nesneyi al

        assertNotNull(foundApiKey);
        assertEquals("test-creator", foundApiKey.getCreatedBy());
        assertEquals("test-updater", foundApiKey.getUpdatedBy());
        assertEquals("test-deleter", foundApiKey.getDeletedBy());
        assertEquals(bUser.getUsername(), retrievedApiKey.get().getUser().getUsername());
        assertEquals(1, foundApiKey.getVersion());
    }
}
