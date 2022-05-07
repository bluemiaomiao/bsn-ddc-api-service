package io.github.bluemiaomiao.bsnddcapiservice.core.service;

import io.github.bluemiaomiao.bsnddcapiservice.core.constant.AuthorityFunctions;
import io.github.bluemiaomiao.bsnddcapiservice.core.constant.ErrorMessage;
import io.github.bluemiaomiao.bsnddcapiservice.core.dto.config.DDCContract;
import io.github.bluemiaomiao.bsnddcapiservice.core.dto.ddc.AccountInfo;
import io.github.bluemiaomiao.bsnddcapiservice.core.dto.ddc.AccountRole;
import io.github.bluemiaomiao.bsnddcapiservice.core.dto.ddc.AccountState;
import io.github.bluemiaomiao.bsnddcapiservice.core.dto.wuhanchain.RespJsonRpcBean;
import io.github.bluemiaomiao.bsnddcapiservice.core.exception.DDCException;
import io.github.bluemiaomiao.bsnddcapiservice.core.net.RequestOptions;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.tx.txdecode.InputAndOutputResult;
import org.web3j.utils.Strings;
import java.util.ArrayList;
import java.util.Objects;

import static io.github.bluemiaomiao.bsnddcapiservice.core.constant.ContractConfig.DDCContracts;
import static java.lang.String.valueOf;

/**
 * ddc authority
 *
 * @author wxq
 */
public class AuthorityService extends BaseService {

    public static DDCContract authorityContract;

    public AuthorityService() {
        authorityContract = DDCContracts.stream().filter(t -> "authority".equals(t.getConfigType())).findFirst().orElse(null);
    }

    /**
     * 平台方可以通过调用该方法进行DDC账户信息的创建，上级角色可进行下级角色账户的操作，平台方通过该方法只能添加终端账户。
     *
     * @param sender    The address the transaction is sent from.
     * @param account   DDC链账户地址
     * @param accName   DDC账户对应的账户名称
     * @param accDID    DDC账户对应的DID信息
     * @param leaderDID 该普通账户对应的上级账户的DID
     * @return 返回交易哈希
     * @throws Exception
     */
    public String addAccountByOperator(String sender, String account, String accName, String accDID, String leaderDID) throws Exception {
        return addAccountByOperator(sender, account, accName, accDID, leaderDID, null);
    }

    /**
     * 平台方可以通过调用该方法进行DDC账户信息的创建，上级角色可进行下级角色账户的操作，平台方通过该方法只能添加终端账户。
     *
     * @param sender    The address the transaction is sent from.
     * @param account   DDC链账户地址
     * @param accName   DDC账户对应的账户名称
     * @param accDID    DDC账户对应的DID信息
     * @param leaderDID 该普通账户对应的上级账户的DID
     * @return 返回交易哈希
     * @throws Exception
     */
    public String addAccountByOperator(String sender, String account, String accName, String accDID, String leaderDID, RequestOptions options) throws Exception {
        // check sender
        checkSender(sender);

        // check account
        checkAccount(account);

        // check account name
        checkAccountName(accName);

        if (Strings.isEmpty(leaderDID)) {
            leaderDID = "";
        }

        // input params
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(new Address(account));
        arrayList.add(new Utf8String(accName));
        arrayList.add(new Utf8String(accDID));
        arrayList.add(new Utf8String(leaderDID));

        // send transaction
        RespJsonRpcBean respJsonRpcBean = assembleTransactionAndSend(sender, options, arrayList, AuthorityFunctions.ADD_ACCOUNT_BY_OPERATOR, authorityContract);
        return (String) respJsonRpcBean.getResult();
    }


    /**
     * 删除账户
     *
     * @param sender  The address the transaction is sent from.
     * @param account DDC链账户地址
     * @return 返回交易哈希
     * @throws Exception
     */
    public String delAccount(String sender, String account) throws Exception {
        return delAccount(sender, account, null);
    }

    /**
     * 删除账户
     *
     * @param sender  The address the transaction is sent from.
     * @param account
     * @param options
     * @return
     * @throws Exception
     */
    public String delAccount(String sender, String account, RequestOptions options) throws Exception {
        // check sender
        checkSender(sender);

        // check account
        checkAccount(account);

        // input parameter
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(new Address(account));

        // send transaction
        RespJsonRpcBean respJsonRpcBean = assembleTransactionAndSend(sender, options, arrayList, AuthorityFunctions.DEL_ACCOUNT, authorityContract);
        return (String) respJsonRpcBean.getResult();
    }

