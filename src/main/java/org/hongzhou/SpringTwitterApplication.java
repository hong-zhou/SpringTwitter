package org.hongzhou;

import org.hongzhou.configure.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class SpringTwitterApplication extends WebMvcConfigurerAdapter{

    public static void main(String[] args) {
        SpringApplication.run(SpringTwitterApplication.class, args);
    }
}
