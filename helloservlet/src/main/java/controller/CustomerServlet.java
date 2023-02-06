package controller;

import model.Customer;
import service.CustomerService;
import service.ICustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customers", "/khachhang"})
public class CustomerServlet extends HttpServlet {

    private ICustomerService iCustomerService;

    @Override
    public void init() throws ServletException {
        iCustomerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = iCustomerService.getAllCustomer();
//        customers.size()
        req.setAttribute("customers", customers);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/customer/customers.jsp");
        requestDispatcher.forward(req, resp);


//        resp.sendRedirect("/translate");
    }
}
