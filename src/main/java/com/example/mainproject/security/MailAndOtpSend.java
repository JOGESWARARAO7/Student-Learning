package com.example.mainproject.security;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailAndOtpSend {

	@Autowired
	private JavaMailSender mailSender;
	
	
	public ResponseEntity<String> sendSimpleEmail(String toemail,int otp,String body,String subjcetString) {
		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("prasanna.vsp80@gmail.com");
		message.setTo(toemail);
		String fullBody = body + "\n\nYour OTP is: " + otp;
	    message.setText(fullBody);
		message.setSubject(subjcetString);
		try {
            mailSender.send(message);
            return ResponseEntity.ok("OTP Sent");
        } catch (MailException e) {
             //Log the exception (you can use a logger here)
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid Email");
        }
		
	}
	public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP
        return String.valueOf(otp);
    }
}
