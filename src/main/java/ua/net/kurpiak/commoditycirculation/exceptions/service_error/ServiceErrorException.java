package ua.net.kurpiak.commoditycirculation.exceptions.service_error;

import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ServiceErrorException extends BaseException {


    public ServiceErrorException(String message) {
        super(message);
    }

    public ServiceErrorException() {
        this("Внітрішня помилка сервера");
    }

    @Override
    public int getCode(){
        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    @Override
    public String formMessage() {
        return getMessage();
    }

    @Override
    public List<String> formListErrors() {
        return null;
    }
}
