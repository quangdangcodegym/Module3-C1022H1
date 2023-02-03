package com.codegym.helloservlet;

import java.io.*;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";

        System.out.println("Gọi hàm init");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Enumeration<String> headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {
            String temp = headers.nextElement();
            String value = request.getHeader(temp);

            System.out.println("Header: " + temp + " : " + value);
        }
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);

        System.out.println("Chạy vào hàm service");
    }

    public void destroy() {
        System.out.println("Hủy servlet");
    }
}