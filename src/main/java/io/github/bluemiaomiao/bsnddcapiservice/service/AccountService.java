package io.github.bluemiaomiao.bsnddcapiservice.service;

import io.github.bluemiaomiao.bsnddcapiservice.core.dto.ddc.AccountInfo;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.UpdateAccountStateParams;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.account.AccountServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 权限管理
 */
@Service
public interface AccountService {
    ResponseEntity<GlobalResponseEntity<AccountInfo>> getAccountByID(String id) throws AccountServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> updateAccountState(String id, UpdateAccountStateParams params) throws AccountServiceInvokeFailedException;
}
