package com.algaworks.algafood.core.email;

import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.MockEnvioEmailServiceImpl;
import com.algaworks.algafood.infrastructure.service.email.SmtpEnvioEmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        switch (emailProperties.getImpl()) {
            case MOCK:
                return new MockEnvioEmailServiceImpl();
            case SMTP:
                return new SmtpEnvioEmailServiceImpl();
            default:
                return null;
        }
    }
}
