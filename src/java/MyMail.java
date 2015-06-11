
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyMail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name, email, web, message;
        try {
            HttpSession hs = request.getSession();
          
            if(request.getParameter("name")=="" || request.getParameter("email")=="" || request.getParameter("message")=="")
            {
                response.sendRedirect("contactus.jsp");
            }
            else
            {
            message = "Name :"+request.getParameter("name")+" \n \n From :"+request.getParameter("email")+"\n \n Message : \n \n"+request.getParameter("message");
            String reciever = "rachit.ag1804@gmail.com";
            String host = "smtp.gmail.com";
            String to = "rachit.ag1804@gmail.com";
            String from = "tripadvi2015@gmail.com";
            String pass = "tripadvisor@2015-2016";
            String subject = "Enquiry";
            Properties props = System.getProperties();
            // -- Attaching to default Session, or we could start a new one --
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            Authenticator auth = new MyMail.SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            // -- Create a new message --
            Message msg = new MimeMessage(session);
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress("tripadvi2015@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciever, false));
            msg.setSubject(subject);
            msg.setText(message);
            // -- Set some other header information --
            msg.setHeader("Password", "tripadvisor@2015-2016");
            msg.setSentDate(new Date());
            // -- Send the message --
            Transport.send(msg);
            response.sendRedirect("contactus.jsp");
            } 
        } catch (Exception ex) {
            ex.printStackTrace();
           
            out.println("Exception " + ex);
        }
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            String username = "tripadvi2015@gmail.com";           // specify your email id here (sender's email id)
            String password = "tripadvisor@2015-2016";                                      // specify your password here
            return new PasswordAuthentication(username, password);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
