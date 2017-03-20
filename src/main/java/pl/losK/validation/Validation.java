package pl.losK.validation;

/**
 * Created by RENT on 2017-03-20.
 */
public class Validation {

    public Validation() {
    }

    public boolean validate(FieldType fieldType, Object value) {
        setFieldType(fieldType);
        setValue(value);

        return validate();
    }

    public enum FieldType {STRING, INTEGER, FLOAT, DOUBLE}

    private FieldType fieldType;
    private Object value;

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Validation(FieldType fieldType, Object value) {
        this.fieldType = fieldType;
        this.value = value;
    }

    //TODO dolozyc kolejne typy
    public boolean validate() {
        boolean result = false;
        switch (fieldType) {
            case STRING:
                if (!isAlphaNumericWithSpaces((String) value)) {
                    result = true;
                }
                break;
        }
        return result;
    }

    public boolean isAlphaNumericWithSpaces(String s) {
        String pattern = "^[a-zA-Z0-9 ]*$";
        return s.matches(pattern);
    }
}
