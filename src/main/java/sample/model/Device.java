package sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by luiz on 15/05/17.
 */
public class Device {

    String token;

    public Device(@JsonProperty("token") String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
