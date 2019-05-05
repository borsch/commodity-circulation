package ua.net.kurpiak.commoditycirculation.exceptions;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class WrongRestrictionException extends BaseException {

    @Override
    public int getCode() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }

    @Override
    public String formMessage() {
        return "Неправильні критерії пошуку";
    }

    @Override
    public List<String> formListErrors() {
        return null;
    }
}
