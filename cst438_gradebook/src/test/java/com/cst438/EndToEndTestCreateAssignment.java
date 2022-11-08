package com.cst438;

import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.domain.AssignmentRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class EndToEndTestCreateAssignment {
	
	public static final String CHROME_DRIVER_FILE_LOCATION = "C:/Users/rdevera/Downloads/chromedriver";

	public static final String URL = "http://localhost:3000";
	
	public static final String TEST_ASSIGNMENT_NAME = "Test Assignment";
	public static final String TEST_ASSIGNMENT_DUE_DATE = "12-25-2022";
	public static final int SLEEP_DURATION = 1000; // 1 second.
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Test
	public void createAssignmentTest() throws Exception {
		
	}
	
}
