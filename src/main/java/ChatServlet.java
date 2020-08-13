import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ChatServlet")
public class ChatServlet extends HttpServlet {
    private static final List<Message> messages = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String comment = request.getParameter("comment");
        Message message = new Message();
        message.setMessage(comment);
        Object fullName = request.getSession().getAttribute("fullName");
        if (fullName == null) {
            fullName = "Аноним";
        }
        message.setUserName(fullName.toString());
        messages.add(message);
        response.sendRedirect("/chat");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<body>");
        writer.println("<h3> Администратор: Рады приветствовать всех в нашем чате! </h3>");
        for (Message m : messages) {
            writer.println("<h3>" + m + "<h3>");
        }
        writer.println("<p> Комментарий: <Br>");
        writer.println("<form action=\"/chat\" method=\"post\">");
        writer.println("<textarea name=\"comment\"></textarea> </p>");
        writer.println("<p> <input type = \"submit\" value = \"Отправить\">");
        writer.println("<input type = \"reset\" value = \"Очистить\"></p>");
        writer.println("</form>");
        writer.println("<p><a href=\"/mainpage\">Домой</a></p>");
        writer.println("</body>");
        writer.println("</head>");
        writer.println("</html>");
    }
}
