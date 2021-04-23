package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class SmtpEnvioEmailServiceImpl implements EnvioEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freeMarkerConfig;

    @Override
    public void enviar(Mensagem message) {
        try {
            String corpoMensagem = processarTemplate(message);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(message.getDestinatarios().toArray(new String[0]));
            helper.setSubject(message.getAssunto());
            helper.setText(corpoMensagem, true);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possivel enviar e-mail", e);
        }
    }

    private String processarTemplate(Mensagem message) {
        try {
            Template template = freeMarkerConfig.getTemplate(message.getTemplateName());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não foi possivel montar o corpo do e-mail");
        }
    }
}
