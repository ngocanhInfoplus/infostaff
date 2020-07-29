package infostaff.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService implements IEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendSimpleMessage(String to, String cc, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		// message.setFrom("ngocanh@infoplusvn.com");
		message.setTo(to);

		if (StringUtils.isNotBlank(cc))
			message.setCc(cc);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);

	}

	@Override
	public void sendMessageWithAttachment(String to, String cc, String subject, String text, String pathToAttachment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendSimpleHtmlMessage(String to, String cc, String subject, String text) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			// helper.setFrom(from);
			helper.setTo(to);
			if (StringUtils.isNotBlank(cc))
				helper.setCc(cc);
			helper.setSubject(subject);
			helper.setText(text, true);

			javaMailSender.send(message);
		} catch (MessagingException e) {
			log.error("Send email error: " + e.toString());
		}
	}
}
