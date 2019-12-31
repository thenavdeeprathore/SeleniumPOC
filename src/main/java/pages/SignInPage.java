package pages;

import locators.SignInLocators;
import org.openqa.selenium.support.PageFactory;
import util.CommonUtils;

public class SignInPage extends CommonUtils {

    public SignInLocators login;

    public SignInPage() {
        this.login = new SignInLocators();
        PageFactory.initElements(driver, this.login);
    }

    public void enterUsername(String userName){
        System.out.println("SIGNIN_PAGE: Entering username: " + userName);
        waitForElementToBeVisible(login.userEmail, 10);
        sendKeys(login.userEmail, userName);
    }

    public void enterPassword(String password){
        System.out.println("SIGNIN_PAGE: Entering password.");
        waitForElementToBeVisible(login.userPassword);
        sendKeys(login.userPassword, password);
    }

    public void clickSignInButton(){
        System.out.println("SIGNIN_PAGE: Clicking the [SIGN_IN] button.\n");
        click(login.userSignInButton);
    }
}
