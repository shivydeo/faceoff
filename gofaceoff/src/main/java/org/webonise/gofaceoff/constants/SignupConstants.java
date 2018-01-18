package org.webonise.gofaceoff.constants;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupConstants {
	
	@FindBy(xpath="//ul[contains(@class,'homeLoginRight')]//li[2]//a")
	public static WebElement signupButtonHomePage;
	
	@FindBy(xpath="//input[@name='user[first_name]']")
	public static WebElement inputFirstName;
	
	@FindBy(xpath="//input[@name='user[last_name]']")
	public static WebElement inputLastName;
	
	@FindBy(xpath="//input[@name='user[username]']")
	public static WebElement inputUsername;
	
	@FindBy(xpath="//input[@name='user[email]']")
	public static WebElement inputEmail;
	
	@FindBy(xpath="//input[@name='user[password]']")
	public static WebElement inputPassword;
	
	@FindBy(xpath="//input[@name='user[password_confirmation]']")
	public static WebElement inputPasswordRepeat;
	
	@FindBy(id="user_date_of_birth_3i")
	public static WebElement inputDOB_day;
	
	@FindBy(id="user_date_of_birth_2i")
	public static WebElement inputDOB_month;
	
	@FindBy(id="user_date_of_birth_1i")
	public static WebElement inputDOB_year;
	
	@FindBy(id="user_state_id")
	public static WebElement selectState;
	
	@FindBy(id="user_promo_code_info")
	public static WebElement inputPromoCode;
	
	@FindBy(xpath="//input[@name='user[have_reached_age_of_majority]']")
	public static WebElement ageConfirmationCheckbox;
	
	@FindBy(xpath="//li//input[@class='signUpButton']")
	public static WebElement signupButton;
	
	@FindBy(xpath="//a[contains(@class,'coinWrap')]//span[contains(@class,'chipsBalance')]")
	public static WebElement chipsBalance;
	
	@FindBy(xpath="//li[@class='activityBox']//p")
	public static WebElement joinedFaceoffMessage;
	
	//Mandatory fields error message xpaths
	@FindBy(xpath="//input[@id='user_first_name']/../following-sibling::span[1]")
	public static WebElement firstNameMandatory;
	
	@FindBy(xpath="//input[@id='user_last_name']/../following-sibling::span[1]")
	public static WebElement lastNameMandatory;
	
	@FindBy(xpath="//input[@id='user_username']/../following-sibling::span[1]")
	public static WebElement userNameMandatory;
	
	@FindBy(xpath="//input[@id='user_email']/../following-sibling::span[1]")
	public static WebElement emailMandatory;
	
	@FindBy(xpath="//input[@id='user_password']/../following-sibling::span[1]")
	public static WebElement passwordMandatory;
	
	@FindBy(xpath="//li[@class='inputList dateDropdown']//span")
	public static WebElement ageAbove18Mandatory;
	
	@FindBy(xpath="//li[@class='inputList stateDropdown']//span")
	public static WebElement selectStateMandatory;
	
	@FindBy(xpath="//input[@id='user_have_reached_age_of_majority']/../following-sibling::span[1]")
	public static WebElement acceptTCMandatory;
	
	
	
}
