package io.github.bluemiaomiao.bsnddcapiservice.exceptions.expense;

public class ExpenseServiceInvokeFailedException extends Exception{
    public ExpenseServiceInvokeFailedException() {
    }

    public ExpenseServiceInvokeFailedException(String message) {
        super(message);
    }
}
