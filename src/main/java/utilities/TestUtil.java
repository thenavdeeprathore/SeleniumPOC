package utilities;

import constants.Constants;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class TestUtil extends CommonUtils {

    /**
     * Created by: Navdeep on 02/01/2020.
     * This function is used for doing web driver setup.
     */
    @BeforeSuite(alwaysRun = true)
    public static void setup() {
        System.out.println("# Setup.");
        loadProperties(Constants.configPropertiesFilePath);
        initializeDrivers(Constants.operatingSystemName, prop.getProperty("browser").toUpperCase());
        initializePageLoadTimeout(Constants.pageLoadTimeout);
        navigateToURL(prop.getProperty("url"));
        initializeReport();
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

}
