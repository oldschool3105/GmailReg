import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.text;

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


    // проверка наличия тайтла
    @Test
    public void pageTitle(){
        driver.get("https://accounts.google.com/SignUp");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        WebElement pageTitle = driver.findElement(By.tagName("h1"));
        pageTitle.isDisplayed();
    }


    // корректно заполненые поля
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


    // попытка зарегистрировать эмейл с неправильной длинной
    @Test
    public void IncorrectLengtsEmail() {
        driver.get("https://accounts.google.com/SignUp"); //переходим по ссылке

        WebElement gmailAdress = driver.findElement(By.id("GmailAddress"));
        gmailAdress.sendKeys("wev");
        WebElement password = driver.findElement(By.id("Passwd"));
        password.click();

        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

        WebElement errorText = driver.findElement(By.xpath("//*[@id=\"errormsg_0_GmailAddress\"]"));
        errorText.getText();
    }



// не заполненое поле имя
    @Test
    public void EmptyField(){
        driver.get("https://accounts.google.com/SignUp"); //переходим по ссылке

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys("");
        WebElement password = driver.findElement(By.id("Passwd"));
        password.click();

        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

        WebElement errorTexting = driver.findElement(By.xpath("//*[@id=\"errormsg_0_FirstName\"]"));
        errorTexting.getText();
    }


    // Сравнение тайтла страницы
    @Test
    public void TextTitle(){
        driver.get("https://accounts.google.com/SignUp"); //переходим по ссылке
        Assert.assertEquals(driver.getTitle(), "Зарегистрируйтесь в Google");
    }

    // Переход на страницу "Использовать текущий адрес электронной почты"
    @Test
    public void CreatedEmailPage(){
        driver.get("https://accounts.google.com/SignUp");
        WebElement createLink = driver.findElement(By.linkText("Использовать текущий адрес эл. почты"));
        createLink.getText();
    }
    // Проверка лейблы над полем Gmail
    @Test
    public void LabelPage(){
        driver.get("https://accounts.google.com/SignUp");
        WebElement gmailLabel = driver.findElement(By.cssSelector("#gmail-address-label > strong:nth-child(1)"));
        Assert.assertEquals("Придумайте имя пользователя", gmailLabel.getText());

    }




//    @Test
//    public  void PageError(){
//        driver.get("https://accounts.google.com/SignUp");
//
//        WebElement firstName = driver.findElement(By.id("GmailAddress"));
//        firstName.sendKeys("jbj");
//        WebElement password = driver.findElement(By.id("Passwd"));
//        password.click();
//
//        WebElement errorCss = driver.findElement(By.cssSelector("#errormsg_0_GmailAddress"));
//        errorCss.getText();
//        System.out.println(errorCss.getText());
//        Assert.assertEquals("Допустимое количество символов: 6–30.", errorCss.getText());
//    }
//

}
