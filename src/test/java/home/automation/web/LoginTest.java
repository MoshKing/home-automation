package home.automation.web;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import home.automation.BaseProjectTest;
import home.automation.constant.ConfigConstant;
import home.automation.web.domain.UserData;
import home.automation.web.gui.components.TopMainMenu;
import home.automation.web.gui.pages.AccountCreationPage;
import home.automation.web.gui.pages.AuthorizationPage;
import home.automation.web.gui.pages.MyAccountPage;
import home.automation.web.service.UserDataService;
import home.automation.web.util.TestDataUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseProjectTest {

    @MethodOwner(owner = "dprymudrau")
    @Test(description = "")
    public void testSigUnFormErrorMessages() {
        SoftAssert softAssert = new SoftAssert();
        UserData userData = UserDataService.generateNewUser();

        //Open Account creation page from Top Main Menu
        TopMainMenu topMainMenu = openHomePage().getTopMainMenu();
        AuthorizationPage authorizationPage = topMainMenu.clickSignInBtn();
        authorizationPage.typeNewUserEmail(userData.getEmail());
        AccountCreationPage accountCreationPage = authorizationPage.clickCreateAnAccountBtn();

        //Validate all error messages
        accountCreationPage.clickRegisterBtn();
        accountCreationPage.validateErrorsAmountMessage(softAssert, TestDataUtil.getCreateAccountPageErrorMessages().size());
        accountCreationPage.validateErrorMessages(softAssert, TestDataUtil.getCreateAccountPageErrorMessages());
        accountCreationPage.validateRequiredFields(softAssert, TestDataUtil.getCreateAccountPageRequiredFieldsLabels());

        //Type data in required fields and validate that error message disappears
        String errorMessage = R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.FIRST_NAME));
        accountCreationPage.typeFirstName(userData.getFirstName());
        accountCreationPage.clickRegisterBtn();
        accountCreationPage.validateErrorsAmountMessage(softAssert, 7);
        softAssert.assertFalse(accountCreationPage.isErrorPresent(errorMessage), String.format("Error '%s' message present!", errorMessage));

        errorMessage = R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.LAST_NAME));
        accountCreationPage.typeLastName(userData.getLastName());
        accountCreationPage.clickRegisterBtn();
        accountCreationPage.validateErrorsAmountMessage(softAssert, 6);
        softAssert.assertFalse(accountCreationPage.isErrorPresent(errorMessage), String.format("Error '%s' message present!", errorMessage));

        errorMessage = R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.ADDRESS));
        accountCreationPage.typeAddressLine1(userData.getAddress());
        accountCreationPage.clickRegisterBtn();
        accountCreationPage.validateErrorsAmountMessage(softAssert, 5);
        softAssert.assertFalse(accountCreationPage.isErrorPresent(errorMessage), String.format("Error '%s' message present!", errorMessage));

        errorMessage = R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.CITY));
        accountCreationPage.typeCity(userData.getCity());
        accountCreationPage.clickRegisterBtn();
        accountCreationPage.validateErrorsAmountMessage(softAssert, 4);
        softAssert.assertFalse(accountCreationPage.isErrorPresent(errorMessage), String.format("Error '%s' message present!", errorMessage));

        errorMessage = R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.STATE));
        accountCreationPage.selectState(userData.getState());
        accountCreationPage.clickRegisterBtn();
        accountCreationPage.validateErrorsAmountMessage(softAssert, 3);
        softAssert.assertFalse(accountCreationPage.isErrorPresent(errorMessage), String.format("Error '%s' message present!", errorMessage));

        errorMessage = R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.ZIPCODE));
        accountCreationPage.typeZipcode(userData.getZipcode());
        accountCreationPage.clickRegisterBtn();
        accountCreationPage.validateErrorsAmountMessage(softAssert, 2);
        softAssert.assertFalse(accountCreationPage.isErrorPresent(errorMessage), String.format("Error '%s' message present!", errorMessage));

        errorMessage = R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.PHONE_NUMBER));
        accountCreationPage.typePhoneNumber(userData.getMobilePhone());
        accountCreationPage.clickRegisterBtn();
        accountCreationPage.validateErrorsAmountMessage(softAssert, 1);
        softAssert.assertFalse(accountCreationPage.isErrorPresent(errorMessage), String.format("Error '%s' message present!", errorMessage));

        accountCreationPage.typePassword(userData.getPassword());
        accountCreationPage.clickRegisterBtn();
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        softAssert.assertTrue(myAccountPage.isPageOpened(), "My Account Page isn't opened");
        softAssert.assertAll();
    }
}