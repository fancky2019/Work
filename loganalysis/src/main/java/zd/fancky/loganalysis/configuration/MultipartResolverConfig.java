package zd.fancky.loganalysis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import zd.fancky.loganalysis.uploadFile.CustomMultipartResolver;

@Configuration
public class MultipartResolverConfig {


    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        return new CustomMultipartResolver();
    }

}
