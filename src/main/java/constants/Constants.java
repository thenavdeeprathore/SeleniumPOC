package constants;

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

}
