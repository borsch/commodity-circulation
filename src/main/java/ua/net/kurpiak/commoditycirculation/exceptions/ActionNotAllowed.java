package ua.net.kurpiak.commoditycirculation.exceptions;

import java.util.List;

public class ActionNotAllowed extends BaseException {

    private final String message;

    public ActionNotAllowed(final String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String formMessage() {
        return message;
    }

    @Override
    public List<String> formListErrors() {
        return null;
    }
}
