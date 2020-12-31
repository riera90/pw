package es.uco.pw.servlet;

import es.uco.pw.display.javabean.CustomerBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logout", urlPatterns = "/pwanabeldiego/logout")
public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        CustomerBean customer = (CustomerBean) session.getAttribute("customer");
        customer.clearLoginAttempt();
        customer.setUserId(-1);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
