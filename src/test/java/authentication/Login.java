package authentication;

import org.testng.Assert;
import org.testng.annotations.Test;
import util.CommonUtils;

public class Login extends CommonUtils {

    @Test(alwaysRun = true, description = "Validate Amazon Home Page Title")
    public void testHomeTitle() {
        String HomePageTitle = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
        String ActualTitle = getPageTitle();
        System.out.println("Page title found: "+ActualTitle);

        Assert.assertEquals(ActualTitle, HomePageTitle, "Amazon home page title incorrect");
        System.out.println("Amazon home page title is matched successfully");
    }

}
