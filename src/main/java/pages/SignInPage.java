package pages;

import locators.SignInLocators;
import org.openqa.selenium.support.PageFactory;
import utilities.TestUtil;

public class SignInPage extends TestUtil {

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

    public void clickContinueButton(){
        System.out.println("SIGNIN_PAGE: Clicking the [CONTINUE] button.\n");
        click(login.continueButton);
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

    public void loginAs(String username, String password){
        signInPage.enterUsername(username);
        signInPage.clickContinueButton();
        signInPage.enterPassword(password);
        signInPage.clickSignInButton();
    }
}
