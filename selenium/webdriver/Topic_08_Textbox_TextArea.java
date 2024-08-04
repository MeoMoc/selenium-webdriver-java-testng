package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_08_Textbox_TextArea {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Login_empty_email_password() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);


        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals("This is a required field.",
                driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText());
        Assert.assertEquals("This is a required field.",
                driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText());
    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("123@345.567");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.",
                driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText());
    }

    @Test
    public void TC_03_Invalid_Pass() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("mocmeo@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.",
                driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText());


    }

    @Test
    public void TC_04_Incorrect_Pass_Email() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);


        driver.findElement(By.cssSelector("input#email")).sendKeys("mocmeo@gmail.com.net");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456789");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals("Invalid login or password.",
                driver.findElement(By.cssSelector("li.error-msg span")).getText());


        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#pass")).clear();

        driver.findElement(By.cssSelector("input#email")).sendKeys("mocmeo@gmail.com");// đk trước rồi
        driver.findElement(By.cssSelector("input#pass")).sendKeys("1245345435");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals("Invalid login or password.",
                driver.findElement(By.cssSelector("li.error-msg span")).getText());

    }

    @Test
    public void TC_05_Success() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        //Đăng ký
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);

        String firstname = "Automation", lastname = "FC", emailaddress = getEmailAddress(), password = "123456789";
        String fullname = firstname + " " + lastname; //hoặc fullname = firstname.concat(" " + lastname);

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstname); //truyền biến
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastname);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailaddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, " + fullname + "!");

        //1 thẻ chứa nhiều text như br thì verify tương đối
        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullname));
        Assert.assertTrue(contactInfo.contains(emailaddress));

        //Logout
        driver.findElement(By.cssSelector("a.skip-account")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        sleepInSeconds(5);

        //Login
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input#email")).sendKeys(emailaddress);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, " + fullname + "!");

        //String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullname));
        Assert.assertTrue(contactInfo.contains(emailaddress));

        //Verify Acc
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"),firstname);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"),lastname);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"),emailaddress);


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

public void sleepInSeconds(long timeInSecond) {
    try {
        Thread.sleep(timeInSecond * 1000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}

    public  String getEmailAddress() {
        Random rand = new Random();
        return "automation" + rand.nextInt(9999) + "@gmail.net";

    }
}