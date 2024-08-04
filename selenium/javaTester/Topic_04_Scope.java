package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Scope {
    // các biến được khai báo ở bên ngoài hàm => phạm vi là class => biến Global
    //có thể dùng cho all hàm trong 1 class

    WebDriver driver;
    String homepageURL = "https://www.facebook.com"; //khai báo: declare

    String fullName = "Automation FC"; //khai báo + khởi tạo (initial)
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Url() {
        // biến khai báo trong một hàm => phạm vi cục bộ => biến Local
        //dùng trong cái hàm nó được khai báo hay block code nó được sinh ra
        String homepageURL = "https://www.facebook.com";

        //trong 1 hàm nếu  có 2 biến cùng tên (global/local) thì nó sẽ ưu tiên lấy biến Local
        //1 biến Local nếu như gọi tới duùng mà chưa được khởi tạo sẽ bị lỗi ngay từ level Compile code
        //Nếu trong 1 hàm có 2 biến cùng tên và mình muốn lấy biến Global thì dùng this
        driver.get(homepageURL);

        // Biến Global chưa khởi tạo mà gọi ra dùng thì ko báo lỗi ở level compile code mà level Run time mới báo lỗi
        driver.get(this.homepageURL);
        //hoặc
        //this.driver.get(homepageURL);


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
