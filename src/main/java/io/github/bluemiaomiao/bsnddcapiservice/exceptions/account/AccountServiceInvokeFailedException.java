package io.github.bluemiaomiao.bsnddcapiservice.exceptions.account;

public class AccountServiceInvokeFailedException extends Exception{
    public AccountServiceInvokeFailedException() {
    }

    public AccountServiceInvokeFailedException(String message) {
        super(message);
    }
}
