package com.firebase.Bluebird3;

import com.firebase.Bluebird3.controller.FirebaseRead;
import com.firebase.Bluebird3.controller.FirebaseUpdate;
import com.firebase.Bluebird3.model.BBUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class Bluebird3ApplicationTests {

	@Autowired
	private FirebaseUpdate firebaseUpdate;

	@Autowired
	private FirebaseRead firebaseRead;

	@Test
	void contextLoads() {
	}


	@Test
	public void testCreateUser(){
		BBUser bbUser = CreateUser();
		String savedUserOrgId = firebaseUpdate.saveUser(bbUser);
		BBUser retrievedUser = firebaseRead.getUser(bbUser.getOrg_id());
		Assertions.assertEquals(savedUserOrgId, retrievedUser.getOrg_id());
		Assertions.assertEquals(bbUser.getEmail(), retrievedUser.getEmail());
	}

	@Test
	public void testSaveUser(){
		BBUser bbUser = CreateUser();
		bbUser.setNote("&&&");
		bbUser.setEmail("123@mail.to");
		String savedUserOrgId = firebaseUpdate.saveUser(bbUser);
		BBUser retrievedUser = firebaseRead.getUser(bbUser.getOrg_id());
		Assertions.assertEquals(savedUserOrgId, retrievedUser.getOrg_id());
		Assertions.assertEquals(bbUser.getEmail(), retrievedUser.getEmail());
		Assertions.assertEquals(bbUser.getNote(), retrievedUser.getNote());
	}

	@Test
	public void testCreateUserErrorMail(){
		BBUser bbUser = CreateUser();
		bbUser.setEmail(null);
		String savedUserOrgId = firebaseUpdate.saveUser(bbUser);
		Assertions.assertEquals(savedUserOrgId, "Error. Fill in all the required fields");
	}

	@Test
	public void testCreateUserErrorOrgId(){
		BBUser bbUser = CreateUser();
		bbUser.setOrg_id(null);
		String savedUserOrgId = firebaseUpdate.saveUser(bbUser);
		Assertions.assertEquals(savedUserOrgId, "Error. Fill in all the required fields");
	}

	@Test
	public void testCreateUserErrorRefs(){
		BBUser bbUser = CreateUser();
		bbUser.setReferences(null);
		String savedUserOrgId = firebaseUpdate.saveUser(bbUser);
		Assertions.assertEquals(savedUserOrgId, "Error in references");
	}

	@Test
	public void testSendMailNotification(){
		BBUser bbUser = CreateUser();
		String sendMail = "";
		sendMail = firebaseUpdate.sendEmailNotification(bbUser);
		Assertions.assertEquals(sendMail, "Your data has been successfully updated");
	}

	private static BBUser CreateUser() {
		BBUser bbUser = new BBUser();
		ArrayList<String> references = new ArrayList<>();
		references.add("id2");

		bbUser.setOrg_id("test_id");
		bbUser.setEmail("test@gmail.com");
		bbUser.setNote("****");
		bbUser.setAddress("Liberty av");
		bbUser.setNumberfield(1);
		bbUser.setReferences(references);


		return bbUser;
	}

}
