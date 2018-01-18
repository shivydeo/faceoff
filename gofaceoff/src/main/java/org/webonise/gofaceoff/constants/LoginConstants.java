package org.webonise.gofaceoff.constants;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginConstants {

	
	//Invalid credentials
	public static final String invalidUsername = "username";
	public static final String invalidPassword = "password";
	
	@FindBy(xpath="(//*[@class='logo'])[1]")
	public static WebElement logo;
	
	@FindBy(xpath="//ul[contains(@class,'homeLoginRight')]//li[1]//a")
	public static WebElement loginButton;
	
	@FindBy(xpath="//div[@id='profileDropdown']")
	public static WebElement profileDropdown;
	
	@FindBy(xpath="//ul[contains(@class,'profileMenu')]//li//a[text()='Logout']")
	public static WebElement logoutButton;
	
	@FindBy(xpath="(//li[@class='inputList'])[1]//input")
	public static WebElement firstInputBox;
	
	@FindBy(xpath="//input[@id='login']")
	public static WebElement inputUsernameorEmail;
	
	@FindBy(xpath="//input[@id='password']")
	public static WebElement inputPassword;
	
	@FindBy(xpath="//input[@class='loginButton']")
	public static WebElement signIn;
	
	@FindBy(xpath="//span[contains(text(),'INVALID')]")
	public static WebElement invalidLoginError;
}
