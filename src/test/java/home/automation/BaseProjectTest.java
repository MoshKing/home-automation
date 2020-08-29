package home.automation;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.common.CommonUtils;
import home.automation.web.domain.ProductData;
import home.automation.web.domain.ProductSize;
import home.automation.web.gui.components.AddToCartInfoModal;
import home.automation.web.gui.pages.ProductDetailPage;
import org.testng.asserts.SoftAssert;

public class BaseProjectTest extends AbstractTest {
    public ProductData addProductToCart(SoftAssert softAssert, ProductDetailPage productDetailPage, String qty, ProductSize productSize) {
        productDetailPage.typeQuantity(qty);
        productDetailPage.selectSize(productSize);
        ProductData productData = productDetailPage.getProductData();
        AddToCartInfoModal addToCartInfoModal = productDetailPage.clickAddToCartButton();
        CommonUtils.pause(2);
        addToCartInfoModal.verifyProductData(softAssert, productData);
        addToCartInfoModal.clickProceedToCheckoutBtn();
        return productData;
    }
}
