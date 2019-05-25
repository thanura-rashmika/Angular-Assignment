package lk.ijse.absd.servlets.servlet;

import lk.ijse.absd.servlets.dto.ItemDTO;
import lk.ijse.absd.servlets.service.other.ServiceFactory;
import lk.ijse.absd.servlets.service.spec.ItemService;
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

@WebServlet(value = "/item")
public class ItemServlet extends HttpServlet {
    private PojoGenerator pojoGenerator;
    private JsonResponseGenerator jsonResponseGenerator;
    private ItemService itemService;

    public ItemServlet() {
        this.itemService=new ServiceFactory().getService(ServiceFactory.ServiceTypes.ITEM);
        this.jsonResponseGenerator=new JsonResponseGenerator();
        this.pojoGenerator=new PojoGenerator();
    }

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
                    JsonArray itemDTOList = new JsonResponseGenerator().getByItemDTOList(itemService.getAll());
                    resp.getWriter().println(itemDTOList);
                }catch (RuntimeException r){
                    r.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,r.getMessage());
                }
                break;
            case "search":
                try {
                    resp.setContentType("application/json");
                    ItemDTO itemDTO = itemService.search(Integer.parseInt(req.getParameter("code")));
                    if (itemDTO == null) {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No item for given code !");
                        return;
                    }
                    JsonObject byItemDTO = new JsonResponseGenerator().getByItemDTO(itemDTO);
                    resp.getWriter().println(byItemDTO);
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
                    ItemDTO itemDTO;
                    try {
                        itemDTO = new PojoGenerator().getItemDTO(req.getReader());
                    } catch (NullPointerException n) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid response data " + n.getMessage());
                        return;
                    }
                    boolean add = itemService.add(itemDTO);
                    if(add){
                        resp.setStatus(200);
                        JsonObject forCommonResponse = new JsonResponseGenerator()
                                .getForCommonResponse(Constaants.ADDED, "Item is added successfully !");
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
                    ItemDTO itemDTO;
                    try {
                        itemDTO = new PojoGenerator().getItemDTO(req.getReader());
                    } catch (NullPointerException n) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid response data " + n.getMessage());
                        return;
                    }
                    boolean add = itemService.update(itemDTO);
                    if(add){
                        resp.setStatus(200);
                        JsonObject forCommonResponse = new JsonResponseGenerator()
                                .getForCommonResponse(Constaants.ADDED, "Item is updated successfully !");
                        resp.getWriter().println(forCommonResponse);
                        return;
                    }else {
                        resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Failed to update ");
                        return;
                    }
                }catch (RuntimeException r){
                    r.printStackTrace();
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
                    String code = req.getParameter("code");
                    if (code == null) {
                        resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Item code is missing !");
                        return;
                    }
                    boolean delete = itemService.delete(Integer.parseInt(code));
                    if(delete){
                        resp.setStatus(200);
                        JsonObject forCommonResponse = new JsonResponseGenerator()
                                .getForCommonResponse(Constaants.DELETED, "Item is deleted !");
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
