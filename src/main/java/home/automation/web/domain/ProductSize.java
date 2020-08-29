package home.automation.web.domain;

public enum ProductSize {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L");

    private String abbreviation;

    ProductSize(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
