package home.automation.web.gui.pages;

import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BaseProjectPage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("http://automationpractice.com/index.php?controller=my-account");
    }
}