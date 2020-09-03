package home.automation.web;

import com.google.common.collect.Ordering;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import home.automation.BaseProjectTest;
import home.automation.constant.TimeConstants;
import home.automation.web.domain.SortOption;
import home.automation.web.gui.pages.SearchResultsPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collections;
import java.util.List;

public class SortTest extends BaseProjectTest {

    private final static Logger LOGGER = Logger.getLogger(SortTest.class);

    @MethodOwner(owner = "dprymudrau")
    @Test(description = "Sort searched products and check that it works correct (prices sorting is incorrect)")
    public void testSortOptions() {
        SoftAssert softAssert = new SoftAssert();

        //Open home page and search for products
        SearchResultsPage searchResultsPage = openHomePage().searchProducts(SEARCH_QUERY);
        searchResultsPage.assertPageOpened();

        //Sort search results by price from highest to lowest and check if it is ordered correctly
        searchResultsPage.selectSortOption(SortOption.PRICE_HIGHEST_FIRST);
        pause(TimeConstants.TWO_SEC_TIMEOUT);
        List<Double> pricesList = searchResultsPage.getPrices();
        softAssert.assertTrue(Ordering.natural().isOrdered(pricesList),
                String.format("Wrong sorting search results from highest to lowest price! %s", pricesList));

        //Sort search results by price from lowest to highest and check if it is ordered correctly
        searchResultsPage.selectSortOption(SortOption.PRICE_LOWEST_FIRST);
        pause(TimeConstants.TWO_SEC_TIMEOUT);
        pricesList = searchResultsPage.getPrices();
        LOGGER.info(pricesList);
        softAssert.assertTrue(Ordering.natural().reverse().isOrdered(pricesList),
                String.format("Wrong sorting search results from lowest to highest price! %s", pricesList));

        //Sort search results by name from A to Z and check if it is ordered correctly
        searchResultsPage.selectSortOption(SortOption.PRODUCT_NAME_A_TO_Z);
        pause(TimeConstants.TWO_SEC_TIMEOUT);
        List<String> productsNames = searchResultsPage.getProductsNames();
        softAssert.assertTrue(Ordering.natural().isOrdered(productsNames),
                String.format("Products wasn't ordered form A to Z! %s", productsNames));

        //Sort search results by name from Z to A and check if it is ordered correctly
        searchResultsPage.selectSortOption(SortOption.PRODUCT_NAME_Z_TO_A);
        pause(TimeConstants.TWO_SEC_TIMEOUT);
        productsNames = searchResultsPage.getProductsNames();
        softAssert.assertTrue(Ordering.natural().reverse().isOrdered(productsNames),
                String.format("Products wasn't ordered form Z to A! %s", productsNames));

        //Sort search results by highest reference and if it is ordered correctly
        searchResultsPage.selectSortOption(SortOption.REFERENCE_HIGHEST_FIRST);
        pause(TimeConstants.TWO_SEC_TIMEOUT);
        softAssert.assertNotEquals(productsNames, searchResultsPage.getProductsNames(), "Products wasn't sorted by highest reference");

        //Sort search results by lowest reference and if it is ordered correctly
        productsNames = searchResultsPage.getProductsNames();
        Collections.reverse(productsNames);
        searchResultsPage.selectSortOption(SortOption.REFERENCE_LOWEST_FIRST);
        pause(TimeConstants.TWO_SEC_TIMEOUT);
        softAssert.assertEquals(searchResultsPage.getProductsNames(), productsNames, "Products wasn't sorted by lowest reference");

        //Sort search results by in stock property reference and if it is ordered correctly
        searchResultsPage.selectSortOption(SortOption.IN_STOCK);
        pause(TimeConstants.TWO_SEC_TIMEOUT);
        searchResultsPage.validateProductInStock(softAssert);

        softAssert.assertAll();
    }
}