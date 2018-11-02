package com.amateur.occupation.util;



import com.amateur.occupation.constant.Const;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


@Slf4j
public class EmailUtil {
    public static final String PROTOCOL = "smtp";
    public static final int PORT = 25;

    public static final String HOST="yourhost";
    public static final String FROM="youremailaddress" ;
    public static final String PWD="yourpassword";
    /**
     *\
     *
     * @return
     */
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.store.protocol", PROTOCOL);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", true);

        Authenticator authenticator = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getDefaultInstance(props, authenticator);

        return session;
    }
    //get current host name
    public static String getCurrentHostName() {
        return Const.LOCAL_IP;
    }

    public static void sendUpdatePassword(String email, String password) {
        StringBuilder builder = new StringBuilder(
                "Please Click following link to reset your Occupation account password!\n");
        String halfUrl = "http://" + getCurrentHostName() + ":" + Const.PORT + "/occupation/user/update_password";
        String url = halfUrl +
                "?email=" + Enycryption.encryptBASE64(email.getBytes())
                + "&password=" + Enycryption.encryptBASE64(password.getBytes());

        builder.append("<a href='" + url + "'>" + halfUrl + "</a>");
        send(email, builder.toString());
    }

    public static void sendAdd(String email, String typeUrl, String dataStr) {
        StringBuilder builder = new StringBuilder(
                "Please Click following link to verify your Occupation account!\n");
        String halfUrl = "http://" + getCurrentHostName() + ":" + Const.PORT + "/occupation/" + typeUrl + "/add";
        String url = halfUrl
                + "?data=" + Enycryption.encryptBASE64(dataStr.getBytes());
        builder.append("<a href='" + url + "'>" + halfUrl + "</a>");
        send(email, builder.toString());
    }


    public static void send(String email, String content) {
        Session session = getSession();
        try {
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(email)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Occupation");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");

            //Send the message
            Transport.send(msg);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}

