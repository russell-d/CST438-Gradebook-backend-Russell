package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.context.SpringBootTest;


import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentRepository;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class EndToEndTestCreateAssignment {
	
	public static final String CHROME_DRIVER_FILE_LOCATION = "/Users/russelldevera/Downloads/chromedriver";

	public static final String URL = "http://localhost:3000";
	
	public static final String TEST_ASSIGNMENT_NAME = "Test Assignment";
	public static final int TEST_ASSIGNMENT_ID = 40443;
	public static final String TEST_ASSIGNMENT_COURSE_TITLE = "Test Course";
	public static final String TEST_ASSIGNMENT_DUE_DATE = "12-25-2022";
	public static final int SLEEP_DURATION = 1000; // 1 second.
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Test
	public void createAssignmentTest() throws Exception {
		
		Assignment check = null;
		
		do {
			check = assignmentRepository.findById(TEST_ASSIGNMENT_ID).get();
			
			if (check != null) {
				assignmentRepository.delete(check);	
			}
				
		} while (check != null);
		
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.xpath("//a[@id='addAssignment']")).click();
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.xpath("//input[@name='assignmentName']")).sendKeys(TEST_ASSIGNMENT_NAME);
			driver.findElement(By.xpath("//input[@name='courseTitle']")).sendKeys(TEST_ASSIGNMENT_COURSE_TITLE);
			driver.findElement(By.xpath("//input[@name='dueDate']")).sendKeys(TEST_ASSIGNMENT_DUE_DATE);
			driver.findElement(By.xpath("//input[@name='add']")).click();
			
			Thread.sleep(SLEEP_DURATION);
			
			String c = driver.findElement(By.xpath("//p[@id='message']")).getText();

			assertEquals("Assignment exists already.", c);
			
		} catch (Exception e) {
			throw e;
		} finally {
			Assignment a = assignmentRepository.findById(TEST_ASSIGNMENT_ID).get();
			
			if (a != null) {
				assignmentRepository.delete(a);
			}
			
			driver.quit();
		}
 		
	}
	
}
