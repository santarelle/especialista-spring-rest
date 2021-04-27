package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SandBoxEnvioEmailserviceImpl extends SmtpEnvioEmailServiceImpl {

    @Override
    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
        MimeMessage mimeMessage = super.criarMimeMessage(mensagem);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandBox().getDestinatario());
        return mimeMessage;
    }
}
