package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IndicativeRatingConverter implements AttributeConverter<IndicativeRating, String> {
    @Override
    public String convertToDatabaseColumn(IndicativeRating rating) {
        return rating != null ? rating.getValue() : null;
    }

    @Override
    public IndicativeRating convertToEntityAttribute(String dbData) {
        return dbData != null ? IndicativeRating.fromValue(dbData) : null;
    }
}
