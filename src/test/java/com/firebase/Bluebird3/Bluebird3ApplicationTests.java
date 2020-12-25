package com.firebase.Bluebird3;

import com.firebase.Bluebird3.controller.FirebaseController;
import com.firebase.Bluebird3.model.BBUser;
import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class Bluebird3ApplicationTests {

	@Autowired
	private FirebaseController firebaseController;

	@Test
	void contextLoads() {
	}


	@Test
	public void testCreateUser(){
		BBUser bbUser = CreateUser();
		String savedUserOrgId = firebaseController.saveUser(bbUser);
		BBUser retrievedUser = firebaseController.getUser(bbUser.getOrg_id());
		Assertions.assertEquals(savedUserOrgId, retrievedUser.getOrg_id());
		Assertions.assertEquals(bbUser.getEmail(), retrievedUser.getEmail());
	}

	@Test
	public void testSaveUser(){
		BBUser bbUser = CreateUser();
		bbUser.setNote("&&&");
		bbUser.setEmail("123@mail.to");
		String savedUserOrgId = firebaseController.saveUser(bbUser);
		BBUser retrievedUser = firebaseController.getUser(bbUser.getOrg_id());
		Assertions.assertEquals(savedUserOrgId, retrievedUser.getOrg_id());
		Assertions.assertEquals(bbUser.getEmail(), retrievedUser.getEmail());
		Assertions.assertEquals(bbUser.getNote(), retrievedUser.getNote());
	}

	@Test
	public void testCreateUserErrorMail(){
		BBUser bbUser = CreateUser();
		bbUser.setEmail(null);
		String savedUserOrgId = firebaseController.saveUser(bbUser);
		Assertions.assertEquals(savedUserOrgId, "Error. Fill in all the required fields");
	}

	@Test
	public void testCreateUserErrorOrgId(){
		BBUser bbUser = CreateUser();
		bbUser.setOrg_id(null);
		String savedUserOrgId = firebaseController.saveUser(bbUser);
		Assertions.assertEquals(savedUserOrgId, "Error. Fill in all the required fields");
	}

	private static BBUser CreateUser() {
		BBUser bbUser = new BBUser();
		bbUser.setOrg_id("test_id");
		bbUser.setEmail("test@gmail.com");
		bbUser.setNote("****");
		bbUser.setAddress("Liberty av");
		bbUser.setNumberfield(1);
		return bbUser;
	}

}
