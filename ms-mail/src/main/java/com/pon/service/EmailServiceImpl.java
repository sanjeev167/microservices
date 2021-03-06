/**
 * 
 */
package com.pon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.pon.entity.Mail;
import com.pon.entity.User;
import com.pon.repository.MailRepository;




/**
 * @author Sanjeev
 *
 */
@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public MailRepository mailRepository;

    @Override
    public void sendSimpleMessage(User input) {
        try {

            Mail newMail = new Mail();
            newMail.setTo(input.getUsername());
            newMail.setSubject("TestSubject");
            newMail.setText("TestText");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(newMail.getTo());
            message.setSubject(newMail.getSubject());
            message.setText(newMail.getText());

            mailRepository.save(newMail);
            System.out.println("EmailServiceImpl => Sanjeev MS-Mail is going to send the mail");
            emailSender.send(message);
            System.out.println("EmailServiceImpl => Sanjeev MS-Mail has been sent");
           
        } catch (MailException exception) {
            exception.printStackTrace();
        }

    }
}