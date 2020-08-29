package home.automation;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.common.CommonUtils;
import home.automation.web.domain.ProductData;
import home.automation.web.domain.ProductSize;
import home.automation.web.gui.components.AddToCartInfoModal;
import home.automation.web.gui.pages.HomePage;
import home.automation.web.gui.pages.ProductDetailPage;
import org.testng.asserts.SoftAssert;

public class BaseProjectTest extends AbstractTest {
    protected final static String SEARCH_QUERY = "dress";
    protected final static int ITEM_INDEX_IN_SEARCH_RESULTS = 2;
    protected final static String ITEM_QTY = "2";

    protected ProductData addProductToCart(SoftAssert softAssert, ProductDetailPage productDetailPage, String qty, ProductSize productSize) {
        productDetailPage.typeQuantity(qty);
        productDetailPage.selectSize(productSize);
        ProductData productData = productDetailPage.getProductData();
        AddToCartInfoModal addToCartInfoModal = productDetailPage.clickAddToCartButton();
        CommonUtils.pause(2);
        addToCartInfoModal.verifyProductData(softAssert, productData);
        addToCartInfoModal.clickProceedToCheckoutBtn();
        return productData;
    }

    protected HomePage openHomePage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        return homePage;
    }
}
