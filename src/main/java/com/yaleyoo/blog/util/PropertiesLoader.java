package com.yaleyoo.blog.util;

import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by steve on 13/3/19.
 */
@Component
public class PropertiesLoader {
    private final String PATH = "application.properties";
    private static final String CONFIG_NAME = "yaleyoo.blog.";

    public Optional<String> loadValue(String key){
        String filepath = Objects.requireNonNull(PropertiesUtil.class.getClassLoader().getResource(PATH)).getPath();
        try (InputStream inStream = new FileInputStream(filepath)){
            Properties prop = new Properties();
            prop.load(inStream);

            return Optional.ofNullable(prop.getProperty(CONFIG_NAME + key));
        } catch(IOException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
