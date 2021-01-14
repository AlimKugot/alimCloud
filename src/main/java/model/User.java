package model;

import helperStatic.SignUpAndIn;

//pattern builder
public class User {
    private final Integer id;
    private final String userName;
    private final String email;
    private final String password;

    private User(Integer id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {
        private Integer id;
        private String userName;
        private String email;
        private String password;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setUserName(String name) {
            this.userName = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = SignUpAndIn.hashPasswordBcrypt(password);
            return this;
        }

        public User build() {
            return new User(this.id, this.userName, this.email, this.password);
        }
    }
}
