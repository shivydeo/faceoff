package org.webonise.gofaceoff.java;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.webonise.automation.core.WeboAutomation;
import org.webonise.gofaceoff.constants.AcceptChallengeConstants;
import org.webonise.gofaceoff.constants.LoginConstants;

import com.relevantcodes.extentreports.LogStatus;

public class AcceptChallenge extends WeboAutomation{
	
	@Test(dataProvider="xml")
	public static void acceptWorldChallenge(Integer iteration, Boolean expectedResult) throws Exception {
		try {
			updateTCData(iteration);
		 	HashMap gettcdata = new HashMap();
			PageFactory.initElements(driver, AcceptChallengeConstants.class);
			PageFactory.initElements(driver, LoginConstants.class);
		/*	weboActions.launchBrowser();
			
			globalMap.put("userName", "testuser1010");
			globalMap.put("password", "12345678");
			globalMap.put("challenger", "ian");
			globalMap.put("homeTeam", "CAMEROON");
			globalMap.put("awayTeam", "REPUBLIC OF CONGO");
			globalMap.put("market", "TOTAL SCORE");
			
			Login.login();*/
			boolean flag = false;
			int challengeCount=1;
			while(!flag && challengeCount<=AcceptChallengeConstants.openChallengeList.size()) {
				System.out.println("Challenge count:"+challengeCount);
				String challenger =	driver.findElement(By.xpath("(//div[@class='challengesList'])["+challengeCount+"]//div[@class='challengedDetails']")).getText();
				String challengerName = challenger.substring(15);
				if(challengerName.equalsIgnoreCase(globalMap.get("challenger"))) {
					System.out.println("Entered If block");
					//Get Home Team
					String home = driver.findElement(By.xpath("(//div[@class='challengesList'])["+challengeCount+"]//span[@class='homeTeam']")).getText();
					String tempHomeTeam[] = home.split("\\(");
					String homeTeam = tempHomeTeam[0].substring(0,tempHomeTeam[0].length() - 1);
					//Get Away Team
					String away = driver.findElement(By.xpath("(//div[@class='challengesList'])["+challengeCount+"]//span[@class='awayTeam']")).getText();
					String tempAwayTeam[] = away.split("\\(");
					String awayTeam = tempAwayTeam[0].substring(0,tempAwayTeam[0].length() - 1);
					//Get Market
					String market = driver.findElement(By.xpath("(//div[@class='challengesList'])["+challengeCount+"]//div[contains(@class,'marketWrap')]//span")).getText();
					if((homeTeam.equalsIgnoreCase(globalMap.get("homeTeam"))) && (awayTeam.equalsIgnoreCase(globalMap.get("awayTeam")))) {
						System.out.println("Entered second if block");
						if(market.equalsIgnoreCase(globalMap.get("market"))) {
							flag=true;
							WebElement acceptChallenge = driver.findElement(By.xpath("(//a[contains(text(),'Accept Challenge')])["+challengeCount+"]"));
							weboActions.click(acceptChallenge);
						}
					}
					++challengeCount;
				}
			
			}
			weboActions.switchToAlertAndAccept();
			
		}
		catch(Exception e) {
			WeboAutomation.extent.log(LogStatus.ERROR, "Failed Due To:: "+e.getMessage());
			weboActions.takeScreenShot();
			Assert.assertTrue(false);
		}
	}

}

//		System.out.println("Home Team: "+homeTeam+" length: "+homeTeam.length());
//		System.out.println(globalMap.get("homeTeam")+" length: "+globalMap.get("homeTeam").length());
//		System.out.println("Away Team: "+awayTeam+" length: "+awayTeam.length());
//		System.out.println(globalMap.get("awayTeam")+" length: "+globalMap.get("awayTeam").length());
//	System.out.println(market+" length: "+market.length());
//	System.out.println(globalMap.get("market")+" length: "+globalMap.get("market").length());