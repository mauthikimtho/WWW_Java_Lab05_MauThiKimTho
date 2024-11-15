package www_lab5_mauthikimtho.backend.converters;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import www_lab5_mauthikimtho.backend.enums.CountryCode;

@Converter(autoApply = true)
public class CountryCodeConventer implements AttributeConverter<CountryCode, Short> {
    @Override
    public Short convertToDatabaseColumn(CountryCode attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public CountryCode convertToEntityAttribute(Short dbData) {
        if (dbData == null) {
            return null;
        }
        return CountryCode.fromCode(dbData);
    }
}
