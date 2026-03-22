package ra.kienpc.session13.service;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class MailService {
    @Autowired
    private JavaMailSender sender;

    // gửi mail thông thường
    public void sendEmailNormal(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setTo(to);
        mailMessage.setText(content);
        mailMessage.setFrom("Kienpc");
        sender.send(mailMessage);
    }

    // gửi nâng cao
    public void sendMailPro(String to, String cc, String subject, String content, MultipartFile[] files) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setCc(new String[]{cc, "kienpcph52044@gmail.com"});
        helper.setSubject(subject);

        String htmlContent = "<h1 style='color:red'>Hello</h1>" +
                "<p>" + content + "</p>";

        helper.setText(htmlContent, true);

        // gửi file đính kèm
        if (files != null) {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                DataSource dataSource = new ByteArrayDataSource(file.getBytes(), file.getContentType());
                helper.addAttachment(fileName, dataSource);
            }
        }
        sender.send(message);
    }

}
