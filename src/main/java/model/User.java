package model;

import security.Crypto;

public class User {
    private final long id;
    private final String userName;
    private final String email;
    private final String password;
    private final Role role;


    private User(long id, String userName, String email, String password, Role role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static class Builder {
        private long id;
        private String userName;
        private String email;
        private String password;
        private Role role;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder userName(String name) {
            this.userName = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = Crypto.hashPasswordBcrypt(password);
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this.id, this.userName, this.email,
                    this.password, this.role);
        }
    }


    public long getId() {
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

    public Role getRole() {
        return role;
    }
}
