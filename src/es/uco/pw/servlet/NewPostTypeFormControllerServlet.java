package es.uco.pw.servlet;

import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.data.dto.post.DTOPost;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "new post", urlPatterns = "/post/new")
public class NewPostTypeFormControllerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/post/newPostTypeFormView.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DTOPost post = new DTOPost();
        String type = (String) request.getParameter("type");
        post.setType(type);
        session.setAttribute("post", post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/post/postFormView.jsp");
        dispatcher.forward(request, response);
    }
}
