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
}