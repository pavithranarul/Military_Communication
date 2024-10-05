package servlet;

import java.io.IOException;

import dao.MessageDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Message;

public class SendMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("messageContent");
        int receiverId = Integer.parseInt(request.getParameter("receiverId"));
        int senderId = Integer.parseInt(request.getParameter("senderId"));

        Message message = new Message();
        message.setContent(content);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);

        MessageDao messageDao = new MessageDao();
        messageDao.saveMessage(message);

        response.sendRedirect("dashboard.jsp");
    }
}
