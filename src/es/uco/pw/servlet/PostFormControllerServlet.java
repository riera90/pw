package es.uco.pw.servlet;

import es.uco.pw.business.Daemons.FlashPostDaemon;
import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.data.dto.post.DTOPost;
import es.uco.pw.display.javabean.CustomerBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "post", urlPatterns = "/post")
public class PostFormControllerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FlashPostDaemon.iteration();
        HttpSession session = request.getSession();
        Integer postId = -1;
        try {
            postId =  Integer.parseInt(request.getParameter("id"));
            System.out.println("post id is <"+postId+">");
            session.setAttribute("post", new DAOPost().get(postId)); // if the post does not exist create a new one.
        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("post", new DTOPost());
        }
        RequestDispatcher dispatcher;
        if (postId == -1)
            dispatcher = request.getRequestDispatcher("/mvc/view/post/newPostTypeFormView.jsp");
        else
            dispatcher = request.getRequestDispatcher("/mvc/view/post/postFormView.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String type = request.getParameter("type");
        String[] topicsString = request.getParameterValues("topics");
        String[] targetsString = request.getParameterValues("targets");
        String publicationTime = request.getParameter("publicationTime");
        String deleteTime = request.getParameter("deleteTime");
        String state = request.getParameter("state");

        System.out.println("publicationTime: "+publicationTime);
        System.out.println("deleteTime "+deleteTime);
        System.out.println("id "+idString);

        String datetimeFormat = "yyyy-MM-dd'T'hh:mm";
        Date publicationDatetime = null;
        Date deleteDatetime = null;
        try {
            publicationDatetime = new SimpleDateFormat(datetimeFormat).parse(publicationTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            deleteDatetime = new SimpleDateFormat(datetimeFormat).parse(publicationTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer id = -1;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        DAOPost postController = new DAOPost();
        DTOPost post = new DTOPost();

        if (id != -1)
            post = postController.get(id);

        post.setTitle(title);
        post.setBody(body);
        post.setDeletedAt(deleteDatetime);
        post.setPublishedAt(publicationDatetime);
        post.clearTopics();
        if (topicsString != null){
            for (String topic:topicsString){
                try {
                    post.addTopic(Integer.parseInt(topic));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        post.clearSentTo();
        if (targetsString != null) {
            for (String target : targetsString) {
                try {
                    post.addSentTo(Integer.parseInt(target));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        HttpSession session = request.getSession();
        CustomerBean customer = (CustomerBean) session.getAttribute("customer");
        post.setOwnerId(customer.getUserId());
        post.setType(type);
        post.setState(state);

        System.out.println(post);

        postController.put(post);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/control/post/postPanelController.jsp");
        dispatcher.forward(request, response);
    }
}
