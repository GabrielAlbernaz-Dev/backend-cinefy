package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

public enum IndicativeRating {
    L("L"),
    TEN("10"),
    TWELVE("12"),
    FOURTEEN("14"),
    SIXTEEN("16"),
    ADULT("18+");

    private final String value;

    IndicativeRating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static IndicativeRating fromValue(String value) {
        for (IndicativeRating rating : IndicativeRating.values()) {
            if (rating.getValue().equals(value)) {
                return rating;
            }
        }

        throw new IllegalArgumentException("Invalid value: " + value);
    }
}