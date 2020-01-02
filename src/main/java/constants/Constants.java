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
    public static String jsbrowserNameVersion;

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static final String reportOutputFolder = "./report/";
    public static final String REPORT_FILE_NAME = "QAReportSmoke.html";

}
