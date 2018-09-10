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
//    public static final String HOST = "smtp.139.com";
//    public static final String FROM = "18260032917@139.com";\
//    public static final String PWD = "jinhongluo@cs";
    public static final String HOST="mail.sjtu.edu.cn";
    public static final String FROM="optimusprime1997@sjtu.edu.cn" ;
    public static final String PWD="jie!18780107043";

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

    public static String getCurrentHostName() {
//        Enumeration<NetworkInterface> allNetInterfaces = null;
//        try {
//            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//        String host = "localhost";
//        InetAddress ip = null;
//        while (allNetInterfaces.hasMoreElements()) {
//            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//            log.debug("netInterface name is {}", netInterface.getName());
//            if (netInterface.getDisplayName().equals("lo0")) {
//                continue;
//            }
//            Enumeration addresses = netInterface.getInetAddresses();
//            while (addresses.hasMoreElements()) {
//                ip = (InetAddress) addresses.nextElement();
//                if (ip != null && ip instanceof Inet4Address) {
//                    log.info("本机的IP=" + ip.getHostAddress());
//                    host = ip.getHostAddress();
//                    return host;
//                }
//            }
//        }
//        log.error("get ip address failed,host:" + host);
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

