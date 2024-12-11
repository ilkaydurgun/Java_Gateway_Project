package tr.edu.ogu.ceng.gateway.controllertests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import tr.edu.ogu.ceng.gateway.common.DPS;
import tr.edu.ogu.ceng.gateway.controller.AuthenticationTokenController;
import tr.edu.ogu.ceng.gateway.controller.UsersController;
import tr.edu.ogu.ceng.gateway.entity.Users;
import tr.edu.ogu.ceng.gateway.repository.UsersRepository;
import tr.edu.ogu.ceng.gateway.service.UsersService;

public class TestUserController extends DPS {
	
	// http adresi verilecek değişken oluştur
	
	static String url = "";

	@Mock
	UsersService usersService;
	
    @InjectMocks
	UsersController usersController;
	
    @Mock
    private AuthenticationTokenController authenticationTokenController;

    
	Users user=new Users();

	
	@BeforeEach
	void setup() {
        MockitoAnnotations.openMocks(this);
        user.setId(1L);
		user.setCreatedAt(LocalDateTime.now());
		user.setUsername("baristurgut");
		user.setPassword("12345");
		user.setRoles("root");
		user.setEmail("cihanbaristr@gmail.com");
		
	}
	
	@Test
	public void testGetUserByUsername() {
		when(usersService.getUserByUsername("baristurgut")).thenReturn(user);

		ResponseEntity<Users> returnedUser = usersController.getUserByUsername("baristurgut");
        assertEquals(user, returnedUser.getBody());
	}

	@Test
	public void testGetUserById() {
		when(usersService.getUser(1L)).thenReturn(user);

		ResponseEntity<Users> returnedUser = usersController.getUser(1L);
        assertEquals(user, returnedUser.getBody());
	}
	
	@Test
	public void testGetUserByEmail() {
		when(usersService.getUserByEmail("cihanbaristr@gmail.com")).thenReturn(user);

		ResponseEntity<Users> returnedUser = usersController.getUserByEmail("cihanbaristr@gmail.com");
        assertEquals(user, returnedUser.getBody());
	}
	
	@Test
    void testLoginUser_SuccessfulLogin() {
        // Mock davranışları ayarla
        when(usersService.getUserByUsernameFromUserMicroservice("baristurgut")).thenReturn(user);
        when(authenticationTokenController.getAuthenticationTokenByUsername("baristurgut")).thenReturn("valid-token");
        when(authenticationTokenController.getValidateAuthenticationTokenByUsername("valid-token")).thenReturn(true);

        // Metodu çağır ve sonucu kontrol et
        ResponseEntity<String> response = usersController.loginUser("baristurgut");

        assertEquals(200,response.getStatusCode().value()); // HTTP 200
        assertEquals("Giriş başarılı!", response.getBody());
    }

    @Test
    void testLoginUser_InvalidToken() {
        // Mock davranışları ayarla
        when(usersService.getUserByUsernameFromUserMicroservice("baristurgut")).thenReturn(user);
        when(authenticationTokenController.getAuthenticationTokenByUsername("baristurgut")).thenReturn("invalid-token");
        when(authenticationTokenController.getValidateAuthenticationTokenByUsername("invalid-token")).thenReturn(false);

        // Metodu çağır ve sonucu kontrol et
        ResponseEntity<String> response = usersController.loginUser("baristurgut");

        assertEquals(401, response.getStatusCode().value()); // HTTP 401
        assertEquals("Geçersiz veya eksik token!", response.getBody());
    }

    @Test
    void testLoginUser_UserNotFound() {
        // Mock davranışları ayarla
        when(usersService.getUserByUsernameFromUserMicroservice("nonexistentuser")).thenReturn(null);

        // Metodu çağır ve sonucu kontrol et
        ResponseEntity<String> response = usersController.loginUser("nonexistentuser");

        assertEquals(404, response.getStatusCode().value()); // HTTP 404
        assertEquals("Geçersiz kullanıcı!", response.getBody());
    }
	
}
