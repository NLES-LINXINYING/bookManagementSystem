package cn.lxy.bookManagementSystem.model.exceptions;

/**
 * 注册和登录异常
 */
public class LoginRegisterException extends RuntimeException {
    public LoginRegisterException() {
    }

    public LoginRegisterException(String message) {
        super(message);
    }

    public LoginRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginRegisterException(Throwable cause) {
        super(cause);
    }
}
