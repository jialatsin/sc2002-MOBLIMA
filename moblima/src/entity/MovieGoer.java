package entity;

import java.io.Serializable;

/* Represents a MovieGoer
 * Details of MovieGoer is captured by the application upon booking of tickets.
 * A MovieGoer can only read database records and create certain records (eg. reviews)
 */
public class MovieGoer implements Serializable {
    private String name;
    private String mobile;
    private String email;

    public MovieGoer(String name, String mobile, String email) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MovieGoer [name=" + name + ", mobile=" + mobile + ", email=" + email + "]";
    }

}
