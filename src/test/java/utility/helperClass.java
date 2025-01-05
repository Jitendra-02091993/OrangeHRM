package utility;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import locators.LocatorManager;

public class helperClass {
	private WebDriver driver;
	public String text = "";
	int elementSize;
	LocatorManager locatorManager = new LocatorManager(driver);
	JavascriptExecutor js;

	public helperClass(WebDriver driver) {
		this.driver = driver;
	}

	public void wait(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickOnElement(String eleFieldName, WebDriver driver) {
		By element = locatorManager.getLocator(eleFieldName);
		WebElement ele = driver.findElement(element);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", ele);
		explicitlyWait(element, 5, driver);
		boolean status = ele.isDisplayed();
		ele.click();
		wait(2);
		Assert.assertEquals(status, true);
		System.out.println("Clicked on element: " + eleFieldName);
	}

	public void sendKeys(String eleFieldName, String text, WebDriver driver) {
		By element = locatorManager.getLocator(eleFieldName);
		WebElement ele = driver.findElement(element);
		ele.click();
		ele.sendKeys(text);
	}

	public void clickOnElementUsingJE(String eleFieldName, WebDriver driver) {
		By by = locatorManager.getLocator(eleFieldName);
		WebElement ele = driver.findElement(by);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
		System.out.println("Clicked on element: " + eleFieldName);
	}

	public void clickOnDynamicElement(String elementFieldName, String replace, WebDriver driver) {
		By by = getLocator(elementFieldName, replace);
		WebElement ele = driver.findElement(by);
		explicitlyWait(by, 5, driver);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", ele);
		ele.click();
		System.out.println("Clicked on element: " + elementFieldName);
	}

	public boolean elementIsDisplayed(By by, boolean value, WebDriver driver) {
		WebElement ele = driver.findElement(by);
		boolean status = ele.isDisplayed();
		Assert.assertEquals(status, value);
		return status;
	}

	public void verifyTextIsMatching(By by, String expectedText, WebDriver driver) {
		try {
			boolean status = elementIsDisplayed(by, true, driver);
			if (status != false) {
				WebElement ele = driver.findElement(by);
				text = ele.getText();
				Assert.assertEquals(text, expectedText);
			}
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		}
	}

	public void enterText(By by, String text, WebDriver driver) {
		try {
			WebElement ele = driver.findElement(by);
			boolean status = elementIsDisplayed(by, true, driver);
			if (status != false) {
				ele.sendKeys(text);
			}
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		}
	}

	public void captureScreenShot(WebDriver driver) {
		// Cast WebDriver to TakesScreenshot
		TakesScreenshot ts = (TakesScreenshot) driver;

		// Capture the screenshot and store it as a file
		File screenshotFile = ts.getScreenshotAs(OutputType.FILE);

		// Define the path where you want to save the screenshot
		File destinationFile = new File("Screenshots/Screenshot.png");

		try {
			// Ensure the directory exists
			destinationFile.getParentFile().mkdirs();

			// Copy the screenshot file to the destination
			FileUtils.copyFile(screenshotFile, destinationFile);
			System.out.println("Screenshot saved at: " + destinationFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to save screenshot: " + e.getMessage());
		}
	}

	public List<WebElement> getElements(By by, WebDriver driver) {
		List<WebElement> ele = driver.findElements(by);
		return ele;
	}

	public Integer getElementsNumber(By by, WebDriver driver) {
		List<WebElement> ele = getElements(by, driver);
		if (!ele.isEmpty()) {
			elementSize = ele.size();
			for (int i = 0; i < elementSize; i++) {
				System.out.println(ele.get(i).getText());
			}
			return elementSize;
		} else {
			return null;
		}
	}

	public void verifyListSize(int expectedValue, By by, WebDriver driver) {
		int elementSize = getElementsNumber(by, driver);
		Assert.assertEquals(elementSize, expectedValue);
		System.out.println("Element size is matched ==> " + "expected: " + expectedValue + " actual: " + elementSize);
	}

	public void implicityWait(int sec, WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}

	public void explicitlyWait(By by, int sec, WebDriver driver) {
		WebElement ele = driver.findElement(by);
		try {
			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
			wait.until(ExpectedConditions.elementToBeClickable(by));
			wait.until(d -> {
				ele.isDisplayed();
				return true;
			});
		} catch (Exception e) {
			System.out.println("Error clicking element: " + e.getMessage());
		}
	}

	public void fluentWait(By by, int sec, WebDriver driver) {
		WebElement ele = driver.findElement(by);
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(sec))
				.pollingEvery(Duration.ofMillis(500)).ignoring(ElementNotInteractableException.class);
		wait.until(d -> {
			ele.isDisplayed();
			return true;
		});
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void setBrowserScreenSize(int width, int height, WebDriver driver) {
		Dimension dimension = new Dimension(width, height);
		driver.manage().window().setSize(dimension);
	}

	public void selectFromDate(String fromDate, String eleFiledName, WebDriver driver) throws Exception {
		Date formattedTargetDate;
//		fromDate = 22-Sep-2024
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		targetDateFormat.setLenient(false);
		try {
			formattedTargetDate = targetDateFormat.parse(fromDate);
			calendar.setTime(formattedTargetDate);
			int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
			int targetMonth = calendar.get(Calendar.MONTH);
			int targetYear = calendar.get(Calendar.YEAR);
//			clickOnElement("chevronRightButton", driver);
			clickOnDynamicElement(eleFiledName, Integer.toString(targetDay), driver);
			System.out.println("Clicked on element: " + eleFiledName);
		} catch (ParseException e) {
			throw new Exception("Invalid formate date");
		}
	}

	public By getLocator(String elementFieldName, String replace) {
		// Get the locator from LocatorManager
		By locator = locatorManager.getLocator(elementFieldName);

		// Check if the locator is null
		if (locator == null) {
			throw new IllegalArgumentException("Locator not found for: " + elementFieldName);
		}

		// Assuming that 'locator' is an XPath or CSS selector, modify it
		String locatorString = locator.toString();

		// Extracting the actual XPath/CSS selector from By object
		String modifiedLocatorString = locatorString.replace("By.xpath: ", "").replace("replace", replace);

		// Create a new By object based on modified string
		By finalElement = By.xpath(modifiedLocatorString); // Or use By.cssSelector(modifiedLocatorString) if it's CSS
		return finalElement;
	}

	public void selectValuesFromDropDown(String eleFiledName, String value, WebDriver driver) {
		By by = locatorManager.getLocator(eleFiledName);
		WebElement ele = driver.findElement(by);
		// Create a Select object
		Select dropdown = new Select(ele);
		dropdown.selectByValue(value);
	}

}
