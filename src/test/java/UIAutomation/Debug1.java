package UIAutomation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 *  WebDriver     TakeScreenhot       JavaScriptExecutor   <===== these are interfaces
 *  Now there are RemoteWebDriver <========== this is class which implements above interfaces
 *  Then there are ChromeDriver, EdgeDriver, FireFox driver which extends RemoteWebDriver  
 *  
 *      WebDriver driver = new ChromeDriver();
 */

public class Debug1 {
	WebDriver driver;
	
	@Test
	public void abcd() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.easemytrip.com/");

		TakesScreenshot sc = (TakesScreenshot)driver;
		File file = sc.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file , new File("./ScreenShot/image.png"));
		
		JavascriptExecutor je = (JavascriptExecutor)driver;
		String title = (String)je.executeScript("return document.title");
		System.out.println(title);
		
		
	}

}
