package ptl.cloud.bank.validation;

public interface Validator<T> {
    boolean validate(T object);
}
