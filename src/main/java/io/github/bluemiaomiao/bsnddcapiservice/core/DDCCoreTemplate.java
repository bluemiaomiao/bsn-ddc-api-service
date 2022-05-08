package io.github.bluemiaomiao.bsnddcapiservice.core;

import io.github.bluemiaomiao.bsnddcapiservice.core.net.DDCWuhan;
import io.github.bluemiaomiao.bsnddcapiservice.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.utils.Numeric;
import io.github.bluemiaomiao.bsnddcapiservice.core.listener.SignEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class DDCCoreTemplate {

    // 创建日志器
    private final Logger logger = LoggerFactory.getLogger("BSN DDC Core SDK");

    private DDCSdkClient clientWithNoneEvent = null;
    private DDCSdkClient clientWithDefaultEvent = null;

    private SignEventListener noneEventListener = null;
    private SignEventListener defaultEventListener = null;

    private Properties properties = null;

    public DDCCoreTemplate() {

        PropertiesUtil propertiesUtil = new PropertiesUtil();
        try {
            this.properties = propertiesUtil.loadSDKProperties();
        } catch (IOException e) {
            this.logger.error(String.format("加载 SDK 配置失败: %s", e.getMessage()));
        }

        this.noneEventListener = (event) -> {
            return null;
        };
        this.defaultEventListener = (event) -> {
            return this.transactionSignature(event.getSender(), event.getRawTransaction());
        };

        this.clientWithNoneEvent = new DDCSdkClient().instance(this.noneEventListener);
        this.clientWithNoneEvent = new DDCSdkClient().instance(this.defaultEventListener);

        this.logger.info("BSN DDC Core SDK 客户端初始化..");
        this.logger.info(String.format("加载私钥: %s", this.properties.getProperty("private-key")));
        this.logger.info(String.format("加载网关地址: %s", this.properties.getProperty("gateway-url")));

        DDCWuhan.setGatewayUrl(this.properties.getProperty("gateway-url"));
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
        String privateKey = this.properties.getProperty("private-key");
        Credentials credentials = Credentials.create(privateKey);
        byte[] signedMessage = TransactionEncoder.signMessage(transaction, 5555, credentials);
        return Numeric.toHexString(signedMessage);
    }
}
