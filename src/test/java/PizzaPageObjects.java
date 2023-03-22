package pizza_page_selenium.src.test.java;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class PizzaPageObjects {
    @FindBy(css = "span[class='ginput_total ginput_total_5']")
    public WebElement priceElement;

    @FindBy(css = "input[name='input_22.3']")
    public WebElement fnameElement;

    @FindBy(how = How.NAME, using = "input_22.6")
    public WebElement lnameElement;

    @FindBy(css = "select[name='input_21']")
    public WebElement selectorElement;

    @FindBy(how = How.ID, using = "input_5_23")
    public WebElement txt_phoneNumber;

    @FindBy(how = How.ID, using = "input_5_21")
    public WebElement selectPickup;

    @FindBy(xpath = "//iframe[@src='coupon.html']")

    public WebElement iframeElement;

    @FindBy(css = "div[id='coupon_Number']")
    public WebElement couponElement;

    @FindBy(css = "textarea[name='input_20']"
    )
    public WebElement textAreaElement;

    @FindBy(css = "input[id='gform_submit_button_5']")
    public WebElement submitButtonElement;

    public void insertCoupon(String coupon) {
        this.textAreaElement.sendKeys(coupon);
        this.submitButtonElement.click();
    }

    public void selectByIndex(int index) {
        Select mySelector = new Select(this.selectorElement);
        mySelector.selectByIndex(index);
    }

    public String getPrice() {
        return this.priceElement.getText();
    }

    public void fillDetails(String firstName, String lastName) {
        this.fnameElement.sendKeys(firstName);
        this.lnameElement.sendKeys(lastName);
    }

    public String getCoupon() {
        return this.couponElement.getText();
    }
}