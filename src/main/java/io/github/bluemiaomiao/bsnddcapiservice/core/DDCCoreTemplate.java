package io.github.bluemiaomiao.bsnddcapiservice.core;

import io.github.bluemiaomiao.bsnddcapiservice.configuration.SecretPropertiesConfiguration;
import io.github.bluemiaomiao.bsnddcapiservice.core.listener.SignEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.utils.Numeric;
import io.github.bluemiaomiao.bsnddcapiservice.core.listener.SignEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DDCCoreTemplate {

    @Autowired
    private SecretPropertiesConfiguration secretProperties;

    // 创建日志器
    private final Logger logger = LoggerFactory.getLogger("BSN DDC Core SDK");

    private DDCSdkClient clientWithNoneEvent = null;
    private DDCSdkClient clientWithDefaultEvent = null;

    private SignEventListener noneEventListener = null;
    private SignEventListener defaultEventListener = null;

    public DDCCoreTemplate() {
        this.noneEventListener = (event) -> {
            return null;
        };
        this.noneEventListener = (event) -> {
            return this.transactionSignature(event.getSender(), event.getRawTransaction());
        };
        this.clientWithNoneEvent = new DDCSdkClient().instance(this.noneEventListener);
        this.clientWithNoneEvent = new DDCSdkClient().instance(this.defaultEventListener);

        this.logger.info("BSN DDC Core SDK Client 初始化..");
    }

    /**
     * 返回一个由空 Event 构建的客户端
     *
     * @return DDCSdkClient 实例
     */
    public DDCSdkClient buildWithNoneEvent() {
        return this.clientWithNoneEvent;
    }

    /**
     * 返回一个由默认 Event 构建的客户端
     *
     * @return DDCSdkClient 实例
     */
    public DDCSdkClient buildWithDefaultEvent() {
        return this.clientWithDefaultEvent;
    }

    private String transactionSignature(String sender, RawTransaction transaction) {
        // TODO: 验证获取到的私钥
        String privateKey = this.secretProperties.getPrivateKey();
        Credentials credentials = Credentials.create(privateKey);
        byte[] signedMessage = TransactionEncoder.signMessage(transaction, 5555, credentials);
        return Numeric.toHexString(signedMessage);
    }
}
