package ua.net.kurpiak.commoditycirculation.pojo.response;

public class Response<T> {

    private T result;

    private Error error;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public static <T> Response<T> of(T object) {
        Response<T> response = new Response<>();
        response.setResult(object);

        return response;
    }

    public static Response of(Error error) {
        Response response = new Response<>();
        response.setError(error);

        return response;
    }
}
