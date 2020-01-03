package pages;

import locators.HomeLocators;
import org.openqa.selenium.support.PageFactory;
import utilities.TestUtil;

public class HomePage extends TestUtil {

    public HomeLocators home;

    public HomePage() {
        this.home = new HomeLocators();
        PageFactory.initElements(driver, this.home);
    }

    public void navigateToSignInPage(){
        System.out.println("HOME_PAGE: Selecting [YOUR_ACCOUNT] in navigation bar.");
        waitForElementToBeVisible(home.YOUR_ACCOUNT, 10);
        scrollToThenClick(home.YOUR_ACCOUNT);
        System.out.println("HOME_PAGE: Navigating to the SIGNIN_PAGE.\n");
    }

    public void selectShoppingCartIcon(){
        click(home.SHOPPING_CART_ICON);
    }

    public String getNumberOfItemsListedInShoppingCartIcon(){
        return getElementText(home.SHOPPING_CART_COUNT);
    }


}
