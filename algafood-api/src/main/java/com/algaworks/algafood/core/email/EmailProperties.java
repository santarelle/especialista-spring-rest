package com.algaworks.algafood.core.email;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    @NonNull
    private String remetente;

    private SandBox sandBox = new SandBox();

    private Implementacao impl = Implementacao.MOCK;

    public enum Implementacao {
        SMTP, MOCK, SANDBOX
    }

    @Getter
    @Setter
    public class SandBox {
        private String destinatario;
    }
}
