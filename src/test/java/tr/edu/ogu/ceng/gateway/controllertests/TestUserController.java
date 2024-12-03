package tr.edu.ogu.ceng.gateway.controllertests;

import org.mockito.Mock;

import tr.edu.ogu.ceng.gateway.common.DPS;
import tr.edu.ogu.ceng.gateway.service.UsersService;

public class TestUserController extends DPS {
	
	// http adresi verilecek değişken oluştur
	
	static String url = "";

	// controller testleri yaz
	@Mock
	UsersService usersService;
	

}
