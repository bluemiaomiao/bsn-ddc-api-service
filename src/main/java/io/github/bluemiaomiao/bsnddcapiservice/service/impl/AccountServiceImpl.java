package io.github.bluemiaomiao.bsnddcapiservice.service.impl;

import io.github.bluemiaomiao.bsnddcapiservice.core.DDCCoreTemplate;
import io.github.bluemiaomiao.bsnddcapiservice.core.dto.ddc.AccountInfo;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.UpdateAccountStateParams;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.account.AccountServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    private DDCCoreTemplate ddcCoreTemplate;

    @Override
    public ResponseEntity<GlobalResponseEntity<AccountInfo>> getAccountByID(String id) throws AccountServiceInvokeFailedException {

        AccountInfo info = null;

        try {
            info = this.ddcCoreTemplate.buildWithNoneEvent().authorityService.getAccount(id);
        } catch (Exception e) {
            throw new AccountServiceInvokeFailedException("查询账户失败");
        }

        return new ResponseEntity<>(new GlobalResponseEntity<>(info), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GlobalResponseEntity<String>> updateAccountState(String id, UpdateAccountStateParams params) throws AccountServiceInvokeFailedException {
        String result = null;
        try {
            result = this.ddcCoreTemplate.buildWithNoneEvent().authorityService.updateAccState(
                    params.getSender(),
                    id,
                    params.getStateForEnum(),
                    params.getChangePlatformState()
            );
        } catch (Exception e) {
            throw new AccountServiceInvokeFailedException("更新账户状态失败");
        }
        return new ResponseEntity<>(new GlobalResponseEntity<>(result), HttpStatus.OK);
    }
}
