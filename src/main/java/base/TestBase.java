package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.TestException;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase extends Constants {

    protected static WebDriver driver;
    protected static Properties prop = new Properties();
    protected static FileInputStream fis;

    /**
     * Created by: Navdeep on 12/30/2019.
     * This function loads the config properties file
     *
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
     *
     * @param OsType:      Which Operating System you want for are running automation test
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
                        // Suppress Console log warnings
                        System.setProperty("webdriver.chrome.silentOutput", "true");
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
                        // Suppress Console log warnings
                        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");
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
            } else if (Constants.operatingSystemName.startsWith("MAC")) {
                System.out.println("Operating System: " + Constants.operatingSystemName);
                if ("CHROME".equals(browserType)) {
                    try {
                        System.setProperty("webdriver.chrome.driver",
                                Constants.sProjectDir + "/drivers/chromedriver");
                        // Suppress Console log warnings
                        System.setProperty("webdriver.chrome.silentOutput", "true");
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
                        // Suppress Console log warnings
                        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");
                        driver = new FirefoxDriver();
                        System.out.println("Drivers Launched for Firefox");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for FIREFOX; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("SAFARI".equals(browserType)) {
                    try {
                        driver = new SafariDriver();
                        System.out.println("Drivers Launched for Safari");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for Safari; Terminating Execution. " + e);
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
            } else {
                System.out.println("Currently we don't support the automation for this Operating System: " + Constants.operatingSystemName);
            }
            getRemoteWebDriverBrowserCapabilities();
            javascriptBrowserVersion();
            deleteAllCookies();
            maximizeBrowserWindow();
        } catch (Exception e) {
            System.out.println("Exception caught while initializing Drivers on OS: " + Constants.operatingSystemName + " for Browser: "
                    + Constants.browserName + " " + Constants.browserVersion + " " + e);
        }
    }

    /**
     * Created by: Navdeep on 02/01/2020.
     * This function is used to get the RemoteWebDriver Capabilities for the Browser Name and Version.
     */
    public static void getRemoteWebDriverBrowserCapabilities() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        Constants.browserName = cap.getBrowserName().toUpperCase();
        Constants.browserVersion = cap.getVersion();
        System.out.println("Browser Name: " + Constants.browserName);
        System.out.println("Browser Version: " + Constants.browserVersion);
    }

    /**
     * Created by: Navdeep on 02/01/2020.
     * This function is used to display the launched Browser Name and Browser Version.
     */
    public static void javascriptBrowserVersion() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Constants.jsbrowserNameVersion = js.executeScript("return navigator.sayswho= (function(){ var ua= navigator.userAgent, tem, M= ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\\/))\\/?\\s*(\\d+)/i) || [];if(/trident/i.test(M[1])){tem=  /\\\\brv[ :]+(\\d+)/g.exec(ua) || [];return 'IE '+(tem[1] || '');}if(M[1]=== 'Chrome'){tem= ua.match(/\\\\b(OPR|Edge)\\/(\\d+)/);if(tem!= null) return tem.slice(1).join(' ').replace('OPR', 'Opera');}M= M[2]? [M[1], M[2]]: [navigator.appName, navigator.appVersion, '-?'];if((tem= ua.match(/version\\/(\\d+)/i))!= null) M.splice(1, 1, tem[1]);return M.join(' ');})();").toString().toUpperCase();
        System.out.println("Browser Name and Version: "+Constants.jsbrowserNameVersion);
    }

    /**
     * Created by: Navdeep on 02/01/2020.
     * This function is used to delete all the cookies immediately at the browser launch.
     */
    public static void deleteAllCookies() {
        driver.manage().deleteAllCookies();
        System.out.println("Deleted All Cookies");
    }

    /**
     * Created by: Navdeep on 02/01/2020.
     * This function is used to maximize the launched browser.
     */
    public static void maximizeBrowserWindow() {
        driver.manage().window().maximize();
        System.out.println("Browser Window is Maximized");
    }

    /**
     * Created by: Navdeep on 01/01/2020.
     * This function is used to wait until page gets loaded in the given time.
     * @param pageLoadTimeout: Time to wait for page to be loaded
     */
    public static void initializePageLoadTimeout(int pageLoadTimeout) {
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        System.out.println("Page Load Timeout Set is: " + pageLoadTimeout + " Seconds");
    }

    /**
     * Created by: Navdeep on 12/30/2019.
     * This function navigates to the URL in the respective launched browser.
     *
     * @param url: Enter your site URL here
     */
    @Parameters({"url"})
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

    /**
     * Created by: Navdeep on 01/01/2020.
     * This function initializes Extent Reports to generate html reports.
     */
    public static void initializeReport() {
        Path path = Paths.get(Constants.reportOutputFolder);
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Fail to create a directory. " + e);
            }
        }
        Constants.htmlReporter = new ExtentHtmlReporter(Constants.reportOutputFolder + Constants.REPORT_FILE_NAME);
        Constants.htmlReporter.config().setDocumentTitle("Amazon Automation Report");
        Constants.htmlReporter.config().setReportName("Automation Test Results");
        Constants.htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        Constants.htmlReporter.config().setTheme(Theme.STANDARD);
        Constants.extent = new ExtentReports();
        Constants.extent.attachReporter(Constants.htmlReporter);
        Constants.extent.setReportUsesManualConfiguration(true);

        Constants.extent.setSystemInfo("OS", Constants.operatingSystemName);
        Constants.extent.setSystemInfo("Browser Name", Constants.browserName);
        Constants.extent.setSystemInfo("Browser Version", Constants.browserVersion);
        Constants.extent.setSystemInfo("Selenium Version", "3.141.5");
        Constants.extent.setSystemInfo("TestNG Version", "7.1.0");
    }

    /**
     * Created by: Navdeep on 03/01/2020.
     * This function is used to initialize drivers for different Browsers and Versions in the BrowserStack.
     * @param browserType
     */
    public static void initializeBrowserStack(String browserType) {
        switch (browserType) {
            case "CHROME":
                try {
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("os", "OS X");
                    capabilities.setCapability("os_version", "Mojave");
                    capabilities.setCapability("browser", "Chrome");
                    capabilities.setCapability("browser_version", "75.0");
                    capabilities.setCapability("browserstack.debug", "true");
                    capabilities.setCapability("resolution", "1280x960");
                    capabilities.setCapability("name", "Test Suite: " + "Smoke");

                    try {
                        driver = new RemoteWebDriver(new URL(browserstack_URL), capabilities);
                        maximizeBrowserWindow();
                    } catch (MalformedURLException e) {
                        System.out.println("Invalid BrowserStack grid URL " + e);
                    }

                } catch (Exception e) {
                    System.out.println("Problem initializing drivers for CHROME; Terminating Execution. " + e);
                    System.exit(0);
                }
                getRemoteWebDriverBrowserCapabilities();
                break;
            case "FIREFOX":
                try {
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("os", "OS X");
                    capabilities.setCapability("os_version", "Mojave");
                    capabilities.setCapability("browser", "Firefox");
                    capabilities.setCapability("browser_version", "66.0");
                    capabilities.setCapability("browserstack.debug", "true");
                    capabilities.setCapability("resolution", "1280x960");
                    capabilities.setCapability("name", "Test Suite: " + "Smoke");

                    try {
                        driver = new RemoteWebDriver(new URL(browserstack_URL), capabilities);
                        maximizeBrowserWindow();
                    } catch (MalformedURLException e) {
                        System.out.println("Invalid BrowserStack grid URL " + e);
                    }

                } catch (Exception e) {
                    System.out.println("Problem initializing drivers for CHROME; Terminating Execution. " + e);
                    System.exit(0);
                }
                getRemoteWebDriverBrowserCapabilities();
                break;
            case "SAFARI":
                try {
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("os", "OS X");
                    capabilities.setCapability("os_version", "Mojave");
                    capabilities.setCapability("browser", "Safari");
                    capabilities.setCapability("browser_version", "12.1");
                    capabilities.setCapability("browserstack.debug", "true");
                    capabilities.setCapability("resolution", "1280x960");
                    capabilities.setCapability("name", "Test Suite: " + "Smoke");

                    try {
                        driver = new RemoteWebDriver(new URL(Constants.browserstack_URL), capabilities);
                        maximizeBrowserWindow();
                    } catch (MalformedURLException e) {
                        System.out.println("Invalid BrowserStack grid URL " + e);
                    }

                } catch (Exception e) {
                    System.out.println("Problem initializing drivers for CHROME; Terminating Execution. " + e);
                    System.exit(0);
                }
                getRemoteWebDriverBrowserCapabilities();
                break;
            default:
                System.out.println("Browser name: " + browserType + " is Invalid");
        }
    }

    /**
     * Created by: Navdeep on 04/01/2020.
     * This function initialize WebDriverManager for any OS, any browsers and any browser versions.
     *
     * @param OsType:      Which Operating System you want for are running automation test
     * @param browserType: Which Browser Type you want for running automation test
     */
    public static void initializeDriversManager(String OsType, String browserType) {
        try {
            if (OsType.startsWith("WINDOWS")) {
                System.out.println("Operating System: " + OsType);
                if ("CHROME".equalsIgnoreCase(browserType)) {
                    try {
                        WebDriverManager.chromedriver().setup();
                        // Suppress Console log warnings
                        System.setProperty("webdriver.chrome.silentOutput", "true");
                        driver = new ChromeDriver();
                        System.out.println("Drivers Launched for Chrome");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for CHROME; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("FIREFOX".equalsIgnoreCase(browserType)) {
                    try {
                        WebDriverManager.firefoxdriver().setup();
                        // Suppress Console log warnings
                        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");
                        driver = new FirefoxDriver();
                        System.out.println("Drivers Launched for Firefox");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for FIREFOX; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("IE".equalsIgnoreCase(browserType)) {
                    try {
                        WebDriverManager.iedriver().setup();
                        driver = new InternetExplorerDriver();
                        System.out.println("Drivers Launched for IE");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for IE; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("HEADLESS".equalsIgnoreCase(browserType)) {
                    try {
                        WebDriverManager.chromedriver().setup();
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
            } else if (Constants.operatingSystemName.startsWith("MAC")) {
                System.out.println("Operating System: " + Constants.operatingSystemName);
                if ("CHROME".equalsIgnoreCase(browserType)) {
                    try {
                        WebDriverManager.chromedriver().setup();
                        // Suppress Console log warnings
                        System.setProperty("webdriver.chrome.silentOutput", "true");
                        driver = new ChromeDriver();
                        System.out.println("Drivers Launched for Chrome");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for CHROME; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("FIREFOX".equalsIgnoreCase(browserType)) {
                    try {
                        WebDriverManager.firefoxdriver().setup();
                        // Suppress Console log warnings
                        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");
                        driver = new FirefoxDriver();
                        System.out.println("Drivers Launched for Firefox");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for FIREFOX; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("SAFARI".equalsIgnoreCase(browserType)) {
                    try {
                        driver = new SafariDriver();
                        System.out.println("Drivers Launched for Safari");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for Safari; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("HEADLESS".equalsIgnoreCase(browserType)) {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("window-size=1280,960");
                    options.addArguments("headless");   // options.setHeadless(true);
                    driver = new ChromeDriver(options);
                    System.out.println("Drivers Launched for Headless Chrome");
                } else {
                    System.out.println("Browser name: " + browserType + " is Invalid");
                }
            } else if (OsType.startsWith("LINUX")) {
                System.out.println("Operating System: " + OsType);
                if ("CHROME".equalsIgnoreCase(browserType)) {
                    try {
                        WebDriverManager.chromedriver().setup();
                        // Suppress Console log warnings
                        System.setProperty("webdriver.chrome.silentOutput", "true");
                        driver = new ChromeDriver();
                        System.out.println("Drivers Launched for Chrome");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for CHROME; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("FIREFOX".equalsIgnoreCase(browserType)) {
                    try {
                        WebDriverManager.firefoxdriver().setup();
                        // Suppress Console log warnings
                        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");
                        driver = new FirefoxDriver();
                        System.out.println("Drivers Launched for Firefox");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for FIREFOX; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("IE".equalsIgnoreCase(browserType)) {
                    try {
                        WebDriverManager.iedriver().setup();
                        driver = new InternetExplorerDriver();
                        System.out.println("Drivers Launched for IE");
                    } catch (Exception e) {
                        System.out.println("Problem initializing drivers for IE; Terminating Execution. " + e);
                        System.exit(0);
                    }
                } else if ("HEADLESS".equalsIgnoreCase(browserType)) {
                    try {
                        WebDriverManager.chromedriver().setup();
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
            } else {
                System.out.println("Currently we don't support the automation for this Operating System: " + Constants.operatingSystemName);
            }
            getRemoteWebDriverBrowserCapabilities();
            javascriptBrowserVersion();
            deleteAllCookies();
            maximizeBrowserWindow();
        } catch (Exception e) {
            System.out.println("Exception caught while initializing Drivers on OS: " + Constants.operatingSystemName + " for Browser: "
                    + Constants.browserName + " " + Constants.browserVersion + " " + e);
        }
    }

}
