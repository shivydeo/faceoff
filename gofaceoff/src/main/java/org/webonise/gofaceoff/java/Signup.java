package org.webonise.gofaceoff.java;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.webonise.automation.core.CommonUtility;
import org.webonise.automation.core.WeboAutomation;
import org.webonise.gofaceoff.java.Login;
import org.webonise.gofaceoff.constants.LoginConstants;
import org.webonise.gofaceoff.constants.SignupConstants;
import org.webonise.gofaceoff.verificationPages.SignupVerification;

import com.relevantcodes.extentreports.LogStatus;

public class Signup extends WeboAutomation{
	
	List<String> illegalStates = Arrays.asList("Arizona","Illinois","Indiana","Iowa","Louisiana","Montana","North Dakota","Tennessee","Utah","Washington");
	String dob_day = "7";
	String dob_month = "March";
	String dob_year = "1990";
	
	public String selectStateFromDropdown(List<WebElement> stateList) {
		int stateCount = stateList.size();
		Random randomState = new Random();
		int stateNo = randomState.nextInt(stateCount) + 2;
		String stateValue = driver.findElement(By.xpath("//select[@id='user_state_id']//option["+stateNo+"]")).getText();
		return stateValue;
		
	}
	
	@Test(dataProvider="xml")
	public void verifySignupFunctionality(Integer iteration, Boolean expectedResult) throws Exception{
		try{
			updateTCData(iteration);
			HashMap gettcdata = new HashMap();
			PageFactory.initElements(driver, SignupConstants.class);
			PageFactory.initElements(driver, LoginConstants.class);
			weboActions.launchBrowser();
			weboActions.click(SignupConstants.signupButtonHomePage);
			
			//Generating input fields
			String firstName = CommonUtility.getNames();
			String lastName = CommonUtility.getNames();
			String userName = firstName;
			String emailAddress = CommonUtility.getEmailAddress();
			String password = CommonUtility.getPassword();
			
			//Storing fields into globalMap 
			globalMap.put("firstName", firstName);
			globalMap.put("lastName", lastName);
			globalMap.put("userName", userName);
			globalMap.put("emailAddress", emailAddress);
			globalMap.put("password", password);
			
			//Clicking on Sign up button without filling any fields & then verifying if mandatory fields when left blank produce an error 
			weboActions.click(SignupConstants.signupButton);
			SignupVerification.verifyMandatoryFields();
			
			//Checking that drop-down list of states does not contain names of states where betting is illegal
			List<WebElement> stateList = driver.findElements(By.xpath("//select[@id='user_state_id']//option"));
			SignupVerification.verifyStateNames(illegalStates, stateList);
			
			//Filling up sign-up form
			weboActions.sendKeys(SignupConstants.inputFirstName, firstName);
			weboActions.sendKeys(SignupConstants.inputLastName, lastName);
			weboActions.sendKeys(SignupConstants.inputUsername, userName);
			weboActions.sendKeys(SignupConstants.inputEmail,emailAddress);
			weboActions.sendKeys(SignupConstants.inputPassword, password);
			weboActions.sendKeys(SignupConstants.inputPasswordRepeat, password);
			weboActions.selectByValue(SignupConstants.inputDOB_day, dob_day);
			weboActions.selectbyVisibilityOfText(SignupConstants.inputDOB_month, dob_month);
			weboActions.selectByValue(SignupConstants.inputDOB_year, dob_year);
			weboActions.selectbyVisibilityOfText(SignupConstants.selectState,selectStateFromDropdown(stateList));
			weboActions.click(SignupConstants.ageConfirmationCheckbox);
			weboActions.click(SignupConstants.signupButton);
		
			//Logout of account 
			weboActions.click(LoginConstants.profileDropdown);
			weboActions.click(LoginConstants.logoutButton);
			
			//Sign in with newly registered account
			Login.login();
			
			//Check amount of initial chips awarded
			SignupVerification.verifyIntitialChipsAmount();
			
			//Check if message "+username+ joined Faceoff" exists in Activity Box
			SignupVerification.verifyActivityBoxMessage();
			
			
		}catch(Exception e){
			WeboAutomation.extent.log(LogStatus.ERROR, "Failed Due To:: "+e.getMessage());
			weboActions.takeScreenShot();
			Assert.assertTrue(false);
		}
	}
}
