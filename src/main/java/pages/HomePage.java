package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(id = "nav-link-yourAccount")
    public WebElement YOUR_ACCOUNT;
    @FindBy(css = "#nav-cart")
    public WebElement SHOPPING_CART_ICON;
    @FindBy(css = "#nav-cart > #nav-cart-count")
    public WebElement SHOPPING_CART_COUNT;
}
