package io.github.bluemiaomiao.bsnddcapiservice.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesUtil {
    public Properties loadSDKProperties() throws IOException {
        InputStream stream = new BufferedInputStream(Files.newInputStream(
                Paths.get("src/main/resources/sdk.properties")));
        Properties properties = new Properties();
        properties.load(stream);
        return properties;
    }
}
