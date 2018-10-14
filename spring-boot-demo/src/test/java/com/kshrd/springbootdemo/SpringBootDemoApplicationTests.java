package com.kshrd.springbootdemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kshrd.springbootdemo.model.User;
import com.kshrd.springbootdemo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
	@Autowired
	private UserService userService;
	
	@Test
	public void testUserNameNull() {
		List<User> users = userService.findAllUsers();		
		for (User user : users) {
			assertNotNull("User name cannot be empty. ID:" + user.getId(), user.getName());
		}
	}
	
	@Test
	public void testUserNameSize() {
		List<User> users = userService.findAllUsers();		
		for (User user : users) {
			assertTrue("User Name should be less then 30. User Name:" + user.getName(), user.getName().length() < 30);
		}
	}

	@Test
	public void testGender() {
		List<User> users = userService.findAllUsers();		
		for (User user : users) {
//			assertEquals('M', user.getGender()); 
			assertTrue("Gender should be M or F. User Name[" + user.getName() + "] Gender: [" +user.getGender() +"]", 
					user.getGender().equals("M")  || user.getGender().equals("F")  );
		}
	}

}
