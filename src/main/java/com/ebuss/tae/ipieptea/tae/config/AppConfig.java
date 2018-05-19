package com.ebuss.tae.ipieptea.tae.config;

import com.ebuss.tae.ipieptea.tae.bean.XSLTProvider;
import com.ebuss.tae.ipieptea.tae.bean.XSLTProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public XSLTProvider xsltProvider() {
        return new XSLTProviderImpl();
    }
}
