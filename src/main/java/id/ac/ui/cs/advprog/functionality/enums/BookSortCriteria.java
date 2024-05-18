package id.ac.ui.cs.advprog.functionality.enums;

public enum BookSortCriteria {
    NEWEST("newest"),
    POPULARITY("popularity"),
    PRICE_ASC("priceAsc"),
    PRICE_DESC("priceDesc");

    private final String sortKey;

    BookSortCriteria(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getSortKey() {
        return sortKey;
    }

    public static BookSortCriteria fromString(String value) {
        for (BookSortCriteria criteria : BookSortCriteria.values()) {
            if (criteria.sortKey.equalsIgnoreCase(value)) {
                return criteria;
            }
        }
        throw new IllegalArgumentException("Unknown sort criteria: " + value);
    }
}
