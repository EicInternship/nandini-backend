package com.einfochips.ecommerce.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.einfochips.ecommerce.Repository.UserRepo;
import com.einfochips.ecommerce.entity.User;
import com.einfochips.ecommerce.exception.EmailNotFoundExcaption;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores .class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class UserServiceImplTest {
	
	
	
	@Mock	
	private BCryptPasswordEncoder passwordEncoder;
	
	@Mock
	private UserRepo userrepo;
	
	@Mock
	private EmailNotFoundExcaption EmailException;
	
	@Mock
	private EmailSendingService mailservice;

	@InjectMocks
	private UserServiceImpl userserviceimpl;
	
	

    @BeforeAll
   static  void BeforeEachTest() throws Exception{
	System.out.println("Before Executing Test Cases");
	System.out.println(".....................................................");
    }
    
	@BeforeEach
	void setUp() throws Exception {
//		UserServiceImpl userservice=new UserServiceImpl();
		System.out.println("BeforeEach Called");
		
	    }

	@Test
	@Order(1)
    @DisplayName("UserInfo Save")
	public void testSaveUser() {
		// Create a sample user
		User user = new User();
		user.setEmail("kumud2222@gmail.com");
		user.setFirstName("Kumud");
		user.setLastName("Vasu");
		user.setCountry("Germany");
		user.setPhoneNo("8768768768");
		user.setPassword("Nandini@1611");
		user.setUserType("Seller");
		user.setRole("NRG");

		// Mock the behavior of passwordEncoder
		when(passwordEncoder.encode(user.getPassword())).thenReturn("$2a$10$SPQp30pluepaLH6G3IOzvOVsxvoPEDnG4bAX.s9K6sx3f/qWlpER2");

		// Mock the behavior of userRepository
		when(userrepo.save(user)).thenReturn(user);

		// Call the method under test
		ResponseEntity<User> response = userserviceimpl.saveUser(user);

		// Verify the result
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
	}
	
	@Test
	@Order(2)
	@DisplayName("ChangeUserPassword")
    public void testChange_UserPassword() {
        // Arrange
        String email = "parthshrimali@gmail.com";
        String password = "Parth@9999";
        String encodedPassword = "encodedPassword";
        
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        
        // Act
        ResponseEntity<Map<String, String>> response = userserviceimpl.changeUserPassword(email, password);
        
        // Assert
        verify(userrepo).changePassword(email, encodedPassword);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
        Map<String, String> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("Change Password Successfullsy", responseBody.get("Change Password"));
    }
	
	@Test
	@DisplayName("TotalProfitTest")
	public void testTotalProfit() {
		when(userrepo.sumOfSpent()).thenReturn(603);
		
		int totalProfit = userserviceimpl.getTotalProfit();
		
		assertEquals(603,totalProfit);
	}
	

	@Test
	@DisplayName("TotalAdminTest")
	public void testTotalAdmin() {
	    when(userrepo.countAdmin()).thenReturn(1L);
	    ResponseEntity<Long> totalAdmin = userserviceimpl.getTotalAdmin();
	    Long actualCount = totalAdmin.getBody();

	    assertEquals(1L, actualCount, "Number of admin users should be 1");
	}
	
	@Test 
	@DisplayName("TotalEmail")
	public void testtotalEmail() {
		when(userrepo.countEmail()).thenReturn(25L);
		ResponseEntity<Long> totalEmailExist=userserviceimpl.countEmail();
		Long actualEmailcount=totalEmailExist.getBody();
		assertEquals(25L,actualEmailcount,"Email Should be 25");
	}
	
	@Test
	@DisplayName("TotalCustomer")
	public void testTotalCustomer() {
		when(userrepo.countCustomer()).thenReturn(16L);
		ResponseEntity<Long> totalCustomer=userserviceimpl.getTotalCustomer();
		Long CustomerTotal=totalCustomer.getBody();
		assertEquals(16L, CustomerTotal,"Total Customer should be 16");
	}
	
	@Test
	@DisplayName("TotalSeller")
	public void testTotalSeller() {
		when(userrepo.countSeller()).thenReturn(8L);
		ResponseEntity<Long> totalSeller=userserviceimpl.getTotalSeller();
		Long countTotalSeller=totalSeller.getBody();
		assertEquals(8,countTotalSeller,"Total Seller should be 8");
		
	}
	
	@Test
	@DisplayName("DeleteUser")
	public void testDeleteUser() {
		when(userrepo.findById(any())).thenReturn(Optional.of(new User()));
		boolean result = userserviceimpl.deleteUser(37L);
		assertTrue(result);
	}
	
	@Test
	@DisplayName("UserList")
	public void testUserList() {
		User user = new User();
		user.setEmail("kumud2222@gmail.com");
		user.setFirstName("Kumud");
		user.setLastName("Vasu");
		user.setCountry("Germany");
		user.setPhoneNo("8768768768");
		user.setPassword("Nandini@1611");
		user.setUserType("Seller");
		user.setRole("NRG");
		List<User> users = new ArrayList<>();
		users.add(user);
		Page<User> page = new PageImpl<>(users);
		
		when(userrepo.findAll(any(Pageable.class))).thenReturn(page);
		 Page<User> p = userserviceimpl.getAllUsers(1,3);
		 assertEquals(page, p);
	}
	
	@Test
	@DisplayName("SaveUser")
	public void testSaveUserInfo() {
		User user = new User();
		user.setEmail("Parag345@gmail.com");
		user.setFirstName("Parag");
		user.setLastName("Mehata");
		user.setCountry("india");
		user.setPhoneNo("9879876771");
		user.setPassword("Parag@123");
		user.setUserType("Seller");
		user.setRole("xyz");
		
		when(userrepo.save(user)).thenReturn(user);
		User users=userserviceimpl.getUser(user);
		assertEquals(user,users );
	}
	
//	@Test
//	@DisplayName("CheckUserEmail")
//	public void testEmailExist() throws Exception {
//		String email="Nandini.Gondaliya@einfochips.com";
//		when(userrepo.existsByEmail(email)).thenReturn(true);
//		ResponseEntity<Map<String, Boolean>> UserExistance=userserviceimpl.checkUsers(email);
//		Map<String,Boolean> response=UserExistance.getBody();
//		assertNotNull(response);
//	    assertTrue(response.containsKey("exists"));
//	    assertTrue(response.get("exists"));
//		assertEquals(HttpStatus.OK,UserExistance.getStatusCode());
//		
//	}
	
	@Test
	@DisplayName("CheckUserEmail")
	public void testEmailExist() throws Exception {
	    String email = "Nandini.Gondaliya@einfochips.com";
	    when(userrepo.existsByEmail(email)).thenReturn(true);

	    assertDoesNotThrow(() -> {
	        userserviceimpl.checkUsers(email);
	    });
	    
	    ResponseEntity<Map<String, Boolean>> userExistance=userserviceimpl.checkUsers(email);
	    Map<String,Boolean> response=userExistance.getBody();
	    assertEquals(HttpStatus.OK,userExistance.getStatusCode());
	}

	@Test
	@DisplayName("CheckUserEmail2")
	public void testEmailExistOrNot() {
		String email="kfjkdsfk233@gmail.com";
		when(userrepo.existsByEmail(email)).thenReturn(false);
		assertThrows(Exception.class,()->userserviceimpl.checkUsers(email));
	}
	
	@Test
	@DisplayName("CheckUserEmail3")
	public void testEmailExistence() throws EmailNotFoundExcaption{
		String emailId="Nandini.Gondaliya@einfochips.com";
		when(userrepo.existsByEmail(emailId)).thenReturn(true);
		
		ResponseEntity<Map<String,Boolean>> Emailexists=userserviceimpl.checkuseremail(emailId);
		Map<String,Boolean> exists=Emailexists.getBody();
		assertEquals(HttpStatus.OK, Emailexists.getStatusCode());
	}
	
	@Test
	@DisplayName("CheckUserEmail4")
	public void testEmailExist1() {
		String emailId="abcdefghijk1246@gmail.com";
		when(userrepo.existsByEmail(emailId)).thenReturn(false);
		assertThrows(Exception.class, () ->userserviceimpl.checkuseremail(emailId));
	}
	
	@Test
	@DisplayName("CheckEmailForForgetPasswordTest")
	public void testCheckEmailForForgetPassword() throws Exception {


	    String email = "test@example.com"; // Specify the desired email

	    // 2. Define the Test Method
	    when(userrepo.existsByEmail(email)).thenReturn(true);

	    // Mock the behavior of passwordEncoder, mailService, and any other dependencies if necessary

	    // 3. Mock the Repository and Dependencies
	    ResponseEntity<Map<String, Boolean>> responseEntity = userserviceimpl.checkEmailForForgetPassword(email);
	    Map<String, Boolean> response = responseEntity.getBody();

	    // 4. Invoke the Service Method
	    // The mocked repository will return true for existsByEmail

	    // 5. Verify the Response
	    assertNotNull(response);
	    assertTrue(response.containsKey("emailExist"));
	    assertTrue(response.get("emailExist"));
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	@DisplayName("checkUserEmail")
	public void testCheckUserEmail() throws Exception {
		String email="anyemail122@gmail.com";
		when(userrepo.existsByEmail(email)).thenReturn(false);
		
		ResponseEntity<Map<String,Boolean>> mailExsistance=userserviceimpl.checkEmailForForgetPassword(email);
		Map<String,Boolean> emailExist=mailExsistance.getBody();
		
		assertNotNull(emailExist);
		assertEquals(HttpStatus.BAD_REQUEST, mailExsistance.getStatusCode());
		
	}

	
	
	@Test
	@Order(1)
	@DisplayName("Addition Test")
	void testAddition() {
		
		assertEquals(6,userserviceimpl.add(5,1 ),"Your Answer should be 10" );
	}
	
	@Test
	@DisplayName("NullorNotNull")
	void testcheckNull() {
		
		String str1=null;
		String str2="Nandini";
		assertNull(userserviceimpl.checkNull(str1),"str1 should be null");
		assertNotNull(userserviceimpl.checkNull(str2), "Str2 should be not null");
	}
	
	@DisplayName("NotEqual")
	@Test
	void testStringNotEqual() {
		assertNotEquals(userserviceimpl.checksameString(),userserviceimpl.checkeequalString(),"Both String should not be same");
	}
	
	@DisplayName("Equal")
	@Test
	void testStringEqual() {
		String str="code";
		assertEquals(userserviceimpl.checkeequalString(),str,"Both String Should be same");
	}
	
	@DisplayName("Greater/Lesser")
	@Test
	void testIsGreater() {
		assertTrue(userserviceimpl.isGreater(10, 5),"first number should be greater");
		assertFalse(userserviceimpl.isGreater(1,5),"first elment should lesser");
	}
	
	@DisplayName("ArrayCheck")
	@Test
	void testArray() {
		String arr[]= {"a","b","c"};
		assertArrayEquals(arr, userserviceimpl.checkArray(),"Array should be [a,b,c]");
	}
	
	@DisplayName("IterableCheck")
	@Test
	void testListArray() {
		List<String> arrayList=List.of("code","einfochips","1611");
		assertIterableEquals(arrayList,userserviceimpl.checkArrayList(),"check String List Please");
	}

	@DisplayName("LineMatchCheck")
	@Test
	void testLineMatch() {
		List<String> listOfArray=List.of("one","two","three","four");
		assertLinesMatch(listOfArray, userserviceimpl.linematch(),"Line should match");
	}
	
	@DisplayName("Throw/Doesn't Throw")
	@Test
	void testThrowException() {
	assertThrows(Exception.class,()->{userserviceimpl.throwException(-9);},"should throw an Exception");
	assertDoesNotThrow(()->{userserviceimpl.throwException(9);},"Should not throw an exception");
	}
	
	@DisplayName("TimeoutCheck")
	@Test
	void testTimeout() {
		assertTimeoutPreemptively(Duration.ofSeconds(3),() ->{userserviceimpl.checkTimeout();});
	}
	
	@AfterEach
	void tearDown() throws Exception {
		System.out.println("AfterEach Called");
	}
	@AfterAll
	static void tearAllDown() throws Exception{
		System.out.println(".....................................................");
		System.out.println("Aftr Executing Test Cases");
		
	}
}
