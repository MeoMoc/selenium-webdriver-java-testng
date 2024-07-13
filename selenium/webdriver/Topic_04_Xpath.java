package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");

        driver.manage().window().maximize();
    }

    @Test
    public void Register_01_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html") ;


    //Action lên màn hình
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

    //Verify data
        String emailAddressErrorMessage   = driver.findElement(By.id("txtFirstname-error")).getText();
        Assert.assertEquals(emailAddressErrorMessage,"Please enter your full name");

        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Please enter your full name");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Please enter email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Please re-enter email address");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Please enter a password");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Please re-enter password");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Please enter the phone number.");

        ;
    }

    @Test
    public void Register_02_Invalid_Email_Address() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void Register_03_Incorrect_Confirm_Email() {

    }
    @Test
    public void Register_04_Invalid_Password() {

    }

    @Test
    public void Register_05_Invalid_Confirm_Password() {

    }

    @Test
    public void Register_06_Invalid_Phone_Number() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

//driver viết code sẽ ở trong 1 cái hàm, gọi step ra, chính là before và after class
