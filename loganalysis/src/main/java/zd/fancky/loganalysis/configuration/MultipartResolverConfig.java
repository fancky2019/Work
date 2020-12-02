package zd.fancky.loganalysis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import zd.fancky.loganalysis.uploadFile.CustomMultipartResolver;

/*
上传文件用
 */
@Configuration
public class MultipartResolverConfig {


    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        return new CustomMultipartResolver();
    }

}
