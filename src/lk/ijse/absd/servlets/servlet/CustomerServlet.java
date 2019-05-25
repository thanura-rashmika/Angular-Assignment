package lk.ijse.absd.servlets.servlet;

import lk.ijse.absd.servlets.dto.CustomerDTO;
import lk.ijse.absd.servlets.service.other.ServiceFactory;
import lk.ijse.absd.servlets.service.spec.CustomerService;
import lk.ijse.absd.servlets.servlet.other.JsonResponseGenerator;
import lk.ijse.absd.servlets.servlet.other.PojoGenerator;
import lk.ijse.absd.servlets.util.Constaants;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService=new ServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            boolean operation = req.getHeader("operation").isEmpty();
        } catch (NullPointerException n){
            resp.sendError(400,"No operation header is presented ! ");
            return;
        }
        switch (req.getHeader("operation")){
            case "getAll":
                try {
                    resp.setContentType("application/json");
                    JsonArray customerDTOList = new JsonResponseGenerator().getByCustomerDTOList(customerService.getAll());
                    resp.getWriter().println(customerDTOList);
                }catch (RuntimeException r){
                    r.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,r.getMessage());
                }
                break;
            case "search":
                try {
                    resp.setContentType("application/json");
                    CustomerDTO customerDTO = customerService.search(Integer.parseInt(req.getParameter("cid")));
                    if (customerDTO == null) {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No customer for given id !");
                        return;
                    }
                    JsonObject byCustomerDTO = new JsonResponseGenerator().getByCustomerDTO(customerDTO);
                    resp.getWriter().println(byCustomerDTO);
                }catch (RuntimeException r){
                    r.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,r.getMessage());
                }
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Get Operation");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            boolean operation = req.getHeader("operation").isEmpty();
        } catch (NullPointerException n){
            resp.sendError(400,"No operation header is presented ! ");
            return;
        }
        switch (req.getHeader("operation")){
            case "add":
                try {
                    CustomerDTO customerDTO;
                    try {
                        customerDTO = new PojoGenerator().getCustomerDTO(req.getReader());
                    } catch (NullPointerException n) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid response data " + n.getMessage());
                        return;
                    }
                    boolean add = customerService.add(customerDTO);
                    if(add){
                        resp.setStatus(200);
                        JsonObject forCommonResponse = new JsonResponseGenerator()
                                .getForCommonResponse(Constaants.ADDED, "Customer is added successfully !");
                        resp.getWriter().println(forCommonResponse);
                        return;
                    }else {
                        JsonObject forCommonResponse = new JsonResponseGenerator()
                                .getForCommonResponse(HttpServletResponse.SC_EXPECTATION_FAILED, "Failed to add !");
                        resp.getWriter().println(forCommonResponse);
                        return;
                    }
                }catch (RuntimeException r){
                    r.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,r.getMessage());
                }
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Post Operation");
                break;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            boolean operation = req.getHeader("operation").isEmpty();
        } catch (NullPointerException n){
            resp.sendError(400,"No operation header is presented ! ");
            return;
        }
        switch (req.getHeader("operation")){
            case "update":
                resp.setContentType("application/json");
                try {
                    CustomerDTO customerDTO;
                    try {
                        customerDTO = new PojoGenerator().getCustomerDTO(req.getReader());
                    } catch (NullPointerException n) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid response data " + n.getMessage());
                        return;
                    }
                    boolean update = customerService.update(customerDTO);
                    if(update){
                        resp.setStatus(200);
                        JsonObject forCommonResponse = new JsonResponseGenerator()
                                .getForCommonResponse(Constaants.UPDATED, "Customer is updated successfully !");
                        resp.getWriter().println(forCommonResponse);
                        return;
                    }else {
                        JsonObject forCommonResponse = new JsonResponseGenerator()
                                .getForCommonResponse(HttpServletResponse.SC_EXPECTATION_FAILED, "Failed to update !");
                        resp.getWriter().println(forCommonResponse);
                        return;
                    }
                }catch (RuntimeException r){
                    resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,r.getMessage());
                }
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Put Operation");
                break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            boolean operation = req.getHeader("operation").isEmpty();
        } catch (NullPointerException n){
            resp.sendError(400,"No operation header is presented ! ");
            return;
        }
        switch (req.getHeader("operation")){
            case "delete":
                resp.setContentType("application/json");
                try {
                    String cid = req.getParameter("cid");
                    if (cid == null) {
                        resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Customer ID is missing !");
                        return;
                    }
                    boolean delete = customerService.delete(Integer.parseInt(cid));
                    if(delete){
                        resp.setStatus(200);
                        JsonObject forCommonResponse = new JsonResponseGenerator()
                                .getForCommonResponse(Constaants.DELETED, "Customer is deleted !");
                        resp.getWriter().println(forCommonResponse);
                        return;
                    }else {
                        resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Failed to delete !");
                        return;
                    }
                }catch (RuntimeException r){
                    resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,r.getMessage());
                }
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Delete Operation");
                break;
        }
    }

}
