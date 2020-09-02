package home.automation.web.domain;

public enum SortOption {
    PRICE_LOWEST_FIRST("Price: Lowest first"),
    PRICE_HIGHEST_FIRST("Price: Highest first"),
    PRODUCT_NAME_A_TO_Z("Product Name: A to Z"),
    PRODUCT_NAME_Z_TO_A("Product Name: Z to A"),
    IN_STOCK("In stock"),
    REFERENCE_LOWEST_FIRST("Reference: Lowest first"),
    REFERENCE_HIGHEST_FIRST("Reference: Highest first");

    private String option;

    SortOption(String option) {
        this.option = option;
    }

    public String getOptionName() {
        return option;
    }
}