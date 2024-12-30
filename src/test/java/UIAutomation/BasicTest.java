package UIAutomation;

import java.time.Duration;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import locators.LocatorManager;
import locators.LoginPageLocators;
import utility.helperClass;

public class BasicTest {
	WebDriver driver;
	private String username;
	private String password;
	helperClass helper = new helperClass(driver);
	LoginPageLocators loginPageLocators = new LoginPageLocators();
	LocatorManager locatorManager = new LocatorManager(driver);

	public BasicTest() {
		this.username = "Admin";
		this.password = "admin123";
	}

	@BeforeTest
	public void initializeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		helper.setBrowserScreenSize(1200, 1200, driver);
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Test(priority = 1, enabled = true)
	public void launchURL() {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		helper.getLocator("selectDate", "1");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(LoginPageLocators.userNameTextBox)));
		driver.findElement(locatorManager.getLocator("userNameTextBox")).sendKeys(username);
		driver.findElement(locatorManager.getLocator("passwordTextBox")).sendKeys(password);
		driver.findElement(locatorManager.getLocator("loginButton")).click();
		helper.wait(3);
		helper.clickOnElement("pimOtpion", driver);
		helper.elementIsDisplayed(locatorManager.getLocator("employeeInfoText"), true, driver);
	}

	@Test(priority = 2, enabled = true)
	public void employeePage() {
		FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(500)).ignoring(ElementNotInteractableException.class);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(LoginPageLocators.addEmployeeText)));
		helper.clickOnElement("addEmployeeText", driver);
		helper.wait(3);
		helper.verifyTextIsMatching((LoginPageLocators.addEmployeeText), "Add Employee", driver);
	}

	@Test(priority = 3, enabled = true)
	public void addEmployee() {
		helper.enterText((LoginPageLocators.firstNameTextBox), "Atharv", driver);
		helper.enterText((LoginPageLocators.middleNameTextBox), "middleName", driver);
		helper.enterText((LoginPageLocators.lastNameTextBox), "lastName", driver);
		helper.clickOnElement("employeeList", driver);
		helper.wait(5);
	}

	@Test(priority = 4, enabled = true)
	public void employeeIDList() {
		helper.verifyListSize(50, (LoginPageLocators.employeeIDList), driver);
	}

	@Test(priority = 5, enabled = true)
	public void aboutPopUpBox() {
		helper.clickOnElement("userDropDown", driver);
		helper.clickOnElement("userDropDownAbout", driver);
		int ele = helper.getElementsNumber(LoginPageLocators.aboutPopUpBoxContents, driver);
		for (int i = 0; i < ele; i++) {
			String abc = helper.getElements(LoginPageLocators.aboutPopUpBoxContents, driver).get(i).getText();
			System.out.println("about box contents is " + abc);
		}
		helper.clickOnElement("aboutPopUpBoxCloseBtn", driver);
	}

	@Test(priority = 6, enabled = true)
	public void checkLeaveList() throws Exception {
		helper.clickOnElement("leaveOtpion", driver);
		helper.wait(3);
		helper.clickOnElementUsingJE("fromDate", driver);
		helper.selectFromDate("30-Dec-2024", "selectDate", driver);
	}
	
	@Test(priority = 7, enabled =true)
	public void checkAssignLeave() throws Exception {
		helper.clickOnElement("assignLeaveText", driver);
		helper.clickOnElementUsingJE("searchEmployeeBox", driver);
		helper.sendKeys("searchEmployeeBox", "J", driver);
		helper.wait(3);
		helper.clickOnElement("firstEmployee", driver);
//		helper.selectValuesFromDropDown("leaveTypeDropDownBox", "CAN - FMLA", driver);
		helper.clickOnElement("leaveTypeDropDownBox", driver);
		helper.clickOnElement("leaveTypeOne", driver);
		helper.clickOnElementUsingJE("fromDate", driver);
		helper.selectFromDate("30-Dec-2024", "selectDate", driver);
		helper.wait(3);
//		helper.sendKeys("commentBox", "I am OOO on upcoming Monday", driver);
		helper.clickOnElement("assignButton", driver);
		helper.elementIsDisplayed(LoginPageLocators.confirmLeaveAlertBox, true, driver);
		helper.clickOnElement("acceptConfirmLeaveAlertBox", driver);
	}

	@AfterTest
	public void captureScreenshot() {
		helper.captureScreenShot(driver);
	}

	@AfterTest
	public void quitBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

}
