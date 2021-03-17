package model;

public class Oauth {
    long id;
    String token;
    String token_type;
    String expires_in;

    public Oauth(long id, String token, String token_type, String expires_in) {
        this.id = id;
        this.token = token;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }

    public long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }
}