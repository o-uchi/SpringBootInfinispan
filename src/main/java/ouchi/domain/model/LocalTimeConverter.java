package ouchi.domain.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, String> {

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

    @Override
    public String convertToDatabaseColumn(LocalTime attribute) {
        return attribute.format(timeFormatter);
    }

    @Override
    public LocalTime convertToEntityAttribute(String dbData) {
        return dbData != null ? LocalTime.parse(dbData, timeFormatter) : null;
    }
}
