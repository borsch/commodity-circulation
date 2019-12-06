package ua.net.kurpiak.commoditycirculation.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor(staticName = "of")
public class Response<T> {

    private T result;
    private Error error;

    public static <T> Response<T> of(T object) {
        return Response.of(object, null);
    }

    public static Response of(Error error) {
        return Response.of(null, error);
    }
}
