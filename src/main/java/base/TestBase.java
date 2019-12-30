package base;

import constants.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected static WebDriver driver;
    protected static Properties prop = new Properties();
    protected static FileInputStream fis;
    protected static String browserNameVersion;

    /**
     * This function is used for doing web driver setup.
     */
    @BeforeSuite(alwaysRun=true)
    public static void setup () {
        System.out.println("# Setup.");
        loadProperties(Constants.configPropertiesFilePath);
        initializeDrivers(Constants.operatingSystemName, prop.getProperty("browser").toUpperCase());
        initializePageLoadTimeout(Constants.pageLoadTimeout);
        navigateToURL(prop.getProperty("url"));
    }

    /**
     * This function is used to quit the driver instance.
     */
    @AfterSuite(alwaysRun = true)
    public void teardown() {
        System.out.println("# Teardown.");
        if (driver != null)
            driver.quit();
    }

    /**
     * Created by: Navdeep on 12/30/2019.
     * This function loads the config properties file
     * @param filePath: The path of your config.properties file
     */
    public static void loadProperties(String filePath) {
        if (driver == null) {
            try {
                fis = new FileInputStream(filePath);
                prop.load(fis);
                System.out.println("Config file loaded successfully!");
            } catch (FileNotFoundException e) {
                System.out.println("Problem Reading Config Properties File; Terminating Execution! " + e);
                System.exit(0);
            } catch (IOException e) {
                System.out.println("Problem Loading Config Properties File; Terminating Execution! " + e);
                System.exit(0);
            }
        } else {
            System.out.println("driver is not null");
            System.exit(0);
        }
    }

    /**
     * Created by: Navdeep on 12/30/2019.
     * This function initialize various browser drivers on different operating systems
     * @param OsType: Which Operating System you want for are running automation test
     * @param browserType: Which Browser Type you want for running automation test
     */
    public static void initializeDrivers(String OsType, String browserType) {
        try {
            if (OsType.startsWith("WINDOWS")) {
                System.out.println("Operating System: " + OsType);
                if ("CHROME".equals(browserType)) {
                    try {
                        System.setProperty("webdriver.chrome.driver",
                                Constants.sProjectDir + "/drivers/chromedriver.exe");
                        Map<String, Object> prefs = new HashMap<String, Object>();
                        prefs.put("profile.default_content_setting_values.notifications", 2);
                        prefs.put("credentials_enable_service", false);
                        prefs.put("profile.password_manager_enabled", false);
                        ChromeOptions options = new ChromeOptions();
                        options.setExperimentalOption("prefs", prefs);
                        options.addArguments("--disable-extensions");
                        options.addArguments("--disable-infobars");
                        driver = new ChromeDriver(options);
                        System.out.println("Drivers Launched for Chrome");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for CHROME; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("FIREFOX".equals(browserType)) {
                    try {
                        System.setProperty("webdriver.gecko.driver",
                                Constants.sProjectDir + "/drivers/geckodriver.exe");
                        driver = new FirefoxDriver();
                        System.out.println("Drivers Launched for Firefox");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for FIREFOX; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("IE".equals(browserType)) {
                    try {
                        System.setProperty("webdriver.ie.driver",
                                Constants.sProjectDir + "/drivers/IEDriverServer.exe");
                        driver = new InternetExplorerDriver();
                        System.out.println("Drivers Launched for IE");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for IE; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("HEADLESS".equals(browserType)) {
                    try {
                        System.setProperty("webdriver.chrome.driver",
                                Constants.sProjectDir + "/drivers/chromedriver.exe");
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("window-size=1400,800");
                        options.addArguments("headless");
                        driver = new ChromeDriver(options);
                        System.out.println("Drivers Launched for Headless Chrome");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for HEADLESS Chrome; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else {
                    System.out.println("Browser name: " + browserType + " is Invalid");
                }
                Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
                Constants.browserName = cap.getBrowserName().toUpperCase();
                Constants.browserVersion = cap.getVersion();
                System.out.println("Browser Name: " + Constants.browserName);
                System.out.println("Browser Version: " + Constants.browserVersion);
            } else if (Constants.operatingSystemName.startsWith("MAC")) {
                System.out.println("Operating System: " + Constants.operatingSystemName);
                if ("CHROME".equals(browserType)) {
                    try {
                        System.setProperty("webdriver.chrome.driver",
                                Constants.sProjectDir + "/drivers/chromedriver");
                        Map<String, Object> prefs = new HashMap<String, Object>();
                        prefs.put("profile.default_content_setting_values.notifications", 2);
                        prefs.put("credentials_enable_service", false);
                        prefs.put("profile.password_manager_enabled", false);
                        ChromeOptions options = new ChromeOptions();
                        options.setExperimentalOption("prefs", prefs);
                        options.addArguments("--disable-extensions");
                        options.addArguments("--disable-infobars");
                        driver = new ChromeDriver(options);
                        System.out.println("Drivers Launched for Chrome");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for CHROME; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("FIREFOX".equals(browserType)) {
                    try {
                        System.setProperty("webdriver.gecko.driver",
                                Constants.sProjectDir + "/drivers/geckodriver");
                        driver = new FirefoxDriver();
                        System.out.println("Drivers Launched for Firefox");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for FIREFOX; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("IE".equals(browserType)) {
                    try {
                        System.setProperty("webdriver.ie.driver",
                                Constants.sProjectDir + "/drivers/IEDriverServer");
                        driver = new InternetExplorerDriver();
                        System.out.println("Drivers Launched for IE");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for IE; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("HEADLESS".equals(browserType)) {
                    System.setProperty("webdriver.chrome.driver",
                            Constants.sProjectDir + "/drivers/chromedriver");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("window-size=1280,960");
                    options.addArguments("headless");   // options.setHeadless(true);
                    driver = new ChromeDriver(options);
                    System.out.println("Drivers Launched for Headless Chrome");
                } else {
                    System.out.println("Browser name: " + browserType + " is Invalid");
                }
                Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
                Constants.browserName = cap.getBrowserName().toUpperCase();
                Constants.browserVersion = cap.getVersion();
                System.out.println("Browser Name: " + Constants.browserName);
                System.out.println("Browser Version: " + Constants.browserVersion);
            } else {
                System.out.println("Currently we don't support the automation for this Operating System: " + Constants.operatingSystemName);
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            browserNameVersion = js.executeScript("return navigator.sayswho= (function(){ var ua= navigator.userAgent, tem, M= ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\\/))\\/?\\s*(\\d+)/i) || [];if(/trident/i.test(M[1])){tem=  /\\\\brv[ :]+(\\d+)/g.exec(ua) || [];return 'IE '+(tem[1] || '');}if(M[1]=== 'Chrome'){tem= ua.match(/\\\\b(OPR|Edge)\\/(\\d+)/);if(tem!= null) return tem.slice(1).join(' ').replace('OPR', 'Opera');}M= M[2]? [M[1], M[2]]: [navigator.appName, navigator.appVersion, '-?'];if((tem= ua.match(/version\\/(\\d+)/i))!= null) M.splice(1, 1, tem[1]);return M.join(' ');})();").toString().toUpperCase();
            driver.manage().deleteAllCookies();
            System.out.println("Deleted All Cookies");
            driver.manage().window().maximize();
            System.out.println("Browser Window is Maximized");
        } catch (Exception e) {
            System.out.println("Exception caught while initializing Drivers on OS: " + Constants.operatingSystemName + " for Browser: "
                    + Constants.browserName + " " + Constants.browserVersion + " " + e);
        }
    }

    public static void initializePageLoadTimeout(int pageLoadTimeout) {
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        System.out.println("Page Load Timeout Set is: " + pageLoadTimeout + " Seconds");
    }

    /**
     * Created by: Navdeep on 12/30/2019.
     * This function navigates to the URL in the respective launched browser
     * @param url: Enter your URL here
     */
    public static void navigateToURL(String url) {
        try {
            System.out.println("Navigating to: " + url);
            driver.navigate().to(url);
            System.out.println("Browser Launched Successfully! User trying to login...");
        } catch (Exception e) {
            System.out.println("FAILURE: URL did not load: " + url);
            throw new TestException("URL did not load");
        }
    }

}
