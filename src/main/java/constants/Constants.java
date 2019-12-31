package constants;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Constants {

    public static final int pageLoadTimeout =40;

    public static final String sProjectDir = System.getProperty("user.dir");
    public static final String sProjectMainPath = sProjectDir + "/src/main/java";
    public static final String sProjectMainResourcesPath = sProjectDir + "/src/main/resources";
    public static final String sProjectTestPath = sProjectDir + "/src/test/java";

    public static final String configPropertiesFilePath = sProjectMainPath + "/config/Config.properties";

    public static final String operatingSystemName = System.getProperty("os.name").toUpperCase();
    public static String browserName;
    public static String browserVersion;

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ExtentTest logger;
    public static final String reportOutputFolder = "./report/";

}
