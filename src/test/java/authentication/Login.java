package authentication;

import org.testng.Assert;
import org.testng.annotations.Test;
import util.CommonUtils;

public class Login extends CommonUtils {

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

}
