package home.automation.web.util;

import com.qaprosoft.carina.core.foundation.webdriver.IDriverPool;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JSUtil implements IDriverPool {

    private final static Logger LOGGER = Logger.getLogger(JSUtil.class);

    private JSUtil() {
    }

    public static void type(WebDriver driver, ExtendedWebElement element, String text) {
        LOGGER.info(String.format("Typing text '%s' by JS in element '%s'", text, element.getNameWithLocator()));
        ((JavascriptExecutor) driver).executeScript(String.format("arguments[0].value='%s'", text), element.getElement());
    }

    public static void clickElement(WebDriver driver, ExtendedWebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element.getElement());
        LOGGER.info("Click with JS for element " + element.toString() + " was successfully completed");
    }
}