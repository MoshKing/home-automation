package home.automation.web.service;

import com.qaprosoft.carina.core.foundation.utils.common.CommonUtils;
import com.qaprosoft.carina.core.foundation.webdriver.IDriverPool;
import home.automation.web.domain.ProductSize;
import home.automation.web.gui.components.AddToCartInfoModal;
import home.automation.web.gui.pages.CartPage;
import home.automation.web.gui.pages.ProductDetailPage;

public class ProductService implements IDriverPool {
    public CartPage addProductToCart(ProductDetailPage productDetailPage, String qty, ProductSize productSize) {
        productDetailPage.typeQuantity(qty);
        productDetailPage.selectSize(productSize);
        AddToCartInfoModal addToCartInfoModal = productDetailPage.clickAddToCartButton();
        CommonUtils.pause(2);
        return addToCartInfoModal.clickProceedToCheckoutBtn();
    }
}
