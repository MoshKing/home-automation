package home.automation.web.util;

import com.qaprosoft.carina.core.foundation.utils.R;
import home.automation.constant.ConfigConstant;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static List<String> getCreateAccountPageErrorMessages() {
        List<String> errorMessages = new ArrayList();
        errorMessages.add(R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.FIRST_NAME)));
        errorMessages.add(R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.LAST_NAME)));
        errorMessages.add(R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.PASSWORD)));
        errorMessages.add(R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.ADDRESS)));
        errorMessages.add(R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.STATE)));
        errorMessages.add(R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.ZIPCODE)));
        errorMessages.add(R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.PHONE_NUMBER)));
        errorMessages.add(R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, ConfigConstant.CITY)));
        return errorMessages;
    }

    public static List<String> getCreateAccountPageRequiredFieldsLabels() {
        List<String> fieldLabel = new ArrayList();
        fieldLabel.add(R.TESTDATA.get(String.format(ConfigConstant.REQUIRED_FIELD_KEY, ConfigConstant.FIRST_NAME)));
        fieldLabel.add(R.TESTDATA.get(String.format(ConfigConstant.REQUIRED_FIELD_KEY, ConfigConstant.LAST_NAME)));
        fieldLabel.add(R.TESTDATA.get(String.format(ConfigConstant.REQUIRED_FIELD_KEY, ConfigConstant.PASSWORD)));
        fieldLabel.add(R.TESTDATA.get(String.format(ConfigConstant.REQUIRED_FIELD_KEY, ConfigConstant.ADDRESS)));
        fieldLabel.add(R.TESTDATA.get(String.format(ConfigConstant.REQUIRED_FIELD_KEY, ConfigConstant.STATE)));
        fieldLabel.add(R.TESTDATA.get(String.format(ConfigConstant.REQUIRED_FIELD_KEY, ConfigConstant.ZIPCODE)));
        fieldLabel.add(R.TESTDATA.get(String.format(ConfigConstant.REQUIRED_FIELD_KEY, ConfigConstant.PHONE_NUMBER)));
        fieldLabel.add(R.TESTDATA.get(String.format(ConfigConstant.REQUIRED_FIELD_KEY, ConfigConstant.CITY)));
        fieldLabel.add(R.TESTDATA.get(String.format(ConfigConstant.REQUIRED_FIELD_KEY, ConfigConstant.EMAIL)));
        return fieldLabel;
    }
}