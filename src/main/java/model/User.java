package model;

import lombok.Getter;
import security.Crypto;

@Getter
public class User {
    private final long id;
    private final String userName;
    private final String email;
    private final String password;

    //todo: problems with @lombok.builder
    private User(long id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public static class Builder {
        private long id;
        private String userName;
        private String email;
        private String password;

        public Builder setId(long id) {
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
            this.password = Crypto.hashPasswordBcrypt(password);
            return this;
        }

        public User build() {
            return new User(this.id, this.userName, this.email, this.password);
        }
    }
}
