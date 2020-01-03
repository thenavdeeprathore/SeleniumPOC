package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeLocators {

    @FindBy(xpath = "//div[@id='nav-signin-tooltip']//span[@class='nav-action-inner'][contains(text(),'Sign in')]")
    public WebElement YOUR_ACCOUNT;
    @FindBy(css = "#nav-cart")
    public WebElement SHOPPING_CART_ICON;
    @FindBy(css = "#nav-cart > #nav-cart-count")
    public WebElement SHOPPING_CART_COUNT;
    @FindBy(css = "#nav-link-accountList > .nav-line-1")
    public WebElement HELLO_USER_NAME;

}
