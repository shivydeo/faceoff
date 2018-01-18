package org.webonise.automation.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.function.Function;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtility extends WeboAutomation {

    @FindBy(xpath = "//a[@id='account-chooser-link']")
    static
    WebElement signInWithDifferentAccount;

    @FindBy(xpath = "//a[@id='account-chooser-add-account']")
    static
    WebElement addAccount;

    @FindBy(xpath = "//button[@value='email@crucibleapp.com']")
    static
    WebElement clickOnDemoAccount;

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchMail;

    @FindBy(xpath = "//a[contains(@title,'Google Account')]")
    WebElement userLogout;

    @FindBy(xpath = "//a[text()='Sign out']")
    WebElement signOut;

    public static void gmailVerification(String username, String password, String searchName) throws InterruptedException {
        try {
            weboActions.launchBrowser("http://gmail.com");
            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(driver, 40);
            if (driver.findElements(By.xpath("//a[text()='Sign In']")).size() > 0) {
                driver.findElement(By.xpath("//a[text()='Sign In']")).click();
                Thread.sleep(2000);
            }
            if (driver.findElements(By.xpath("//a[@id='gmail-sign-in']")).size() != 0) {
                driver.findElement(By.xpath("//a[@id='gmail-sign-in']")).click();
                Thread.sleep(2000);
                driver.findElement(By.id("Email")).sendKeys(username);
                Thread.sleep(2000);
                driver.findElement(By.id("next")).click();
                Thread.sleep(2000);
            } else if (driver.findElements(By.xpath("//a[@id='account-chooser-link']")).size() != 0) {
                Thread.sleep(2000);
                signInWithDifferentAccount.click();
                Thread.sleep(2000);
                addAccount.click();
                Thread.sleep(2000);
                driver.findElement(By.id("Email")).sendKeys(username);
                Thread.sleep(2000);
                driver.findElement(By.id("next")).click();
                Thread.sleep(2000);
            } else if (driver.findElements(By.xpath("//button[@value='email@crucibleapp.com']")).size() != 0) {
                Thread.sleep(2000);
                clickOnDemoAccount.click();
                Thread.sleep(2000);
            } else if (driver.findElements(By.xpath("//input[@id='Email' and @type='email']")).size() != 0) {
                Thread.sleep(2000);
                driver.findElement(By.id("Email")).sendKeys(username);
                Thread.sleep(2000);
                driver.findElement(By.id("next")).click();
                Thread.sleep(2000);
            }
            if (driver.findElements(By.id("Passwd")).size() != 0) {
                driver.findElement(By.id("Passwd")).sendKeys(password);
                Thread.sleep(2000);
                driver.findElement(By.id("signIn")).click();
                Thread.sleep(5000);
            }
            Thread.sleep(5000);
            System.out.println("waiting for searchbox");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gbqfq")));
            System.out.println("searchbox visible");
            Thread.sleep(5000);
            //wait.until(ExpectedConditions.elementToBeClickable(By.id("gbqfb")));
            driver.findElement(By.id("gbqfq")).sendKeys(searchName);
            Thread.sleep(5000);
            wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='gbqfb']"))));
            driver.findElement(By.xpath("//button[@class='gbqfb']")).click();
            Thread.sleep(2000);
            wait.until((ExpectedConditions.elementToBeClickable(By.xpath(".//div[5]/div/div/table/tbody/tr/td[6]"))));
            driver.findElement(By.xpath(".//div[5]/div/div/table/tbody/tr/td[6]")).click();
            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String dateFormat(String use) {
        String formattedDate = null;
        DateFormat dateFormat;
        Date date;
        switch (use) {
            case "username":
                dateFormat = new SimpleDateFormat("dd_HH_mm_ss");
                date = new Date();
                formattedDate = dateFormat.format(date);
                break;
            case "ss":
                dateFormat = new SimpleDateFormat("EEE_MMM_d_yyyy_HH_mm_ss");
                date = new Date();
                formattedDate = dateFormat.format(date);
                break;
            case "date":
                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                date = new Date();
                formattedDate = dateFormat.format(date);
                break;
        }
        return formattedDate;
    }

    public static String getEmailAddress() {
        String emailAddress;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmssSS");
        emailAddress = "weboniseQA_" + sdf.format(date) + "@weboapps.com";
        return emailAddress;
    }

    public static String getPassword() {
        String password;
        password = "weboniseQA-" + randomNumericValueGenerate(99999);
        return password;
    }

    public static String getNames() {
        String name;
        name = "webonise" + RandomStringUtils.random(5, "abcdefghijklmnopqrstuvwxyz");
        return name;
    }

    public static String getAddress() {
        String name;
        name = "Weboniselab," + RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz") + "," + RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz");
        return name;
    }

    public static int randomNumericValueGenerate(int length) {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(length);
        return randomInt;
    }

    public static String generateRandomChars(int length) {
        String random = RandomStringUtils.random(length);
        return random;
    }

    public static String getRandomNumber(int digCount) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(digCount);
        for (int i = 0; i < digCount; i++)
            sb.append((char) ('0' + rnd.nextInt(10)));
        return sb.toString();
    }

    public static String[] wrongEmailInputs() {
        String wrongInputEmails[] = {"T3$t%%gmail.com", " email.test@gmail.com", "test.mail", "test email@gmail.com", "testmailgmail.com", "@gmail.com", "testmail@@gmail.com",/*"test.mail@-gmail.com","#$%^%^..&%#^&*@gmail.com",*/"test.mail@gmail..com"};
        return wrongInputEmails;
    }

    public static void selectAnyParticularDate(String dateToSelect, String monthToSelect, String yearToSelect) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        SimpleDateFormat ft = new SimpleDateFormat();
        Date date = new Date();
        Calendar calender = Calendar.getInstance();
        int currentdate = calender.get(Calendar.DATE);
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        String presentDate = ft.format(date);
        String presentYear = String.valueOf(year);
        String presentMonth = String.valueOf(month);
    }

    public static int getCurrentYear() {
        Calendar calender = Calendar.getInstance();
        int currentdate = calender.get(Calendar.DATE);
        int year = calender.get(Calendar.YEAR);
        return year;
    }

}
