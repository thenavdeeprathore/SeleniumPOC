package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Constants {

    protected static final int pageLoadTimeout =40;

    protected static final String sProjectDir = System.getProperty("user.dir");
    protected static final String sProjectMainPath = sProjectDir + "/src/main/java";
    protected static final String sProjectMainResourcesPath = sProjectDir + "/src/main/resources";
    protected static final String sProjectTestPath = sProjectDir + "/src/test/java";

    protected static final String configPropertiesFilePath = sProjectMainPath + "/config/Config.properties";

    protected static final String operatingSystemName = System.getProperty("os.name").toUpperCase();
    protected static String browserName;
    protected static String browserVersion;
    protected static String jsbrowserNameVersion;

    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static final String reportOutputFolder = "./report/";
    protected static final String REPORT_FILE_NAME = "QAReportSmoke.html";

//    protected static String browserstackUsername = System.getenv("BROWSERSTACK_USERNAME");    //Jenkins
//    protected static String browserstackAccessKey = System.getenv("BROWSERSTACK_ACCESS_KEY"); //Jenkins
    protected static String browserstackUsername = "BROWSERSTACK_USERNAME";
    protected static String browserstackAccessKey = "BROWSERSTACK_ACCESS_KEY";
    protected static final String browserstack_USERNAME = browserstackUsername;
    protected static final String browserstack_AUTOMATE_KEY = browserstackAccessKey;
    protected static final String browserstack_GridURL = "@hub-cloud.browserstack.com/wd/hub";
    protected static final String browserstack_URL = "https://" + browserstack_USERNAME + ":" + browserstack_AUTOMATE_KEY + browserstack_GridURL;

}
