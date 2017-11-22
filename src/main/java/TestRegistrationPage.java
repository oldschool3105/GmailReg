import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class TestRegistrationPage {

    private WebDriver driver;


    @Before
    public void startDriver() {
        System.setProperty("webdriver.gecko.driver", MainClass.getGeckoDriverPath());
        DesiredCapabilities capabilities=DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver= new FirefoxDriver();
    }

    @After
    public void quitDriver() {
        driver.quit();
    }



    @Test
    public void pageTitle(){
        driver.get("https://accounts.google.com/SignUp");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        WebElement pageTitle = driver.findElement(By.tagName("h1"));
        pageTitle.isDisplayed();
    }

    @Test
    public void PassRegistration()  {
        driver.get("https://accounts.google.com/SignUp"); //переходим по ссылке

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys("Misha");

        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys("Bidnyj");

        WebElement gmailAdress = driver.findElement(By.id("GmailAddress"));
        gmailAdress.sendKeys("sdhfljsdlfksjdhfk");

        WebElement password = driver.findElement(By.id("Passwd"));
        password.sendKeys("misha1234");

        WebElement passRep = driver.findElement(By.id("PasswdAgain"));
        passRep.sendKeys("misha1234");

        WebElement mounth = driver.findElement(By.xpath("//*[@id=\"BirthMonth\"]/div[1]"));
        mounth.click();
        mounth.sendKeys(Keys.ARROW_DOWN);
        mounth.sendKeys(Keys.ENTER);

        WebElement dayBith = driver.findElement(By.id("BirthDay"));
        dayBith.sendKeys("9");

        WebElement yearBith = driver.findElement(By.id("BirthYear"));
        yearBith.sendKeys("1995");

        WebElement gender = driver.findElement(By.xpath("//*[@id=\"Gender\"]/div[1]"));
        gender.click();
        gender.sendKeys(Keys.ARROW_DOWN);
        gender.sendKeys(Keys.ENTER);

        WebElement phoneNumber = driver.findElement(By.id("RecoveryPhoneNumber"));
        phoneNumber.sendKeys("991812099");

        WebElement buttonNext = driver.findElement(By.id("submitbutton"));
        buttonNext.click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

        WebElement termOfUse = driver.findElement(By.id("tos-scroll"));

    }

    @Test
    public void IncorrectLengtsEmail() {
        driver.get("https://accounts.google.com/SignUp"); //переходим по ссылке

        WebElement gmailAdress = driver.findElement(By.id("GmailAddress"));
        gmailAdress.sendKeys("wev");
        WebElement password = driver.findElement(By.id("Passwd"));
        password.click();

        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

        WebElement errorText = driver.findElement(By.xpath("//*[text()='Please use between 6 and 30 characters.']"));
        errorText.getText();
        System.out.println(errorText.getText());

    }

    //попытка зарегестрировать уже существующий адрес
    @Test
    public void AddedEmail() {
        driver.get("https://accounts.google.com/SignUp"); //переходим по ссылке

        WebElement gmailAdressLengt = driver.findElement(By.id("GmailAddress"));
        gmailAdressLengt.sendKeys("testaccount");
        WebElement password = driver.findElement(By.id("Passwd"));
        password.click();

        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

        WebElement errorTexting = driver.findElement(By.xpath("//*[@id=\"errormsg_0_GmailAddress\"]"));
        errorTexting.getText();
        System.out.println(errorTexting.getText());
    }

    @Test
    public void EmptyField(){
        driver.get("https://accounts.google.com/SignUp"); //переходим по ссылке

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys("");
        WebElement password = driver.findElement(By.id("Passwd"));
        password.click();

        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

        WebElement errorTexting = driver.findElement(By.xpath("//*[text()='You can't leave this empty.']"));
        errorTexting.getText();
    }



}
