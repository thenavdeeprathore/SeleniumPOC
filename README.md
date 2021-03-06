## Selenium Java TestNG Automation Framework(TDD) POC: www.amazon.com
This is a web automation framework, implemented using:
 * Programming Language: **Java**
 * Test Automation Tool: **Selenium WebDriver {version 4.0.0-alpha-3}**
 * Test Automation Framework: **TestNG (TDD)**
 * Build Tool: **Maven**
 * Version Control: **GIT**
 * IDE: **IntelliJ IDEA**

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
* Config for WebDriverManager
    * OS - {Mac, Win, Linux}
    * Browsers - {Chrome, Firefox, IE, Edge, Opera, Phantomjs}
    * Any Browser Versions.
* Config to suppress console log warnings.
* Config listeners:
    * ITestListener
    * IInvokedMethodListener
    * ExtentReportListener
* Config to generate overridden HTML reports per Test Suite wise.
* Config to kill all the launched browser instances.
* Config to get screenshots for the failed test cases.
* Config for BrowserStack.
* Config for Code Coverage using Jacoco.
* Additional Features:
    * Selenium 4 new locator feature: [Relative Locators](src/main/java/selenium4concepts/RelativeLocatorConcept.java)
    * Generate random unique test data for automation using [Java Faker](https://github.com/DiUS/java-faker)

## Amazon Site Test Repository:
* [Login](src/test/java/authentication/Login.java)
    * testHomeTitle
    * testUrlProtocol
    * testNavigateToSignIn
    * testSignIn

## Steps to clone the project and execute the tests:

* `git clone https://github.com/thenavdeeprathore/SeleniumPOC.git`
* `cd SeleniumPOC`
* `mvn clean install -Dsurefire.suiteXmlFiles=src/main/resources/testng.xml`
    * `# view test execution report at ‘report/QAReportSmoke.html’`
    * `# view code coverage report at ‘target/site/jacoco/index.html’`

## Steps to collaborate with this project:
* Create a fork on github to the **thenavdeeprathore/SeleniumPOC** repo.
* Clone the fork to your local machine:
    * `git clone http://github.com/(userName)/SeleniumPOC.git`
* `cd SeleniumPOC`
* Add a remote to the forked repo to keep your fork up-to-date:
    * `git remote add upstream https://github.com/thenavdeeprathore/SeleniumPOC.git`
* Verify new remote named upstream:
    * `git remote -v`
* To update fork with latest changes:
    * `git fetch upstream`
    * `git merge upstream/master`
* Checkout the master branch
    * `git checkout master`
* `git branch branchName`
* `git checkout branchName`
* Make your changes and commit them
* `git status`
* `git add .`
* `git commit -m "initial commit"`

* `git fetch upstream`
* `git merge upstream/master`
* `git rebase master`

* `git push origin branchName`

* `git push --set-upstream origin {branchName}`

* A pull request will be created on GitHub, send a link to the reviewer for approval.
* Click rebase and merge the code.
* Delete remote branch

* `git checkout master`
* `git branch -d branchName`
* `git push`