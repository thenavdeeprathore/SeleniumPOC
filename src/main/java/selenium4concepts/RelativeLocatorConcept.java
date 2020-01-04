package selenium4concepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.locators.RelativeLocator.*;
import utilities.TestUtil;

public class RelativeLocatorConcept extends TestUtil {

//    WebElement e1 = driver.findElement(RelativeLocator.withTagName("p").above(By.id("userName")));

    // import static org.openqa.selenium.support.locators.RelativeLocator.*;
    WebElement e1 = driver.findElement(withTagName("p").above(By.id("userName")));

    WebElement e2 = driver.findElement(withTagName("p").below(By.id("userName")));

    WebElement e3 = driver.findElement(withTagName("p").near(By.id("userName")));

    WebElement e4 = driver.findElement(withTagName("p").toLeftOf(By.id("userName")));

    WebElement e5 = driver.findElement(withTagName("p").toRightOf(By.id("userName")));

    WebElement e6 = driver.findElement(withTagName("p").below(By.id("userName")).above(By.id("signIn")).toRightOf(By.name("Password")));

}
