package sd.assignment.Service.Utils;

import sd.assignment.Service.DTO.FoodDTO;
import sd.assignment.Service.DTO.PlaceOrderDTO;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


public class MailSender {

    private final Session session;
    private final String from = "medicdebuzunar@gmail.com";


    public MailSender() {
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("email@gmail.com", "password");
            }
        });
        session.setDebug(true);
    }


    public void send(String to, List<FoodDTO> foods, Integer price, PlaceOrderDTO order) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Order " + order.getId());

            String content = foods.stream().map(food -> new String(food.getCategory() + ": " + food.getName() +
                    "\nprice: " + food.getPrice() + " quantity: " + food.getQuantity() + "\n\n"))
                    .collect(Collectors.toList()).toString();
            content += "Total: " + price
                    + "\naddress: " + order.getAddress()
                    + "\ndetails: " + order.getDetails();
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}