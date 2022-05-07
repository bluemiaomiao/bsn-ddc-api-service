package io.github.bluemiaomiao.bsnddcapiservice.core.util;


import com.alibaba.fastjson.JSONObject;
import io.github.bluemiaomiao.bsnddcapiservice.core.constant.ErrorMessage;
import io.github.bluemiaomiao.bsnddcapiservice.core.dto.config.BasicConfiguration;

import io.github.bluemiaomiao.bsnddcapiservice.core.exception.DDCException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author wxq
 * @create 2021/12/11 15:47
 * @description JsonFileUtil
 */
public class JsonFileUtil {
    public static BasicConfiguration readJsonFile(File filePath) {
        try {
            String input = FileUtils.readFileToString(filePath, "UTF-8");
            return JSONObject.parseObject(input, BasicConfiguration.class);
        } catch (IOException e) {
            throw new DDCException(ErrorMessage.BASIC_CONFIGURATION_READ_FAILED);
        }
    }
}
