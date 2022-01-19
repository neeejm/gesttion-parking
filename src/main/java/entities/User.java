package entities;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private boolean isAdmin;

    public User(int id, String username, String email, String password) {
        this.id += id;
        this.username = username;
        this.email = email;
        this.isAdmin = false;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.isAdmin = false;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
