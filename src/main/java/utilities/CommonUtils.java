package utilities;

import base.TestBase;
import com.aventstack.extentreports.Status;
import base.Constants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import java.io.File;
import java.io.IOException;

public class CommonUtils extends TestBase {

    protected static WebDriverWait wait;
    private static int timeout = 10;
    protected Actions actions;

    /*      ----- Common Utils -----      */

    /**
     * Created by: Navdeep on 02/01/2020.
     * This function is used to kill all the active browser driver instances.
     * @throws IOException
     */
    public static void killBrowserDriverInstances() throws IOException {
        System.out.println("Killing all the open driver instances !!!");
        if(Constants.operatingSystemName.startsWith("MAC")) {
            if (Constants.browserName.equalsIgnoreCase("Chrome")) {
                Runtime.getRuntime().exec("ps auxww | grep -i 'chromedriver' | grep -v grep | awk '{ print $2 }' | xargs kill -9");
                System.out.println("Chrome Driver instances closed on "+Constants.operatingSystemName);
            } else if (Constants.browserName.equalsIgnoreCase("Firefox")) {
                Runtime.getRuntime().exec("ps auxww | grep -i 'geckodriver' | grep -v grep | awk '{ print $2 }' | xargs kill -9");
                System.out.println("Firfox Driver instances closed on "+Constants.operatingSystemName);
            } else {
                System.out.println("Drivers not available for: "+Constants.operatingSystemName);
            }
        } else if(Constants.operatingSystemName.startsWith("WINDOWS")){
            if (Constants.browserName.equalsIgnoreCase("Chrome")) {
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
                System.out.println("Chrome Driver instances closed on "+Constants.operatingSystemName);
            } else if (Constants.browserName.equalsIgnoreCase("Firefox")) {
                Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
                System.out.println("Firfox Driver instances closed on "+Constants.operatingSystemName);
            } else if (prop.getProperty("BROWSER").equalsIgnoreCase("IE")) {
                Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe /T");
                System.out.println("IE Driver instances closed on "+Constants.operatingSystemName);
            } else {
                System.out.println("Drivers not available for: "+Constants.operatingSystemName);
            }
        } else {
            System.out.println("Automation suite doesn't support this OS: "+Constants.operatingSystemName);
        }
    }
    
    /**
     * Created by: Navdeep on 12/31/2019.
     * This function returns the current Url of the page
     * @return: current page Url
     */
    public String getCurrentURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            throw new TestException(String.format("Current URL is: %s", driver.getCurrentUrl()));
        }
    }

    /**
     * Created by: Navdeep on 12/31/2019.
     * This function returns the current title of the page
     * @return: current page Title
     */
    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            throw new TestException(String.format("Current page title is: %s", driver.getTitle()));
        }
    }

    /**
     * Created by: Navdeep on 12/31/2019.
     * This function will navigate back to the previous visited page
     */
    public void navigateBack() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            System.out.println("FAILURE: Could not navigate back to previous page.");
            throw new TestException("Could not navigate back to previous page.");
        }
    }

    /**
     * Created by: Navdeep on 12/31/2019.
     * This function will navigate forward to the previous visited page
     */
    public void navigateForward() {
        try {
            driver.navigate().forward();
        } catch (Exception e) {
            System.out.println("FAILURE: Could not navigate forward to previous page.");
            throw new TestException("Could not navigate forward to previous page.");
        }
    }

    /**
     * Created by: Navdeep on 12/31/2019.
     * This function will refresh the current page
     */
    public void pageRefresh() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            System.out.println("FAILURE: Could not able to refresh the page.");
            throw new TestException("Could not able to refresh the page.");
        }
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new TestException("Could not able to sleep wait. "+e);
        }
    }

    /**
     * Created by: Navdeep on 01/01/2020.
     * This function takes screenshots of the web page
     * @return path of the screenshot file
     */
    public String getScreenshot() {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            System.out.println("Capture Failed " + e);
        }
        return path;
    }

    /**
     * Created by: Navdeep on 01/01/2020.
     * This function logs message both on terminal as well as in the extent reports.
     * @param message
     */
    public static void logInfo(String message) {
        System.out.println(message);
        Constants.test.get().log(Status.INFO, message);
    }

    /*      ----- Common Utils using By locator -----      */

    public void waitForElementToBeVisible(By selector) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("The following element was not visible: %s", selector));
        }
    }

    public void waitForElementToBeClickable(By selector) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
            throw new TestException("The following element is not clickable: " + selector);
        }
    }

    public WebElement getElement(By selector) {
        try {
            return driver.findElement(selector);
        } catch (Exception e) {
            System.out.println(String.format("Element %s does not exist - proceeding", selector));
        }
        return null;
    }

    public void sendKeys(By selector, String value) {
        WebElement element = getElement(selector);
        clear(element);
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            throw new TestException(String.format("Error in sending [%s] to the following element: [%s]", value, selector.toString()));
        }
    }

    public void click(By selector) {
        WebElement element = getElement(selector);
        waitForElementToBeClickable(selector);
        try {
            element.click();
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", selector));
        }
    }

    public void scrollToThenClick(By selector) {
        WebElement element = driver.findElement(selector);
        actions = new Actions(driver);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            actions.moveToElement(element).perform();
            actions.click(element).perform();
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", element.toString()));
        }
    }



    /*      ----- Common Utils using WebElement locator -----      */

    public void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
        try {
            timeout = timeout != null ? timeout: 5;
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(condition);
        } catch (Exception e) {
            System.out.println("The following element is Not Visible: " + e.getMessage());
        }
    }

    public Boolean waitForElementToBeVisible(WebElement element, Integer... timeout) {
        try {
            waitFor(ExpectedConditions.visibilityOf(element),
                    timeout.length > 0 ? timeout[0] : null);
        } catch (Exception e) {
            System.out.println("The following element is Not Visible: " + e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean waitForElementToBeClickable(WebElement element, Integer... timeout) {
        try {
            waitFor(ExpectedConditions.elementToBeClickable(element),
                    timeout.length > 0 ? timeout[0] : null);
        } catch (Exception e) {
            System.out.println("The following element is Not Clickable: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not displayed: %s", e.getMessage()));
        }
    }

    public String getText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            throw new TestException(String.format("The following element Text could not be fetched: %s", e.getMessage()));
        }
    }

    /**
     * Created by: Navdeep on 12/31/2019.
     * This function will clear the element text from the input box
     * @param element
     */
    public void clear(WebElement element) {
        try {
            element.clear();
            waitForElementTextToBeEmpty(element);
        } catch (Exception e) {
            System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
        }
    }

    /**
     * Created by: Navdeep on 12/31/2019.
     * This function will click the element
     * @param element
     */
    public void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            System.out.println(String.format("The following element is not clickable: [%s]" + e));
        }
    }

    public void sendKeys(WebElement element, String inputText) {
        try {
            element.sendKeys(inputText);
        } catch (Exception e) {
            System.out.println(String.format("The following element is unable to write text: [%s]"+inputText+" : " + e));
        }
    }

    public void waitForElementTextToBeEmpty(WebElement element) {
        String text;
        try {
            text = element.getText();
            int maxRetries = 10;
            int retry = 0;
            while ((text.length() >= 1) || (retry < maxRetries)) {
                retry++;
                text = element.getText();
            }
        } catch (Exception e) {
            System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
        }

    }

}
