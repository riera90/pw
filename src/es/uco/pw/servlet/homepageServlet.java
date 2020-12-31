package es.uco.pw.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hello", urlPatterns = "/pw/hello")
public class homepageServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<BODY>");
        out.println("<h1>Hola Mundo</h1>");
        out.println("Bienvenido al servlet<br/>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
