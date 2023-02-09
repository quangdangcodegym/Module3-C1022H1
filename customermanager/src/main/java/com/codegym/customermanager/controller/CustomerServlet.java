package com.codegym.customermanager.controller;



import com.codegym.customermanager.model.Customer;
import com.codegym.customermanager.model.CustomerType;
import com.codegym.customermanager.service.inmemory.CustomerService;
import com.codegym.customermanager.service.inmemory.CustomerTypeService;
import com.codegym.customermanager.service.ICustomerService;
import com.codegym.customermanager.service.ICustomerTypeService;
import com.codegym.customermanager.service.mysql.MSCustomerService;
import com.codegym.customermanager.utils.DateUtils;
import com.codegym.customermanager.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customers", "/khachhang"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CustomerServlet extends HttpServlet {

    private ICustomerService iCustomerService;
    private ICustomerTypeService iCustomerTypeService;

    @Override
    public void init() throws ServletException {
        iCustomerService = new MSCustomerService();
        iCustomerTypeService = new CustomerTypeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        System.out.println("Đương dẫn chứa web ở server");
        String appRealPath = getServletContext().getRealPath("/");
        System.out.println(appRealPath);
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateCustomers(req, resp);
                break;
            case "delete":
                showDeleteCustomers(req, resp);
                break;
            default:
                showCustomers(req, resp);
        }



//        resp.sendRedirect("/translate");
    }

    private void showDeleteCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Customer customer = iCustomerService.findCustomerById(id);

        req.setAttribute("customer", customer);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("customer/delete.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createCustomer(req, resp);
                break;
            case "delete":
                deleteCustomer(req, resp);
                break;

        }
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("idDelete"));
        iCustomerService.deleteCustomerById(id);


        resp.sendRedirect("/customers?delete=success");
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Customer customer = new Customer();

        isValidName(req, customer, errors);
        isValidAddress(req, customer, errors);

        String sCreatedAt = req.getParameter("createdAt");
        Date createAt = DateUtils.formatDate(sCreatedAt);
        customer.setCreatedAt(createAt);
//        errors.size()

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/customer/create.jsp");
        if (errors.size() == 0) {
            handleImageUpload(req, customer, errors);
            req.setAttribute("message", "Them thanh cong");
            iCustomerService.createCustomer(customer);
            requestDispatcher.forward(req, resp);
        }else{
            req.setAttribute("errors", errors);
            req.setAttribute("customer", customer);
            requestDispatcher.forward(req, resp);
        }
    }

    private void handleImageUpload(HttpServletRequest req, Customer customer, List<String> errors) throws ServletException, IOException {
        for (Part part : req.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            if(!fileName.equals("")){
                String appRealPath = getServletContext().getRealPath("/") + "images";
                File file = new File(appRealPath);
                if (!file.exists()) {
                    file.mkdir();
                }
                String nameFileServer = appRealPath + File.separator + fileName;
                System.out.println("Name file server: " + nameFileServer);
                part.write(nameFileServer);


                String nameFileProject = "D:\\CODEGYM\\CODEGYM\\Module3\\C1022H1\\customermanager\\src\\main\\webapp\\images" + File.separator + fileName;
                System.out.println("Name file project: " + nameFileProject);
                part.write(nameFileProject);

                customer.setImage(File.separator + "images" + File.separator + fileName);
            }

//            part.write();
        }

    }
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    private void isValidAddress(HttpServletRequest req, Customer customer, List<String> errors) {
        String address = req.getParameter("address");
        if (!ValidateUtils.isAddressValid(address)) {
            errors.add("Địa chỉ không hợp lệ. Chỉ chứa từ từ 8-15 kí và bắt đầu A-Za-z0-9");
        }
        customer.setAddress(address);
    }

    private void isValidName(HttpServletRequest req, Customer customer, List<String> errors) {
        String name = req.getParameter("name");
        if (!ValidateUtils.isNameValid(name)) {
            errors.add("Tên không hợp lệ. Chỉ chứa từ từ 8-15 kí và bắt đầu A-Za-z0-9");
        }
        customer.setName(name);
    }

    private void showCreateCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("customer/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = iCustomerService.getAllCustomer();
        List<CustomerType> customerTypes = iCustomerTypeService.getAllCustomerTypes();
//        customers.size()
        req.setAttribute("customers", customers);
        req.setAttribute("customerTypes", customerTypes );

        String delete = req.getParameter("delete");
        if (delete != null) {
            // delete: success
            req.setAttribute("delete", delete );
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/customer/customers.jsp");
        requestDispatcher.forward(req, resp);
    }
}
