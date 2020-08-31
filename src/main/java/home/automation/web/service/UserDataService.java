package home.automation.web.service;

import com.qaprosoft.carina.core.foundation.utils.R;
import home.automation.util.CryptoUtil;
import home.automation.web.domain.UserData;
import home.automation.web.domain.UserType;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;
import java.util.stream.IntStream;

public class UserDataService {

    private final static String DEFAULT_USER_DATA_KEY = "user.default.%s";

    public static UserData generateNewUser() {
        UserData userData = new UserData();
        String email = RandomStringUtils.random(8, true, false).concat("@email.com").toLowerCase();
        String phoneNumber = generatePhoneNumber();
        userData.setEmail(email);
        userData.setMobilePhone(phoneNumber);
        userData.setPassword(CryptoUtil.decrypt(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "password"))));
        userData.setFirstName(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "first_name")));
        userData.setLastName(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "last_name")));
        userData.setAddress(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "address")));
        userData.setCity(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "city")));
        userData.setState(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "state")));
        userData.setZipcode(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "zipcode")));
        userData.setCountry(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "country")));
        userData.setUserType(UserType.NEW);
        return userData;
    }

    public static UserData getRegisteredUser() {
        UserData userData = new UserData();
        userData.setEmail(R.TESTDATA.get("user1"));
        userData.setPassword(R.TESTDATA.get("password1"));
        userData.setUserType(UserType.REGISTERED);
        userData.setFirstName(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "first_name")));
        userData.setLastName(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "last_name")));
        userData.setAddress(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "address")));
        userData.setCity(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "city")));
        userData.setState(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "state")));
        userData.setZipcode(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "zipcode")));
        userData.setCountry(R.TESTDATA.get(String.format(DEFAULT_USER_DATA_KEY, "country")));
        userData.setMobilePhone(R.TESTDATA.get("phone_number1"));
        return userData;
    }

    private static String generatePhoneNumber() {
        StringBuilder stringBuilder = new StringBuilder("+1202");
        IntStream.range(0, 7).forEach(i -> stringBuilder.append(new Random().nextInt(9)));
        return stringBuilder.toString();
    }
}