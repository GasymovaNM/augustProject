package ru.nelly.mychat;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("fullName");
        System.out.println(name);
        HttpSession session = request.getSession();
        session.setAttribute("fullName", name);
        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formatDateTime = dateTime.format(formatter);
        String date = "Дата входа: " + formatDateTime;

        HttpSession session = request.getSession();
        Object fullName = session.getAttribute("fullName");
        if (fullName == null) {
            printWriter.println("<html>");
            printWriter.println("<body>");
            printWriter.println("<h1> Привет! Вы находитесь на главной странице! </h1>");
            printWriter.println("<h1>" + date + "</h1>");
            printWriter.println("<h1> Введите Ваше имя: </h1>");
            printWriter.println("<form action=\"/\" method=\"post\">");
            printWriter.println("<input type=\"text\" name=\"fullName\" size=\"40\">");
            printWriter.println("<input type=\"submit\" value=\"Отправить ответ\">");
            printWriter.println("</form>");
            printWriter.println("</body>");
            printWriter.println("</html>");
        } else {
            printWriter.println("<html>");
            printWriter.println("<body>");
            printWriter.println("<h1> Привет," + fullName + ", вы находитесь на главной странице! </h1>");
            printWriter.println("<h1>" + date + "</h1>");
            printWriter.println("<p><a href=\"/chat\">Перейти в чат</a></p>");
            printWriter.println("<form action=\"/exit\" method=\"post\">");
            printWriter.println("<input type=\"submit\" value=\"Выйти\">");
            printWriter.println("</form>");
            printWriter.println("</body>");
            printWriter.println("</html>");
        }
    }
}
