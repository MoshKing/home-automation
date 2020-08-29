package home.automation.web.gui.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class BaseProjectUIObject extends AbstractUIObject {
    public BaseProjectUIObject(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
}