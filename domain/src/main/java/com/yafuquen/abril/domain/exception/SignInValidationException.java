package com.yafuquen.abril.domain.exception;

/**
 * Exception for sign in validation errors.
 *
 * @author yafuquen
 */
public class SignInValidationException extends SignInException {

    private final ValidationErrorType validationErrorSource;

    public SignInValidationException(ValidationErrorType validationErrorSource) {
        this.validationErrorSource = validationErrorSource;
    }

    public ValidationErrorType getValidationErrorSource() {
        return validationErrorSource;
    }

    public enum ValidationErrorType {
        INVALID_USERNAME,
        INVALID_PASSWORD
    }
}
