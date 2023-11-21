package crud.candidate.domain.utils;

import com.nimbusds.jose.shaded.gson.Gson;

import java.io.BufferedReader;

public class JsonUtils {

    public static <T> T fromJson(BufferedReader json, Class<T> classOfT) {
        return gsonInstance().fromJson(json, classOfT);
    }

    public static <T> String toJson(T object) {
        return gsonInstance().toJson(object);
    }

    private static Gson gsonInstance() {
        return new Gson();
    }

}
