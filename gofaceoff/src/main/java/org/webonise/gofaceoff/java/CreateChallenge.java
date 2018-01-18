package org.webonise.gofaceoff.java;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.webonise.automation.core.WeboAutomation;
import org.webonise.gofaceoff.constants.LoginConstants;
import org.webonise.gofaceoff.constants.CreateChallengeConstants;
import org.webonise.gofaceoff.verificationPages.CreateChallengeVerfication;
import org.webonise.gofaceoff.java.Login;
import org.webonise.gofaceoff.java.AcceptChallenge;
import com.relevantcodes.extentreports.LogStatus;

public class CreateChallenge extends WeboAutomation {
	
	public static String friendUserName = "testuser1010";
	
	@Test(dataProvider="xml")
	public static void challengeWorld(Integer iteration, Boolean expectedResult) throws Exception {
		try {
			updateTCData(iteration);
			HashMap gettcdata = new HashMap();
			PageFactory.initElements(driver, LoginConstants.class);
			PageFactory.initElements(driver, CreateChallengeConstants.class);
			weboActions.launchBrowser();
			
			//Login 
			globalMap.put("userName", "ian");
			globalMap.put("password", "12345678");
			Login.login();
			
			//Click on first challenge on soccer page and challenge the world
			driver.findElement(By.xpath("(//a[@href='/categories/soccer'])[2]")).click();
			weboActions.click(CreateChallengeConstants.firstChallenge);

			//Save challenge details into global map
			globalMap.put("status", "WAITING!");
			globalMap.put("homeTeam", CreateChallengeConstants.firstChallengeHomeTeam.getText());
			globalMap.put("awayTeam", CreateChallengeConstants.firstChallengeAwayTeam.getText());
			String tempMarket = CreateChallengeConstants.firstChallengeFirstMarket.getText();
			String market = tempMarket.substring(tempMarket.indexOf("(") +1, tempMarket.indexOf(")"));
			if(market.equalsIgnoreCase("total goals")) {
				market = "TOTAL SCORE";
			}
			globalMap.put("market", market);
			globalMap.put("outcome", CreateChallengeConstants.firstChallengeFirstOutcome.getText());
			globalMap.put("challenger", globalMap.get("userName"));
			
			
			//Try to challenge the world without raising stake
			weboActions.click(CreateChallengeConstants.firstChallengeWorldButton);
			weboActions.click(CreateChallengeConstants.popupChallengeWorldButton);
			CreateChallengeVerfication.verifyStakeZeroError();
			
			
			//Create world challenge
			weboActions.sendKeys(CreateChallengeConstants.firstStakebox, "2");
			weboActions.click(CreateChallengeConstants.firstChallengeWorldButton);
			weboActions.click(CreateChallengeConstants.popupChallengeWorldButton);
			CreateChallengeVerfication.verifyErrorPopupIsNotPresent();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//button[@class='close'])[1]")).sendKeys(Keys.ESCAPE);
			Thread.sleep(3000);
			weboActions.click(LoginConstants.logo); 
		
			//Verify if open challenge is visible in pending challenges 
			 weboActions.click(CreateChallengeConstants.myChallengesTab);
			 String challengeTitle = CreateChallengeConstants.pendingChallengeTitle.getText();
			 String marketTitle = CreateChallengeConstants.pendingChallengeMarket.getText();
			 System.out.println(challengeTitle);
			 String status = CreateChallengeConstants.pendingChallengeStatus.getText();
			 String homeTeam = challengeTitle.substring(0, challengeTitle.indexOf("(") - 1);
			 String awayTeam = challengeTitle.substring(challengeTitle.indexOf("@") + 2, challengeTitle.length() - 4);
			 String challengeTo = driver.findElement(By.xpath("(//div[contains(@class,'friendChallenged')])[1]//div[contains(@class,'market')]//span")).getText();
			 String market1 = marketTitle.replace(challengeTo, "");
			 market1 = market1.substring(0,market1.length() - 1);
			 CreateChallengeVerfication.verifyOpenChallenge(homeTeam, awayTeam, market1,status);
			 
			//Logout
			weboActions.click(LoginConstants.profileDropdown);
			weboActions.click(LoginConstants.logoutButton);
			
			globalMap.put("userName", "testuser1010");
			Login.login();
			
			AcceptChallenge.acceptWorldChallenge(iteration, expectedResult); 
					
		}
		catch(Exception e) {
			WeboAutomation.extent.log(LogStatus.ERROR, "Failed Due To:: "+e.getMessage());
			weboActions.takeScreenShot();
			Assert.assertTrue(false);
		}
		
	}
	
}
