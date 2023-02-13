package com.codegym.casetemplate.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateProducts(req, resp);
                break;
            case "delete":
                showDeleteProducts(req, resp);
                break;
            case "edit":
                showEditProducts(req, resp);
                break;
            default:
                showProducts(req, resp);
        }
    }

    private void showProducts(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void showEditProducts(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showDeleteProducts(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showCreateProducts(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}