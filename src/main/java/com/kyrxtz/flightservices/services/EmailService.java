package com.kyrxtz.flightservices.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Value("${sendgrid.apikey}")
    private String apiKey;
    
    public void Send(String email, String code, String airline, String departureCity, String arrivalCity, LocalDateTime departureDate) throws IOException {
        Mail mail = new Mail();

        Email from = new Email();
        from.setName("Your Reservation with FlightScanner");
        from.setEmail("flightscannerltd@gmail.com");
        mail.setFrom(from);

        String subject = "Your Reservation with FlightScanner";
        mail.setSubject(subject);

        Email to = new Email();
        to.setEmail(email);

        Personalization personalization = new Personalization();
        personalization.addTo(to);
        personalization.setSubject(subject);
        personalization.addDynamicTemplateData("reservationIdentifier",code);
        personalization.addDynamicTemplateData("reservationAirline",airline);
        personalization.addDynamicTemplateData("departureCity",departureCity);
        personalization.addDynamicTemplateData("arrivalCity",arrivalCity);
        String[] dateTime = departureDate.toString().split("T");
        personalization.addDynamicTemplateData("departureDate",dateTime[0]);
        personalization.addDynamicTemplateData("departureTime",dateTime[1]);

        mail.addPersonalization(personalization);

        Content content = new Content();
        content.setType("text/html");
        content.setValue("Something");
        mail.addContent(content);

        mail.setTemplateId("d-7ab75c0545a54b43939c01fb4783526b");

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
  }
}