package selenium4concepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.locators.RelativeLocator.*;
import utilities.TestUtil;

public class RelativeLocatorConcept extends TestUtil {
/*
Relative Locators
This is a new feature of Selenium 4. Generally, when starting to write automation tests, you can find the element using by CSS or Xpath locators such as find by ID, name, link text, etc.
Now with Selenium 4, you can find your element using Relative Locators. This means you can find your element that is close to other ones.

We have several methods to find a close-by (relative) element. These are:

toLeftOf() : Element located to the left of specified element.
toRightOf() : Element located to the right of the specified element.
above() : Element located above with respect to the specified element.
below() : Element located below with respect to the specified element.
near() : Element is at most 50 pixels far away from the specified element. The pixel value can be modified.
*/

//    WebElement e1 = driver.findElement(RelativeLocator.withTagName("p").above(By.id("userName")));

    // import static org.openqa.selenium.support.locators.RelativeLocator.*;
    WebElement e1 = driver.findElement(withTagName("p").above(By.id("userName")));

    WebElement e2 = driver.findElement(withTagName("p").below(By.id("userName")));

    WebElement e3 = driver.findElement(withTagName("p").near(By.id("userName")));

    WebElement e4 = driver.findElement(withTagName("p").toLeftOf(By.id("userName")));

    WebElement e5 = driver.findElement(withTagName("p").toRightOf(By.id("userName")));

    WebElement e6 = driver.findElement(withTagName("p").below(By.id("userName")).above(By.id("signIn")).toRightOf(By.name("Password")));

}
