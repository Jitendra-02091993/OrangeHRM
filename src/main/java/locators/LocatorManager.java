package locators;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LocatorManager {
	private WebDriver driver;
    private Map<String, By> locatorsMap;
    public static String fieldName="";

    // Constructor
    public LocatorManager(WebDriver driver) {
        this.driver = driver;
        this.locatorsMap = new HashMap<>();
        loadLocators();
    }

    // Load locators from specified classes
    private void loadLocators() {
        try {
            // List of locator classes to load
            Class<?>[] locatorClasses = {LoginPageLocators.class, HomePageLocators.class};

            for (Class<?> locatorClass : locatorClasses) {
                Field[] fields = locatorClass.getDeclaredFields();
                for (Field field : fields) {
                    if (By.class.isAssignableFrom(field.getType())) {
                        fieldName = field.getName();
                        By locator = (By) field.get(null); // Get the static field value
                        locatorsMap.put(fieldName, locator);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // Method to get the locator by name
    public By getLocator(String name) {
        return locatorsMap.get(name);
    }

}
