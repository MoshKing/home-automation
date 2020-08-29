package home.automation.web;

import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import home.automation.BaseProjectTest;
import home.automation.web.domain.ProductData;
import home.automation.web.domain.ProductSize;
import home.automation.web.domain.ProjectConstants;
import home.automation.web.domain.UserData;
import home.automation.web.gui.pages.*;
import home.automation.web.service.UserDataService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrderCreationTest extends BaseProjectTest {

    private final static String SEARCH_QUERY = "dress";
    private final static int ITEM_INDEX_IN_SEARCH_RESULTS = 2;
    private final static String ITEM_QTY = "2";

    @MethodOwner(owner = "dprymudrau")
    @Test(description = "Create an order as guest user validate that product data the same on each stage of creation")
    public void testGuestUserOrderCreation() {
        UserData userData = UserDataService.generateNewUser();
        SoftAssert softAssert = new SoftAssert();

        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();

        //Search an item and open its' details
        SearchResultsPage searchResultsPage = homePage.searchProducts(SEARCH_QUERY);
        ProductDetailPage productDetailPage = searchResultsPage.openProductDetails(ITEM_INDEX_IN_SEARCH_RESULTS);

        //Add product to cart from pdp
        ProductData productData = addProductToCart(softAssert, productDetailPage, ITEM_QTY, ProductSize.MEDIUM);
        CartPage cartPage = new CartPage(getDriver());
        cartPage.getCartItem(0).verifyProductData(softAssert, productData);
        cartPage.clickProceedToCheckoutBtn();

        //Create a new user
        AuthorizationPage authPage = new AuthorizationPage(getDriver());
        authPage.typeEmail(userData.getEmail());
        AccountCreationPage accountCreationPage = authPage.clickCreateAnAccountBtn();
        AddressesPage addressesPage = accountCreationPage.fillUserDataAndClickRegisterBtn(userData);

        //Validate shipping info and go to PaymentPage
        addressesPage.verifyDeliveryAddress(softAssert, userData);
        ShippingPage shippingPage = addressesPage.clickProceedToCheckoutBtn();
        String deliveryPrice = shippingPage.getDeliveryPrice();
        shippingPage.changeTermsAndConditionsCheckboxState(true);
        PaymentPage paymentPage = shippingPage.clickProceedToCheckoutBtn();

        //Validate order item info and create an order
        paymentPage.getOrderItem(0).verifyProductData(softAssert, productData);
        String expectedProductTotal = paymentPage.getOrderItem(0).getItemTotal();
        paymentPage.validateOrderTotals(softAssert, expectedProductTotal, deliveryPrice);

        //Validate price on order summary and confirmation pages
        String expectedTotal = ProjectConstants.CONCAT_DOLLAR.concat(String.valueOf(Double.parseDouble(expectedProductTotal.replaceAll(ProjectConstants.REPLACE_DOLLAR, "")) +
                Double.parseDouble(deliveryPrice.replaceAll(ProjectConstants.REPLACE_DOLLAR, ""))));
        OrderSummaryPage orderSummaryPage = paymentPage.clickPayByBankWireBtn();
        softAssert.assertEquals(orderSummaryPage.getOrderTotal(), expectedTotal, "Wrong order total on OrderSummaryPage!");
        OrderConfirmationPage orderConfirmationPage = orderSummaryPage.clickConfirmOrderBtn();
        softAssert.assertEquals(orderConfirmationPage.getOrderTotal(), expectedTotal, "Wrong order total on OrderConfirmationPage!");
        softAssert.assertAll();
    }
}
