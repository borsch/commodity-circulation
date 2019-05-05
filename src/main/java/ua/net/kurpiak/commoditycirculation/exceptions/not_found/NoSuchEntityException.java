package ua.net.kurpiak.commoditycirculation.exceptions.not_found;

import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Andrii on 18.08.2016.
 */
public class NoSuchEntityException extends BaseException {

    public NoSuchEntityException(String message){
        super(message);
    }

    public int getCode(){
        return HttpServletResponse.SC_NOT_FOUND;
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
