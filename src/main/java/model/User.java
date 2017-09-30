package model;

public class User {
    long id;
    String username;
    String email;
    String pass;
    int roles;

    public User(long id, String username, String email, String pass, int roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.roles = roles;
    }

    public User(long id, String username, String email, int roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }
    

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", pass=" + pass + '}';
    }
    
}
