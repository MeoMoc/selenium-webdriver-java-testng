package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_07_WebElement_Commands_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {


        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");

    }

    @Test
    public void TC_01_Element() {
        //Tìm và trả về 1 element: HTML Elememnt: textbox, textarea/dropdown
        //Tìm và lưu vào 1 bến WebElement (chưa tương tác)
        driver.findElement(By.id(""));

        //Tìm và lưu vào 1 bến WebElement (có tương tác lên)
        driver.findElement(By.id("")).click();
        driver.findElement(By.id("")).sendKeys("");

        //Khi có dùng biến này ít nhất từ 2 lần trở lên
        WebElement fullNameTextbox = driver.findElement(By.id(""));
        fullNameTextbox.clear();
        fullNameTextbox.sendKeys("AutomationFC");
        fullNameTextbox.getAttribute("value");

        //Dùng xóa dữ lieuej trong 1 field cho phép nhập  editable
        //Textbbox, area, dropdown
        //Thường được sử dụng trươc sendKey để bảo toàn tính toàn vẹn của DL
        driver.findElement(By.id("")).clear();

        //Dùng nhập liệu vào các field trên
        driver.findElement(By.id("")).sendKeys("");

        //dùng để click lên element
        driver.findElement(By.id("")).click();

        //tìm từ node cha vào node con
        driver.findElement(By.id("form-validate")).findElement(By.id("firstname"));
        driver.findElement(By.cssSelector("form#form-validate input#firstname"));

        //trả về nhiều elements khớp đk
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

        //Java non Generic
        ArrayList name = new ArrayList();
        name.add("Automation FC");
        name.add(15);
        name.add('B');
        name.add(true);

        //Java Generic
        //Cho phép add 1 kiểu DL
        ArrayList<String> address = new ArrayList<String>();
        //address.add("Automation FC");
        //address.add(15);
        //address.add('B');
        //address.add(true); // báo lỗi

        // verify 1 checkbox/radio/dropdown (default) đã được chọn hay chưa
        driver.findElement(By.id("")).isSelected();
        Assert.assertTrue(driver.findElement(By.id("")).isSelected());
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        // Dropdown (default/custom)
        Select select = new Select(driver.findElement(By.id("")));

        //verify 1 element bất kỳ có hiển thị ko
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());

        //verify 1 element có được thao tác lên ko (ko phải read-only)
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled());

        //HTML Element
        //<input type="text" id ="firsstname" name="firstname" value=""
        //title="First Name" maxlength="255" class="input-text required-entry">
        //Assert.assertTrue(driver.findElement(By.id("")).getAttribute("title"));
        //Assert.assertTrue(driver.findElement(By.id("")).getAttribute("type"));
        //Assert.assertTrue(driver.findElement(By.id("")).getAttribute("id"));

        //Tab Accessibility/Properties trong Element
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");//dùng với CSS trả veef true/false)
        driver.findElement(By.id("")).getDomProperty("baseURI");
        driver.findElement(By.id("")).getDomProperty("outerHTML");

        //Tab Style trong Element => Font/Sixe/Color/...
        driver.findElement(By.id("")).getCssValue("background-color");
        //rgb(46, 138, 184)

        //ví trí của Element so với độ phân giải màn hình
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();
        nameTextboxLocation.getX();
        nameTextboxLocation.getY();

        //Chieu cao + rộng
        Dimension addressSize = driver.findElement(By.id("")).getSize();

        //Location + Size
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect(); // tổng hợp 2 thằng t5reen cộng lại
        nameTextboxRect.getX();
        nameTextboxRect.getY();

        //Location
        Point namePoint = nameTextboxRect.getPoint();
        //Size
        Dimension nameSize = nameTextboxRect.getDimension();
        nameSize.getHeight();
        nameSize.getWidth();

        //Handle Shadow elements (JSExecutor)
        driver.findElement(By.id("")).getShadowRoot();

        driver.findElement(By.id("")).getSize();
        //Từ id/class/name/css/xpath có thể truy ra được ngược lại tagname HTML => dùng khi ko biêt tagname
        driver.findElement(By.cssSelector("#firstname")).getTagName(); //input
        driver.findElement(By.id("firstname")).getTagName(); //input
        driver.findElement(By.className("form-instructions")).getTagName();//p

        //Element A => đầu ra là tagname của element A
        String formListTag = driver.findElement(By.xpath("//*[@class='form-list']")).getTagName();//ul
        // Đầu vào của Element B sẽ nhận tagname của ele A là tham số truyền vào
        driver.findElement(By.xpath("//div[@class='remember-me-popup']/preceding-sibling::ul" + formListTag));
        //=> khi chạy sẽ tìm cả 2 cái

        //text bị ần thì ko get được
        driver.findElement(By.cssSelector("address.copyright")).getText();

        //chụp hình bị lôỗi và lưu dưới dạng nào: BYTE/FILE (lưu thành hình có kích thước local trong ổ cứng/BASE64 (hình dưới dạg text nên ko có kích thước)
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BASE64);

        //Template tăng tốc độ viêt code

        //Form (element nào là thẻ form hoặc nằm trong thẻ form) giống hành vi nhấn enter
        //Register/Login/Search/...
        driver.findElement(By.id("")).submit();
    }

    @Test
    public void TC_02_Logo() {

    }

    @Test
    public void TC_03_Form() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

