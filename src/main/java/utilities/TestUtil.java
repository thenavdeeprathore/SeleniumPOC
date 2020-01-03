package utilities;

import base.Constants;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import pages.HomePage;
import pages.SignInPage;

import java.io.IOException;

public class TestUtil extends CommonUtils {

    protected static SignInPage signInPage;
    protected static HomePage homePage;

    /**
     * Created by: Navdeep on 02/01/2020.
     * This function is used for doing web driver setup.
     */
//    @Parameters({"url", "browser"})
    @BeforeSuite(alwaysRun = true)
    public static void setup() {
        System.out.println("# Setup.");
        loadProperties(Constants.configPropertiesFilePath);
//        initializeDrivers(Constants.operatingSystemName, browser.toUpperCase());    //Give browser name through command line
        initializeDriversManager(Constants.operatingSystemName, prop.getProperty("browser").toUpperCase());
        initializePageLoadTimeout(Constants.pageLoadTimeout);
//        navigateToURL(url); //Give url through command line
        navigateToURL(prop.getProperty("url"));
        initializeReport();
        initializePages();
    }

    /**
     * Created by: Navdeep on 02/01/2020.
     * This function is used to quit the driver instance.
     */
    @AfterSuite(alwaysRun = true)
    public void teardown() {
        System.out.println("# Teardown.");
        if (driver != null)
            driver.quit();
        try {
            killBrowserDriverInstances();
        } catch (IOException e) {
            System.out.println("Failure: while killing driver instances. "+e);
        }
    }

    /**
     * Created by: Navdeep on 02/01/2020.
     * This function is used to initialize page objects.
     */
    public static void initializePages() {
        signInPage = new SignInPage();
        homePage = new HomePage();
    }

}
