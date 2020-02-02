package com.example.demo.kindergarden;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.management.BadAttributeValueExpException;

import com.example.demo.kindergarden.model.ChildrenModel;
import com.example.demo.kindergarden.model.KindergardenModel;
import com.example.demo.kindergarden.repos.ChildrenModelRepository;
import com.example.demo.kindergarden.repos.KindergardenRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
class KindergardenQueueApplicationTests {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ChildrenModelRepository childrenModelRepository;
	@Autowired
	private KindergardenRepository kindergardenRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void createChildrenTest() throws BadAttributeValueExpException, ParseException {
		   // given
		   
		   String pattern = "dd-MM-yyyy";
		   SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		   ChildrenModel cm1 = new ChildrenModel("Test", "Testy", "000000-00000", false, simpleDateFormat.parse("12-01-1922"));

		   entityManager.persist(cm1);
		   entityManager.flush();
		
		   // when
		   ChildrenModel found = childrenModelRepository.findById(cm1.getIdentityCode()).get();
		
		   // then
			assertEquals(cm1.getIdentityCode(), found.getIdentityCode(), "Created entry Id matches provided ID");
	}
	@Test
	void createChildrenBadAttributeTest(){

		   String pattern = "dd-MM-yyyy";
		   SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		   Assertions.assertThrows(BadAttributeValueExpException.class, ()->new ChildrenModel("Test", "Testy", "0000000-000000", false, simpleDateFormat.parse("12-01-1922")));

	}
	@Test
	void createChildrenParseException(){

		   String pattern = "dd-MM-yyyy";
		   SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		   Assertions.assertThrows(ParseException.class, ()->new ChildrenModel("Test", "Testy", "000000-00000", false, simpleDateFormat.parse("Kāposts")));

	}

	@Test
	void createKindergardenTest() {
		   // given
		   KindergardenModel km1 = new KindergardenModel("Test kindergarden", "Test address for test kindergarden", new ArrayList<ChildrenModel>() );

		   entityManager.persist(km1);
		   entityManager.flush();
		
		   // when
		   KindergardenModel found = kindergardenRepository.findById(km1.getId()).get();
		
		   // then
			assertEquals(km1.getName(), found.getName(), "Created kindergarden Name matches provided kindergarden name");
	}

}
