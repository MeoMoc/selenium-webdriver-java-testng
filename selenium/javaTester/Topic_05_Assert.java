package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_05_Assert {
    WebDriver driver;
    @Test
    public void verifyTestNG() {
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com");

        //java có nhiều thư viện để verify dữ liệu => gọi là testing framework (apply cho Unit/Intergration/UI Automation test)
        //phổ biến: JUnit 4/TestNG/JUnit 5/Hamcrest/AssertJ/...
        //TestNG vừa \là thư viện vừa ;à Test runner

        //kiểu DL là boolean
        //khi mong muốn đk trả về là đuúng => dùng asserttrue để verify nếu muốn chứa data đó, còn ko là assertFalse
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));

        //khi mong muốn đk trả về là sai
        Assert.assertFalse(driver.getPageSource().contains("Create a new account"));

        //Các hàm trả về dữ liệu boolean true/false
        //Quy tăc: bắt đầu với tiền tố là isXXX
        //WebElement
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select(driver.findElement(By.id(""))).isMultiple();
        //=> dùng vơới asserttrue/false

        //selenium 3/2/1
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //selenium 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //áp dụng được  nhiều kiểu DL, mong đợi 1 điều kiện giống thực tế tuyệt đối)
        // Actual = expected
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com");
        //Assert.assertEquals(driver.findElement(By.id("")).getText("Create a new account"));




        //Unit Test
        //Object name = null;
        //Assert.assertNull();

        //name = "testing";
        //Assert.assertNotNull();


    }

}
