package ptl.cloud.bank.validation.card;

import ptl.cloud.bank.entities.Card;

public abstract class ValidationHandler {
    protected ValidationHandler next;

    public ValidationHandler(ValidationHandler next) {
        this.next = next;
    }

    public ValidationHandler() {
    }

    public void setNext(ValidationHandler validationHandler) {
        next = validationHandler;
    }

    public abstract void validate(Card card);
}
