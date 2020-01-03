package authentication;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestUtil;

public class Login extends TestUtil {

    @Test(alwaysRun = true, description = "Validate Amazon Home Page Title")
    public void testHomeTitle() {
        String HomePageTitle = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
        String ActualTitle = getPageTitle();
        logInfo("Page title found: "+ActualTitle);
        Assert.assertEquals(ActualTitle, HomePageTitle, "Amazon home page title is incorrect");
        logInfo("Amazon home page title is matched successfully");
    }

    @Test(alwaysRun = true, description = "Validate URL contains https:")
    public void testUrlProtocol() {
        String CurrentUrl = getCurrentURL();
        logInfo("Current URL: "+CurrentUrl);
        Assert.assertTrue(CurrentUrl.contains("https:"), "URL protocol https is not present");
        logInfo("Current URL is secured and contains https: protocol");
    }

    @Test(alwaysRun = true, description = "Validate navigate to Sign In page")
    public void testNavigateToSignIn() {
        homePage.navigateToSignInPage();
        logInfo("Navigated to Sign In page successfully");
        Assert.assertTrue(isDisplayed(signInPage.login.userEmail), "User Email field is not displayed");
        logInfo("User Email field is displayed successfully");
    }

    @Test(alwaysRun = true, description = "Validate Sign In with valid credentials")
    public void testSignIn() {
        String username = prop.getProperty("email");
        String password = prop.getProperty("password");
        signInPage.loginAs(username, password);
        waitForElementToBeVisible(homePage.home.HELLO_USER_NAME);
        Assert.assertTrue(getElementText(homePage.home.HELLO_USER_NAME).equalsIgnoreCase("Hello, Navdeep"), "Login is unsuccessful");
        logInfo("User Logged In successfully");
    }

}
