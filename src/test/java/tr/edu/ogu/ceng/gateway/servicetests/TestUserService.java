package tr.edu.ogu.ceng.gateway.servicetests;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.MediaType;
import org.springframework.web.client.*;

import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.service.UsersService;

class TestUserService {

    @InjectMocks
    private UsersService usersService;

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    private Users testUser;

    @BeforeEach
    void setUp() {
        // Mockito ile mock'ları başlatıyoruz
        MockitoAnnotations.openMocks(this);

        // testUser nesnesini örnek kullanıcı verisi ile dolduruyoruz
        testUser = new Users();
        testUser.setId(1L);
        testUser.setUsername("baristurgut");
        testUser.setEmail("cihanbaristr@gmail.com");
        testUser.setRoles("user");
        testUser.setPassword("encrypted_password");

        // RestClient mock davranışlarını ayarlıyoruz
        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.contentType(MediaType.APPLICATION_JSON)).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.body(any())).thenReturn(requestBodyUriSpec);
    }

    @Test
    void testCreateNewUser_Success() {
        // Başarı durumunda dönecek olan kullanıcı nesnesi
        Users mockUser = new Users();
        mockUser.setUsername("newuser");
        mockUser.setEmail("newuser@example.com");

        // Mock response for retrieve
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(Users.class)).thenReturn(mockUser);

        // Kullanıcı oluşturuluyor
        Users savedUser = usersService.createNewUser(mockUser);

        // Sonuçları doğruluyoruz
        assertNotNull(savedUser);
        assertEquals("newuser", savedUser.getUsername());
        assertEquals("newuser@example.com", savedUser.getEmail());
    }

    @Test
    void testCreateNewUser_Failure() {
        // Başarısızlık durumunda dönecek response mock'laması
        Users mockUser = new Users();
        mockUser.setUsername("newuser");
        mockUser.setEmail("newuser@example.com");

        // Mock response for retrieve (failure)
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(Users.class)).thenThrow(new RuntimeException("Error"));

        // Hata durumunda kullanıcı nesnesinin null döneceğini doğruluyoruz
        Users createdUser = usersService.createNewUser(mockUser);
        assertNull(createdUser);
    }



    @Test
    void testGetUserByUsername() {
        // Kullanıcı adı ile kullanıcıyı mock'lıyoruz
        when(usersService.getUserByUsername("baristurgut")).thenReturn(testUser);
        // Servis metodunu çağırıyoruz
        Users retrievedUser = usersService.getUserByUsername("baristurgut");
        // Sonuçları doğruluyoruz
        assertNotNull(retrievedUser);
        assertEquals("baristurgut", retrievedUser.getUsername());
    }


    @Test
    void testGetUserByEmail() {
        // Kullanıcı email'i ile kullanıcıyı mock'lıyoruz
        when(usersService.getUserByEmail("cihanbaristr@gmail.com")).thenReturn(testUser);
        // Servis metodunu çağırıyoruz
        Users retrievedUser = usersService.getUserByEmail("cihanbaristr@gmail.com");
        // Sonuçları doğruluyoruz
        assertNotNull(retrievedUser);
        assertEquals("baristurgut", retrievedUser.getUsername());
    }

    @Test
    void testGetUserByUsernameFromUserMicroservice() {
        // Kullanıcı bilgilerini mikroservisten almak için mock'lıyoruz
        when(restClient.get().uri(anyString()).accept(any()).retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(Users.class)).thenReturn(testUser);
        // Servis metodunu çağırıyoruz
        Users retrievedUser = usersService.getUserByUsernameFromUserMicroservice("baristurgut");
        // Sonuçları doğruluyoruz
        assertNotNull(retrievedUser);
        assertEquals("baristurgut", retrievedUser.getUsername());
    }
}
