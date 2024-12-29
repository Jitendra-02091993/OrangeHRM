package locators;
import org.openqa.selenium.By;

public class LoginPageLocators {
	
	public static By userNameTextBox = By.xpath("//input[@name=\"username\"]");
	public static By passwordTextBox = By.xpath("//input[@name=\"password\"]");
	public static By loginButton = By.xpath("//*[text()=' Login ']");
	public static By pimOtpion = By.xpath("//nav[@class=\"oxd-navbar-nav\"]/child::div[2]/child::ul/li[2]");
	public static By employeeInfoText = By.xpath("//*[text()=\"Employee Information\"]");
	public static By addText = By.xpath("//button[text()=\" Add \"]");
	public static By addEmployeeText = By.xpath("//a[text()=\"Add Employee\"]");	
	public static By firstNameTextBox = By.xpath("//input[@name=\"firstName\"]");
	public static By middleNameTextBox = By.xpath("//input[@name=\"middleName\"]");
	public static By lastNameTextBox = By.xpath("//input[@name=\"lastName\"]");
	public static By employeeList = By.xpath("//a[text()=\"Employee List\"]");
	public static By employeeIDList = By.xpath("//div[@class=\"oxd-table-body\"]/div/child::div//div[2]");
	public static By aboutPopUpBoxCloseBtn = By.xpath("//div[@role=\"document\"]/button");
	public static By aboutPopUpBoxContents = By.xpath("//div[@role=\"document\"]/div[2]//div/p");
	public static By userDropDown = By.xpath("//p[@class=\"oxd-userdropdown-name\"]");
	public static By userDropDownAbout = By.xpath("//ul[@role=\"menu\"]/li[1]");
	public static By leaveOtpion = By.xpath("//nav[@class=\"oxd-navbar-nav\"]/child::div[2]/child::ul/li[3]");
	public static By fromDate = By.xpath("(//div[@class=\"oxd-date-input\"])[1]//i");
	public static By toDate = By.xpath("(//div[@class=\"oxd-date-input\"])[2]");
	public static By chevronRightButton = By.xpath("//i[@class=\"oxd-icon bi-chevron-right\"]");
	public static By chevronLeftButton = By.xpath("//i[@class=\"oxd-icon bi-chevron-left\"]");
	public static By selectDate = By.xpath("//div[@class=\"oxd-calendar-dates-grid\"]/div[replace]");
	public static By assignLeaveText = By.xpath("//a[normalize-space()='Assign Leave']");
	public static By searchEmployeeBox = By.xpath("//input[@placeholder='Type for hints...']");
	public static By leaveTypeDropDownBox = By.xpath("//div[contains(text(),\"-- Select --\")]");
	public static By commentBox = By.xpath("//*[contains(text(),\"Comments\")]/../following-sibling::div");
	public static By firstEmployee = By.xpath("//div[@class=\"oxd-autocomplete-option\"][position()=1]");
	public static By assignButton = By.xpath("//button[normalize-space()='Assign']");	
	public static By confirmLeaveAlertBox = By.xpath("//div[@role='document']");
	public static By acceptConfirmLeaveAlertBox = By.xpath("//button[normalize-space()='Ok']");
	public static By leaveTypeOne = By.xpath("//span[text()=\"CAN - Bereavement\"]");
	
	
	
	
	
}
