package ua.net.kurpiak.commoditycirculation.exceptions;

import java.util.List;

public abstract class BaseException extends RuntimeException {

    private int code;
    private String message;

    public BaseException(){
        this("Помилка");
    }

    public BaseException(String message){
        super(message);
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * override this method to create custom message for users
     *
     * @return converted message
     */
    public abstract String formMessage();

    /**
     * override this method to create a list of errors for user
     *
     * return null to show that exception has no list of errors
     *
     * @return list of errors
     */
    public abstract List<String> formListErrors();
}
