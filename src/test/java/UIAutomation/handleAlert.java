package UIAutomation;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class handleAlert {
	
	private WebDriver driver;
	@Test(priority =1, groups = {"smoke"})
	public void handleAlertBox() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.hyrtutorials.com/p/alertsdemo.html");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@id=\"alertBox\"]")).click();
		Alert ale = driver.switchTo().alert();
		System.out.println(ale.getText());
		Thread.sleep(3000);
		ale.accept();
}
	@Test (priority =2, enabled = true)
	public void handleConfirmAlertBox() throws Exception {
		driver.findElement(By.xpath("//button[@id=\"confirmBox\"]")).click();
		Alert ale = driver.switchTo().alert();
		System.out.println(ale.getText());
		Thread.sleep(3000);
		ale.accept();
}
	
	@Test (priority =3)
	public void handlePromptAlertBox() throws Exception {
		driver.findElement(By.xpath("//button[@id=\"promptBox\"]")).click();
		Alert ale = driver.switchTo().alert();
		ale.sendKeys("All well is happening in 2025");
		System.out.println(ale.getText());
		ale.accept();
		System.out.println(driver.findElement(By.xpath("//div[@id=\"output\"]")).getText());
}
	
	@AfterTest
	public void quitBrowser() {
		driver.quit();	
		}
}
