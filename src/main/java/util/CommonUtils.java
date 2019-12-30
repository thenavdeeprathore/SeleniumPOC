package util;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

public class CommonUtils extends TestBase {

    protected static WebDriverWait wait;
    private static int timeout = 10;
    protected Actions actions;

    public String getCurrentURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            throw new TestException(String.format("Current URL is: %s", driver.getCurrentUrl()));
        }
    }

    public void waitForElementToBeVisible(By selector) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("The following element was not visible: %s", selector));
        }
    }

    public void clearField(WebElement element) {
        try {
            element.clear();
            waitForElementTextToBeEmpty(element);
        } catch (Exception e) {
            System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
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
        clearField(element);
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

    public void waitForElementToBeClickable(By selector) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
            throw new TestException("The following element is not clickable: " + selector);
        }
    }

    public void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
        try {
            timeout = timeout != null ? timeout: 5;
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(condition);
        } catch (Exception e) {
            System.out.println("The following element is Not Visible: " + e.getMessage());
        }
    }

    public Boolean waitForVisibility(WebElement element, Integer... timeout) {
        try {
            waitFor(ExpectedConditions.visibilityOf(element),
                    timeout.length > 0 ? timeout[0] : null);
        } catch (Exception e) {
            System.out.println("The following element "+element+" is Not Visible: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            System.out.println("The following element "+element+" is Not clickable: " + e);
        }
    }

    public void clear(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            System.out.println("The following element "+element+" can't be cleared: " + e);
        }
    }

    public void sendKeys(WebElement element, String inputText) {
        try {
            element.sendKeys(inputText);
        } catch (Exception e) {
            System.out.println("The following element "+element+" unable to write text "+inputText+": " + e);
        }
    }

    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            throw new TestException(String.format("Current page title is: %s", driver.getTitle()));
        }
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println("Caught exception during sleep wait "+e);
        }
    }


}
