package com.codegym.helloservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "Translate", urlPatterns = "/translate")
public class Translate extends HttpServlet {

    private Map<String, String> discts;

    @Override
    public void init() throws ServletException {
        discts = new HashMap<>();
        discts.put("hello", "Xin chào");
        discts.put("red", "Màu đỏ");
        discts.put("orange", "Quả cam");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        // "text/plain", "json", "image/jpg"
        resp.setContentType("text/html");


        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form action=\"/translate\" method=\"post\" >\n" +
                "        <input type=\"text\" value=\"\" placeholder=\"...\" name=\"txtName\" >\n" +
                "        <button>Dịch</button>\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("txtName");
        String value = "";
        if (discts.containsKey(name)) {
            value = discts.get(name);
        }

        PrintWriter printWriter = resp.getWriter();

        resp.setContentType("text/html");
        String str = String.format("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form action=\"/translate\" method=\"post\" >\n" +
                "        <input type=\"text\" value=\"\" placeholder=\"...\" name=\"txtName\" >\n" +
                "        <button>Dịch</button>\n" +
                "\n" +
                "        <h1>%s</h1>\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>", value);


        printWriter.println(str);




    }
}
