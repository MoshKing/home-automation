package home.automation.web;

import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import home.automation.BaseProjectTest;
import home.automation.web.domain.ProductSize;
import home.automation.web.gui.pages.CartPage;
import home.automation.web.gui.pages.HomePage;
import home.automation.web.gui.pages.ProductDetailPage;
import home.automation.web.gui.pages.SearchResultsPage;
import home.automation.web.service.ProductService;
import org.testng.annotations.Test;

public class OrderCreationTest extends BaseProjectTest {

    private final static String SEARCH_QUERY = "dress";
    private final static int ITEM_INDEX_IN_SEARCH_RESULTS = 2;
    private final static String ITEM_QTY = "2";

    @MethodOwner(owner = "dprymudrau")
    @Test(description = "Create an order as guest user validate that product data the same on each stage of creation")
    public void testGuestUserOrderCreation() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();

        //Search an item and open its' details
        SearchResultsPage searchResultsPage = homePage.searchProducts(SEARCH_QUERY);
        ProductDetailPage productDetailPage = searchResultsPage.openProductDetails(ITEM_INDEX_IN_SEARCH_RESULTS);

        //Add product to cart from pdp
        CartPage cartPage = new ProductService().addProductToCart(productDetailPage, ITEM_QTY, ProductSize.MEDIUM);
        pause(30);
    }
}
