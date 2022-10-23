package entity;

import java.io.Serializable;

public class Admin implements Serializable {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
