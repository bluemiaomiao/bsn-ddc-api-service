package io.github.bluemiaomiao.bsnddcapiservice.exceptions.transaction;

public class TransactionServiceInvokeFailedException extends Exception{
    public TransactionServiceInvokeFailedException() {
    }

    public TransactionServiceInvokeFailedException(String message) {
        super(message);
    }
}
