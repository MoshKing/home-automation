package home.automation.web;

import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import home.automation.BaseProjectTest;
import home.automation.constant.ProjectConstants;
import home.automation.web.domain.*;
import home.automation.web.gui.components.AddToCartInfoModal;
import home.automation.web.gui.components.QuickViewModal;
import home.automation.web.gui.pages.*;
import home.automation.web.service.UserDataService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrderCreationTest extends BaseProjectTest {

    @DataProvider
    public Object[][] getUserType() {
        return new Object[][]{{UserType.NEW}, {UserType.REGISTERED}};
    }

    @MethodOwner(owner = "dprymudrau")
    @Test(dataProvider = "getUserType",
            description = "Create an order and validate that product data the same on each stage of creation")
    public void testGuestUserOrderCreation(UserType userType) {
        UserData userData;
        if (UserType.REGISTERED.equals(userType)) {
            userData = UserDataService.getRegisteredUser();
        } else {
            userData = UserDataService.generateNewUser();
        }
        SoftAssert softAssert = new SoftAssert();

        HomePage homePage = openHomePage();

        //Search an item and open its' details
        SearchResultsPage searchResultsPage = homePage.searchProducts(SEARCH_QUERY);
        ProductDetailPage productDetailPage = searchResultsPage.openProductDetails(ITEM_INDEX_IN_SEARCH_RESULTS);

        //Add product to cart from pdp
        ProductData productData = addProductToCart(softAssert, productDetailPage, ITEM_QTY, ProductSize.MEDIUM);
        CartPage cartPage = new CartPage(getDriver());
        cartPage.getCartItem(0).verifyProductData(softAssert, productData);
        cartPage.clickProceedToCheckoutBtn();

        createAnOrder(softAssert, userType, userData, productData);

        softAssert.assertAll();
    }

    @MethodOwner(owner = "dprymudrau")
    @Test(description = "Create an order by adding item to bag from quick view and validate that product data the same on each stage of creation")
    public void addProductToCartAndCreateAnOrder() {
        UserData userData = UserDataService.generateNewUser();

        SoftAssert softAssert = new SoftAssert();

        HomePage homePage = openHomePage();

        //Search an item and open quick view
        SearchResultsPage searchResultsPage = homePage.searchProducts(SEARCH_QUERY);
        QuickViewModal quickViewModal = searchResultsPage.openQuickViewModal(ITEM_INDEX_IN_SEARCH_RESULTS);
        quickViewModal.typeQuantity(ITEM_QTY);
        quickViewModal.selectSize(ProductSize.MEDIUM);
        ProductData productData = quickViewModal.getProductData();

        //Add product to cart from quick view
        AddToCartInfoModal addToCartInfoModal = quickViewModal.clickAddToBagBtn();
        pause(3);
        addToCartInfoModal.verifyProductData(softAssert, productData);
        CartPage cartPage = addToCartInfoModal.clickProceedToCheckoutBtn();
        cartPage.clickProceedToCheckoutBtn();

        createAnOrder(softAssert, UserType.NEW, userData, productData);

        softAssert.assertAll();
    }

    private void createAnOrder(SoftAssert softAssert, UserType userType, UserData userData, ProductData productData) {
        //Create a new user
        AuthorizationPage authPage = new AuthorizationPage(getDriver());
        AddressesPage addressesPage;
        if (UserType.REGISTERED.equals(userType)) {
            addressesPage = authPage.signIn(userData);
        } else {
            authPage.typeNewUserEmail(userData.getEmail());
            AccountCreationPage accountCreationPage = authPage.clickCreateAnAccountBtn();
            accountCreationPage.fillUserDataAndClickRegisterBtn(userData);
            addressesPage = new AddressesPage(getDriver());
        }

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
    }
}
