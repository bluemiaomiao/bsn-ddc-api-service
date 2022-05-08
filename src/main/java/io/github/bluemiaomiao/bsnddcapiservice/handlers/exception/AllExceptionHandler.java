package io.github.bluemiaomiao.bsnddcapiservice.handlers.exception;

import io.github.bluemiaomiao.bsnddcapiservice.exceptions.account.AccountServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.block.BlockServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.dataanalysis.DataAnalysisServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.expense.ExpenseServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.oneonefivefive.OneOneFiveFiveServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.seventwoone.SevenTwoOneServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.sign.SignServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.transaction.TransactionServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@ResponseBody
@RestControllerAdvice(value = {"io.github.bluemiaomiao.bsnddcapiservice", "org.spring"})
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleException(Exception e) {
        String message = String.format("未定义的业务错误: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1110").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleRuntimeException(RuntimeException e) {
        String message = String.format("未定义的运行时错误: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1010").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(AccountServiceInvokeFailedException.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleAccountServiceInvokeFailedException(
            AccountServiceInvokeFailedException e
    ) {
        String message = String.format("权限管理服务调用失败: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1101").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(BlockServiceInvokeFailedException.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleBlockServiceInvokeFailedException(
            BlockServiceInvokeFailedException e
    ) {
        String message = String.format("区块查询服务调用失败: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1101").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(DataAnalysisServiceInvokeFailedException.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleDataAnalysisServiceInvokeFailedException(
            DataAnalysisServiceInvokeFailedException e
    ) {
        String message = String.format("数据解析服务调用失败: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1101").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ExpenseServiceInvokeFailedException.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleExpenseServiceInvokeFailedException(
            ExpenseServiceInvokeFailedException e
    ) {
        String message = String.format("费用管理服务调用失败: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1101").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(OneOneFiveFiveServiceInvokeFailedException.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleOneOneFiveFiveServiceInvokeFailedException(
            OneOneFiveFiveServiceInvokeFailedException e
    ) {
        String message = String.format("1155服务调用失败: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1101").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(SevenTwoOneServiceInvokeFailedException.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleSevenTwoOneServiceInvokeFailedException(
            SevenTwoOneServiceInvokeFailedException e
    ) {
        String message = String.format("721服务调用失败: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1101").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(SignServiceInvokeFailedException.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleSignServiceInvokeFailedException(
            SignServiceInvokeFailedException e
    ) {
        String message = String.format("签名事件服务调用失败: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1101").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(TransactionServiceInvokeFailedException.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleTransactionServiceInvokeFailedException(
            TransactionServiceInvokeFailedException e
    ) {
        String message = String.format("交易查询服务调用失败: %s", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(null).withCode("1101").withMessage(message),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseEntity<Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        StringBuilder messageBuilder = new StringBuilder();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError error : errors) {
            messageBuilder.append(error.getDefaultMessage());
        }

        return new ResponseEntity<>(
                new GlobalResponseEntity<>().withMessage(messageBuilder.toString()).withCode("1100"),
                HttpStatus.BAD_REQUEST
        );
    }
}
