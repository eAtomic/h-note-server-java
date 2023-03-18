package cn.hyv5.hnote.exception;

public class LoginException extends RuntimeException{
    public static LoginException LOGIN_METHOD_ERR = new LoginException("login method err");
    public LoginException(String message) {
        super(message);
    }
}
