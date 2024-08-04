package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Topic_06_WebBrowser_Commands_01 {
    WebDriver driver;
    // Các câu lệnh thao tác với broser đứng sau driver.

    WebElement element;
    //  Các câu lệnh thao tác với webElement đưứng sau element.

    //trong lập trình, thử viện của selenium, khai báo webdriver để chạy bất kỳ trình duyệt nào mong muốn. Nếu 1 class kế thừa class khác hoặc interface kế thua interface => extend, 1 trong 4 nguyên tắc của OOP, nếu 1 class kế thức interface => implement


    @BeforeClass // Pre-conditions của TestNG, gắn annotation vào các hàm để thành tính năng
    public void beforeClass() {
        //Muốn dùng được thì phải khởi tạo, nếu ko khởi tạo sẽ gặp lỗi: NullPointerException. 1 biến chưa được khởi tao đã được gọi ra để sử dụng
        //Khởi tạo là New, gọi ra là Call
        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver = new EdgeDriver();
        //driver = new SafariDriver();
        System.out.println(driver.toString());
        // FirefoxDriver: firefox on windows (b143f280-5d86-4638-a231-1af1b407de1d => GUID: global unique identifier duy nhất ko trùng lặp)
        // driver = new HTMLUnit(); //Headless browser: Crawl data => DA/ Dev FE làm unit test


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com");
    }

    @Test
    public void TC_01_Url() throws MalformedURLException {
        // Set trực tiếp hoặc khai báo biến r gán vào sau
        driver.get("https://www.facebook.com");
        String homePageURL = "https://www.facebook.com"; //khai báo biến
        driver.get(homePageURL); //biến ko trong ""


        // tham số cũng là 1 kiểu DL, void là ko cần trả về dữ liệu MÀ CHỈ ĐANG THAO TÁC LÊN BROWSER
        // CÁI BIẾN THUỘC KIEU DL GÌ THÌ CÁI HÀM THUỘC VÀO KIỂU DL ĐÓ THÌ DÙNG CÁC HÀM ĐÓ ĐƯỢC
        // TÊN HÀM: DÙNG LÀM GÌ
        // CÓ THAM SỐ HAY KHÔNG
        // TRẢ VỀ GÌ
        // CÓ THUỘC KIỂU DL ĐÓ HAY KO

        // Nếu như có 1 tab/window thì tương tự quit
        // Nhiều hơn 1 th nó sẽ đóng cái đang active
        driver.close();

        //đóng browser ko care bn tab/window
        driver.quit();

        //**CHỈ 2 HÀM findElement/findElements bị ành hưởng Timeout cua implicitWait chờ đến khi element được tìm thấy

        //đi tìm loạaij By nào trả về element nếu như được tìm thấy (webElement)
        // Ko được tìm thấy: Fail tại step này - throw exception:NoSuchElementException
        WebElement emailAddressTextbox = driver.findElement(By.id("email"));

        //đi tìm loại By nào trả về nhiều element nếu như được tìm thấy (List webElement) =? List cho phép lưu giá trị trùng lặp, còn Set thì ko
        // Ko được tìm thấy: Ko bị fail tại step này - trả  về một list rỗng
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        checkboxes.get(1).click();
        //driver.findElements();
        //tại sao phải lấy dữ liệu ra => 2 cách verify
        //Nếu dùng 1  lần thì ko khai báo biến. VD:
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com");
        //Dùng nhiều lần thì khai báo/
        String loginPageURL = "https://www.facebook.com"; //khai báo biến
        driver.get(loginPageURL);

        //dùng để lấy ra url của màn hình/page hiện tại đang đứng
        driver.getCurrentUrl(); //*

        //Lấy ra page source HTML/CSS/JS cua page hiện tại => chính là view page source => verify một cách tương đối
        driver.getPageSource();
        driver.getCurrentUrl().contains("Logo của Apple và Google Play là nhãn hiệu hàng hóa thuộc chủ sở hữu tương ứng.");
        Assert.assertTrue(driver.getCurrentUrl().contains("Logo của Apple và Google Play là nhãn hiệu hàng hóa thuộc chủ sở hữu tương ứng."));

        //Lấy ra title của page hiện tại
        driver.getTitle();

        //lấy ra ID của cửa sổ/tab hiện tại => cũng giống như ID của driver
        //Handle window/tab
        driver.getWindowHandle();//*
        driver.getWindowHandles();//*

        //cookies - framework
        driver.manage().getCookies();

        // Get những log ở dev tool
        //driver.manage().logs().get(LogType.DRIVER);//*

        //apply cho việc tìm element (findelement và findelements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));//**

        //chờ cho page load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        //set trước khi dùng với thư viện JSExeccutor
        //Inject 1 đoạn code JS vào browser/Element
        driver.manage().timeouts().scriptTimeout((Duration.ofSeconds(30)));

        //Selenium 4 mới có
        //driver.manage().timeouts().getImplicitWaitTimeout(Duration.ofSeconds(30));//**
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        //chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();//*
        driver.manage().window().minimize();

        //TEST GUI: giao diện
        //Test Responsive (Resolution - tức độ phân giải khác nhau)
        driver.manage().window().setSize(new Dimension(1366,768));

        driver.manage().window().getSize();

        //Set cho browser ỏ vị tr nào so với dộ phân giai màn hình (trên màn hình có kích thước bn)
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        //điều hướng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        //thao tác vs history của webpage (back/forward)
        driver.navigate().to("https://www.facebook.com");
        driver.navigate().to(new URL("https://www.facebook.com")); //chọn add exception => nhâận kiềểu dự liệu URl của Java
        driver.get("https://www.facebook.com");//**

        //Alert/window (tab)/Frame (iframe)
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");

        //Lấy ra ID tab/win hiện tại
        //Handle windoww/tab
        String homepagewindowID = driver.getWindowHandle();
        driver.switchTo().window(homepagewindowID);

        Set<String> allWindowIDs = driver.getWindowHandles();

        //switch/handle frame (iframe)
        //index/ID (name)/Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("3944678");
       // driver.switchTo().frame(driver.findElement(""));

        //switch về HTML chứa frame trước đó
        driver.switchTo().defaultContent();
        driver.switchTo().parentFrame();

    }

    @Test
    public void TC_02_Logo() {

    }

    @Test
    public void TC_03_Form() {

    }

    @AfterClass //Post-conditions
    public void afterClass() {
        driver.quit(); //**
    }
}

