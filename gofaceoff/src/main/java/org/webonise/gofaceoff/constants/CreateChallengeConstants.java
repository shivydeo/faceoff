package org.webonise.gofaceoff.constants;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateChallengeConstants {
	
	@FindBy(xpath="//a[contains(text(),'My Challenges')]")
	public static WebElement myChallengesTab;
	
	@FindBy(xpath="(//div[contains(@class,'friendChallenged')])[1]")
	public static WebElement pendingChallenge;
	
	@FindBy(xpath="(//div[contains(@class,'friendChallenged')])[1]//div[contains(@class,'competitionPartication')]")
	public static WebElement pendingChallengeTitle;
	
	@FindBy(xpath="(//div[contains(@class,'friendChallenged')])[1]//div[contains(@class,'market')]")
	public static WebElement pendingChallengeMarket;
	
	@FindBy(xpath="//span[contains(@class,'statusButton')]")
	public static WebElement pendingChallengeStatus;
	
	@FindBy(xpath="(//div[contains(@class,'challengeBtnWrap')]//a)[1]")
	public static WebElement firstChallenge;
	
	@FindBy(xpath="(//div[@class='wrapperDiv']//div)[1]//li[contains(@class,'contenderOne')]//span")
	public static WebElement firstChallengeHomeTeam;
	
	@FindBy(xpath="(//div[@class='wrapperDiv']//div)[1]//li[contains(@class,'contenderTwo')]//span")
	public static WebElement firstChallengeAwayTeam;
	
	@FindBy(xpath="(//div[@class='challengerContainer'])[1]//li//h2")
	public static WebElement firstChallengeFirstMarket;
	
	@FindBy(xpath="(//div[@class='catchHandicap'])[1]//span[@class='betCatch']")
	public static WebElement firstChallengeFirstOutcome;
	
	@FindBy(xpath="(//input[contains(@class,'stakeInput')])[1]")
	public static WebElement firstStakebox;
	
	@FindBy(xpath="//div[@class='error-container']//h3[text()='Your stake should be greater than 0']")
	public static WebElement stakeErrorMessage;
	
	@FindBy(xpath="(//div[contains(@class,'challengeFriend')])[1]")
	public static WebElement firstChallengeFriendButton;
	
	@FindBy(xpath="(//a[@class='js-open-challenge'])[1]")
	public static WebElement firstChallengeWorldButton;
	
	@FindBy(xpath="(//div[@class='search'])[1]//input[@id='search_people_input']")
	public static WebElement firstSearchBox;
	
	@FindBy(xpath="(//li[@class='friendSearchResult'])[1]//span[@class='userName']")
	public static WebElement firstSearchResult;
	
	@FindBy(xpath="//button[contains(text(),'CHALLENGE A FRIEND')]")
	public static WebElement popupChallengeFriendButton;
	
	@FindBy(xpath="//button[@class='challengeWorldBtn challengeConfirm']")
	public static WebElement popupChallengeWorldButton;
	
	@FindBy(xpath="//h3[contains(text(),'AHHHH YEAH!!')]")
	public static WebElement challengeCreatedPopup;
	
	@FindBy(xpath="(//button[@class='close'])[2]")
	public static WebElement closePopup;
	
}