    /**
     * 运营方或平台方通过该方法进行DDC账户信息的查询，上级角色可进行下级角色账户的操作。
     *
     * @param account DDC用户链账户地址
     * @return 返回DDC账户信息
     * @throws Exception
     */
    public AccountInfo getAccount(String account) throws Exception {
        return getAccount(account, null);
    }

    /**
     * 运营方或平台方通过该方法进行DDC账户信息的查询，上级角色可进行下级角色账户的操作。
     *
     * @param account DDC用户链账户地址
     * @return 返回DDC账户信息
     * @throws Exception
     */
    public AccountInfo getAccount(String account, RequestOptions options) throws Exception {
        // check account
        checkAccount(account);

        // input parameter
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(account);

        // send call tran and decode output
        InputAndOutputResult inputAndOutputResult = sendCallTransactionAndDecodeOutput(options, arrayList, AuthorityFunctions.GET_ACCOUNT, authorityContract);

        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccountDID(valueOf(inputAndOutputResult.getResult().get(0).getData()));
        accountInfo.setAccountName(valueOf(inputAndOutputResult.getResult().get(1).getData()));
        accountInfo.setLeaderDID(valueOf(inputAndOutputResult.getResult().get(3).getData()));
        accountInfo.setField(valueOf(inputAndOutputResult.getResult().get(6).getData()));
        String accountRole = valueOf(inputAndOutputResult.getResult().get(2).getData());
        if (accountRole != null && !accountRole.trim().isEmpty()) {
            accountInfo.setAccountRole(AccountRole.getByVal(Integer.parseInt(accountRole)));
        }
        String platformState = valueOf(inputAndOutputResult.getResult().get(4).getData());
        if (platformState != null && !platformState.trim().isEmpty()) {
            accountInfo.setPlatformState(AccountState.getByVal(Integer.parseInt(platformState)));
        }

        String operatorState = valueOf(inputAndOutputResult.getResult().get(5).getData());
        if (operatorState != null && !operatorState.trim().isEmpty()) {
            accountInfo.setOperatorState(AccountState.getByVal(Integer.parseInt(operatorState)));
        }

        return accountInfo;
    }

    /**
     * 运营方或平台方通过该方法进行DDC账户信息状态的更改。
     *
     * @param sender  The address the transaction is sent from.
     * @param account DDC用户链账户地址
     * @param state   状态 ：Frozen - 冻结状态 ； Active - 活跃状态
     * @return 返回交易哈希
     * @throws Exception
     */
    public String updateAccState(String sender, String account, AccountState state, boolean changePlatformState) throws Exception {
        return updateAccState(sender, account, state, changePlatformState, null);
    }

    /**
     * 运营方或平台方通过该方法进行DDC账户信息状态的更改。
     *
     * @param sender  The address the transaction is sent from.
     * @param account DDC用户链账户地址
     * @param state   状态 ：Frozen - 冻结状态 ； Active - 活跃状态
     * @return 返回交易哈希
     * @throws Exception
     */
    public String updateAccState(String sender, String account, AccountState state, boolean changePlatformState, RequestOptions options) throws Exception {
        // check sender
        checkSender(sender);

        // check account
        checkAccount(account);

        if (Objects.isNull(state)) {
            throw new DDCException(ErrorMessage.ACCOUNT_STATUS_IS_EMPTY);
        }

        // input params
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(new Address(account));
        arrayList.add(state.getStatus());
        arrayList.add(changePlatformState);

        // send transaction
        RespJsonRpcBean respJsonRpcBean = assembleTransactionAndSend(sender, options, arrayList, AuthorityFunctions.UPDATE_ACCOUNT_STATE, authorityContract);
        return (String) respJsonRpcBean.getResult();
    }

    /**
     * 跨平台授权链账户转移DDC
     *
     * @param sender
     * @param from
     * @param to
     * @param approved
     * @return
     * @throws Exception
     */
    public String crossPlatformApproval(String sender, String from, String to, boolean approved) throws Exception {
        return crossPlatformApproval(sender, from, to, approved, null);
    }

    /**
     * 跨平台授权链账户转移DDC
     *
     * @param sender
     * @param from
     * @param to
     * @param approved
     * @return
     * @throws Exception
     */
    public String crossPlatformApproval(String sender, String from, String to, boolean approved, RequestOptions options) throws Exception {
        // check sender
        checkSender(sender);

        // check from
        checkFrom(from);

        // check to
        checkTo(to);

        // input params
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(new Address(from));
        arrayList.add(new Address(to));
        arrayList.add(approved);

        // send transaction
        RespJsonRpcBean respJsonRpcBean = assembleTransactionAndSend(sender, options, arrayList, AuthorityFunctions.CrossPlatformApproval, authorityContract);
        return (String) respJsonRpcBean.getResult();
    }
}
