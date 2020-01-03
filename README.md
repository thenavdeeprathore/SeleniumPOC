## Selenium Java TDD Automation POC: www.amazon.com
This is a web automation framework, implemented using (Java + Selenium Webdriver, TestNG & Maven).

Page Object Model (POM) design is used to make this code more readable, maintainable, and reusable.

## _Prerequisite:_
* Java
* Maven
* Browsers (Chrome, Firefox, Safari, IE)

## Framework Features:
* Config for Operating Systems:
    * Mac
    * Windows
* Config for various browsers for latest browser versions:
    * Mac
        * Chrome
        * Firefox
        * Safari
        * Headless Chrome
    * Windows
        * Chrome
        * Firefox
        * IE
        * Headless Chrome
* Config listeners
    * ITestListener
    * IInvokedMethodListener
    * ExtentReportListener
* Config to generate overridden HTML reports per Test Suite wise.
* Config to kill all the launched browser instances.
* Config to get screenshots for the failed test cases.
* Config for BrowserStack.



## Steps to clone the project and execute the tests:

* `git clone https://github.com/thenavdeeprathore/SeleniumPOC.git`
* `cd SeleniumPOC`
* `mvn clean install -Dsurefire.suiteXmlFiles=src/main/resources/testng.xml`

