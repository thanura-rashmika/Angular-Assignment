package lk.ijse.absd.servlets.servlet;

import lk.ijse.absd.servlets.dto.OrderDTO;
import lk.ijse.absd.servlets.service.other.ServiceFactory;
import lk.ijse.absd.servlets.service.spec.OrderService;
import lk.ijse.absd.servlets.servlet.other.JsonResponseGenerator;
import lk.ijse.absd.servlets.servlet.other.PojoGenerator;
import lk.ijse.absd.servlets.util.Constaants;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/orders")
public class OrdersServlet extends HttpServlet {

    private OrderService orderService;
    private PojoGenerator pojoGenerator;
    private JsonResponseGenerator jsonResponseGenerator;

    public OrdersServlet() {
        this.orderService=new ServiceFactory().getService(ServiceFactory.ServiceTypes.ORDERS);
        this.pojoGenerator=new PojoGenerator();
        this.jsonResponseGenerator=new JsonResponseGenerator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
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
                    OrderDTO orderDTO;
                    try {
                        orderDTO = new PojoGenerator().getOrderDTO(req.getReader());
                    } catch (NullPointerException n) {
                        n.printStackTrace();
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid response data " + n.getMessage());
                        return;
                    }
                    boolean add = orderService.add(orderDTO);
                    if(add){
                        resp.setStatus(200);
                        JsonObject forCommonResponse = new JsonResponseGenerator()
                                .getForCommonResponse(Constaants.ADDED, "Order is added successfully !");
                        resp.getWriter().println(forCommonResponse);
                        return;
                    }else {
                        resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Failed to add ");
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
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
