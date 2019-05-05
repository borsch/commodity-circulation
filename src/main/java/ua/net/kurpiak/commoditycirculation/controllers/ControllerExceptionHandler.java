package ua.net.kurpiak.commoditycirculation.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.pojo.response.Error;
import ua.net.kurpiak.commoditycirculation.pojo.response.Response;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Response handle(BaseException e) {
        return Response.of(new Error(e.getCode(), e.formMessage(), e.formListErrors()));
    }

    @ExceptionHandler(Exception.class)
    public Response handle(Exception e) {
        e.printStackTrace();

        return Response.of(new Error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Внутрішня помилка сервера"));
    }
}
