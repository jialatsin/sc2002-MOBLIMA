package entity;

import java.io.Serializable;

public class MovieGoer implements Serializable {
    private String name;
    private String mobile;
    private String email;

    public MovieGoer(String name, String mobile, String email) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }
}
