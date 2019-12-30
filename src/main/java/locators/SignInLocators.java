package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInLocators {

    @FindBy(css = "#ap_email")
    public WebElement userEmail;
    @FindBy(css = "#ap_password")
    public WebElement userPassword;
    @FindBy(css = "#signInSubmit")
    public WebElement userSignInButton;
}
