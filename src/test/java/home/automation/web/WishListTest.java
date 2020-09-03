package home.automation.web;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import home.automation.BaseProjectTest;
import home.automation.web.domain.ProductData;
import home.automation.web.domain.UserData;
import home.automation.web.gui.components.WishListItem;
import home.automation.web.gui.pages.*;
import home.automation.web.service.UserDataService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class WishListTest extends BaseProjectTest {

    @Test(description = "Add product to wish list and check that it is correct")
    @MethodOwner(owner = "dprymudrau")
    public void testAddingProductToWishList() {
        SoftAssert softAssert = new SoftAssert();
        UserData userData = UserDataService.generateNewUser();

        //Create a new user
        AuthorizationPage authorizationPage = openHomePage().getTopMainMenu().clickSignInBtn();
        authorizationPage.typeNewUserEmail(userData.getEmail());
        AccountCreationPage accountCreationPage = authorizationPage.clickCreateAnAccountBtn();
        accountCreationPage.fillUserDataAndClickRegisterBtn(userData);

        //Add product to wish list and and check message after clicking 'addToWishListBtn'
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        SearchResultsPage searchResultsPage = myAccountPage.searchProducts(SEARCH_QUERY);
        searchResultsPage.getSearchResults().get(1).clickAddToWishList();
        softAssert.assertEquals(searchResultsPage.getMessageAfterClickOnWishListBtn(),
                R.TESTDATA.get("SearchResultsPage.successfulAddedToWishListMessage"),
                "Successfull added to wish list message is wrong!");
        searchResultsPage.clickCloseMessageBtn();
        String expectedProductName = searchResultsPage.getProductsNames().get(1);

        //Open wish list page and validate that product added correctly
        myAccountPage = searchResultsPage.getTopMainMenu().clickUserAccountBtn();
        MyWishListPage myWishListPage = myAccountPage.clickMyWishListBtn();
        myWishListPage.getWishListsTable()
                .clickWishList(R.TESTDATA.get("MyWishListPage.myWishListName"));
        List<WishListItem> wishListItems = myWishListPage.getWishListItems();
        softAssert.assertEquals(wishListItems.size(), 1, "Items in wish list are more than expected!");
        ProductData productData = wishListItems.get(0).getItemData();
        softAssert.assertEquals(productData.getName(), expectedProductName, "Wrong product name added to wish list!");
        softAssert.assertEquals(productData.getQty(), 1, "Wrong quantity of product added to wish list!");
        softAssert.assertAll();
    }

}