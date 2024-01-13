package pizza_page_selenium.src.test.java;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class PizzaMain {
    WebDriver driver;
    PizzaPageObjects pizza;
    WebDriverWait wait;
    Actions action;
    String fname = "Elior";
    String lname = "Levi";
    String expectedInitialPrice = "$7.50";
    String expectedUpdatedPrice = "$10.50";
    String expectedCouponText = "088-234";

    public PizzaMain() {
    }

    @BeforeClass
    public void SetupSession() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://atidcollege.co.il/Xamples/pizza/");
        this.driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        this.wait = new WebDriverWait(this.driver, 10L);
        this.action = new Actions(this.driver);
        this.pizza = (PizzaPageObjects) PageFactory.initElements(this.driver, PizzaPageObjects.class);
        this.pizza.fillDetails(this.fname, this.lname);
    }

    @Test(description = "Verify the initial price of the pizza order")
    public void test01_verifyInitialPrice() {
        System.out.println(this.pizza.priceElement.getText());
        System.out.println("Hello World!");
        Assert.assertEquals(this.pizza.getPrice(), this.expectedInitialPrice);
    }

    @Test(description = "Verify the updated price")
    public void test02_verifyUpdatedPrice() {
        this.pizza.selectByIndex(1);
        Assert.assertEquals(this.pizza.getPrice(), this.expectedUpdatedPrice);
    }

    @Test(description = "Verify Coupon")
    public void test03_verifyCoupon() {
        this.driver.switchTo().frame(this.pizza.iframeElement);
        String myCoupon = this.pizza.getCoupon();
        this.driver.switchTo().defaultContent();
        Assert.assertEquals(myCoupon, expectedCouponText);
        System.out.println(myCoupon);

        this.pizza.insertCoupon(myCoupon);
        Alert myAlert = this.driver.switchTo().alert();
        String alertText = myAlert.getText();
        myAlert.accept();
        String expectedResult = this.fname + " " + this.lname + " " + myCoupon;
        Assert.assertEquals(alertText, expectedResult);
    }

    @AfterClass
    public void CloseSession() {
        Uninterruptibles.sleepUninterruptibly(5L, TimeUnit.SECONDS);
        this.driver.quit();
    }
}
