package org.webonise.gofaceoff.java;

import java.util.HashMap;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.webonise.automation.core.WeboAutomation;
import org.webonise.gofaceoff.constants.LoginConstants;
import com.relevantcodes.extentreports.LogStatus;
import org.webonise.gofaceoff.verificationPages.LoginVerification;

public class Login extends WeboAutomation{
	
	public static void login() {
		System.out.println("Entered Login");
		weboActions.click(LoginConstants.loginButton);
		weboActions.sendKeys(LoginConstants.inputUsernameorEmail, globalMap.get("userName"));
		weboActions.sendKeys(LoginConstants.inputPassword, globalMap.get("password"));
		weboActions.click(LoginConstants.signIn);
	}
	
	@Test(dataProvider="xml")
	public void validEmailLogin(Integer iteration, Boolean expectedResult) throws Exception{
		try{
			updateTCData(iteration);
			HashMap gettcdata = new HashMap();
			PageFactory.initElements(driver, LoginConstants.class);
			weboActions.launchBrowser();
			weboActions.click(LoginConstants.loginButton);
			
			LoginVerification.verifyLoginAppearance();
			weboActions.sendKeys(LoginConstants.inputUsernameorEmail, globalMap.get("emailAddress"));
			weboActions.sendKeys(LoginConstants.inputPassword, globalMap.get("password"));
			weboActions.click(LoginConstants.signIn);
			
			weboActions.click(LoginConstants.profileDropdown);
			weboActions.click(LoginConstants.logoutButton);
			
		}catch(Exception e){
			WeboAutomation.extent.log(LogStatus.ERROR, "Failed Due To:: "+e.getMessage());
			weboActions.takeScreenShot();
			Assert.assertTrue(false);
		}
	}
	
	@Test(dataProvider="xml")
	public void validUsernameLogin(Integer iteration, Boolean expectedResult) throws Exception{
		try{
			updateTCData(iteration);
			HashMap gettcdata = new HashMap();
			PageFactory.initElements(driver, LoginConstants.class);
			weboActions.click(LoginConstants.loginButton);
			
			weboActions.sendKeys(LoginConstants.inputUsernameorEmail, globalMap.get("userName"));
			weboActions.sendKeys(LoginConstants.inputPassword, globalMap.get("password"));
			weboActions.click(LoginConstants.signIn);
			
			weboActions.click(LoginConstants.profileDropdown);
			weboActions.click(LoginConstants.logoutButton);
			
		}catch(Exception e){
			WeboAutomation.extent.log(LogStatus.ERROR, "Failed Due To:: "+e.getMessage());
			weboActions.takeScreenShot();
			Assert.assertTrue(false);
		}
	}
	
	@Test(dataProvider="xml")
	public void blankLoginCredentials(Integer iteration, Boolean expectedResult) {
		try{
			updateTCData(iteration);
			HashMap gettcdata = new HashMap();
			PageFactory.initElements(driver, LoginConstants.class);
			weboActions.click(LoginConstants.loginButton);
			
			weboActions.click(LoginConstants.signIn);
			Thread.sleep(3000);
			LoginVerification.verifyInvalidErrorMessage();
			
		}catch(Exception e){
			WeboAutomation.extent.log(LogStatus.ERROR, "Failed Due To:: "+e.getMessage());
			weboActions.takeScreenShot();
			Assert.assertTrue(false);
		}
	}
	
	@Test(dataProvider="xml")
	public void invalidLogin(Integer iteration, Boolean expectedResult) throws Exception {
		try{
			updateTCData(iteration);
			HashMap gettcdata = new HashMap();
			PageFactory.initElements(driver, LoginConstants.class);
			weboActions.click(LoginConstants.loginButton);
			
			weboActions.sendKeys(LoginConstants.inputUsernameorEmail, LoginConstants.invalidUsername);
			weboActions.sendKeys(LoginConstants.inputPassword, LoginConstants.invalidPassword);
			weboActions.click(LoginConstants.signIn);
			
			LoginVerification.verifyInvalidErrorMessage();
			
		}catch(Exception e){
			WeboAutomation.extent.log(LogStatus.ERROR, "Failed Due To:: "+e.getMessage());
			weboActions.takeScreenShot();
			Assert.assertTrue(false);
		}
	}
	
	@Test(dataProvider="xml")
	public void isPasswordMasked(Integer iteration, Boolean expectedResult)  throws Exception{ //To verify that the password is masked while entering,i.e, doesn't appear as plain text
		try{
			updateTCData(iteration);
			HashMap gettcdata = new HashMap();
			PageFactory.initElements(driver, LoginConstants.class);
			weboActions.click(LoginConstants.loginButton);
			
			LoginVerification.verifyPasswordMasked();
			
		}catch(Exception e){
			WeboAutomation.extent.log(LogStatus.ERROR, "Failed Due To:: "+e.getMessage());
			weboActions.takeScreenShot();
			Assert.assertTrue(false);
		}
	}
	
	@Test(dataProvider="xml")
	public void loginThenClickBackLink(Integer iteration, Boolean expectedResult) throws Exception {
		try{
			updateTCData(iteration);
			HashMap gettcdata = new HashMap();
			PageFactory.initElements(driver, LoginConstants.class);
			weboActions.launchBrowser();
			weboActions.click(LoginConstants.loginButton);
			
		//	weboActions.sendKeys(LoginConstants.inputUsername, LoginConstants.validEmail);
		//	weboActions.sendKeys(LoginConstants.inputPassword, LoginConstants.validPassword);
			weboActions.click(LoginConstants.signIn);
			
			driver.navigate().back();
			weboActions.click(LoginConstants.loginButton);
			
			weboActions.chkVisible(LoginConstants.profileDropdown);
			
			
		}catch(Exception e){
			WeboAutomation.extent.log(LogStatus.ERROR, "Failed Due To:: "+e.getMessage());
			weboActions.takeScreenShot();
			Assert.assertTrue(false);
		}
		
	}
}
