package infostaff.service;

public interface IEmailService {
	void sendSimpleMessage(String to, String cc, String subject, String text);

	void sendMessageWithAttachment(String to, String cc, String subject, String text, String pathToAttachment);

	void sendSimpleHtmlMessage(String to, String cc, String subject, String text);
}
