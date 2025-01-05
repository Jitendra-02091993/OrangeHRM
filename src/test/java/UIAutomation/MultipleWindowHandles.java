package UIAutomation;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultipleWindowHandles {
	private WebDriver driver;
	@Test
	public void windowHandles() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.hyrtutorials.com/p/window-handles-practice.html");
		driver.manage().window().maximize();
		String parentWindow = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//button[@id=\"newWindowBtn\"]")).click();
//		Set<String> list = driver.getWindowHandles();
		
		for(String allWindows : driver.getWindowHandles()) {
			if(!allWindows.equals(parentWindow)) {
				driver.switchTo().window(allWindows);
				System.out.println(driver.getTitle());
			}
		}
		
		WebElement ele = driver.findElement(By.xpath("//input[@id=\"firstName\"]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.sendKeys("WIP:");
		Thread.sleep(5000);
		driver.	close(); // <======= this will close current active browser to which driver is currently active
		driver.quit(); // <======= this will close all the windows which is associated with driver instance
	}

}
