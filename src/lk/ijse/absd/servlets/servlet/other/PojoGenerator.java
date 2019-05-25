package lk.ijse.absd.servlets.servlet.other;

import lk.ijse.absd.servlets.dto.CustomerDTO;
import lk.ijse.absd.servlets.dto.ItemDTO;
import lk.ijse.absd.servlets.dto.OrderDTO;
import lk.ijse.absd.servlets.dto.OrderDetailDTO;

import javax.json.*;
import java.io.BufferedReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tharindu
 * Date: 2019-04-06
 * Time: 06:44 PM
 */
public class PojoGenerator {

    /**
     * @param bufferedReader- this will assigned the inputstream by client's request
     * @return the customerDTO mapped by input json
     * will throw nullpointer exception if the expected arguments are not presented
     */
    public CustomerDTO getCustomerDTO(BufferedReader bufferedReader){
        JsonReader reader = Json.createReader(bufferedReader);
        JsonObject customer = reader.readObject();
        int cid = customer.getInt("cid");
        String name = customer.getString("name");
        String address = customer.getString("address");
        String mobile = customer.getString("mobile");
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setCid(cid);
        customerDTO.setName(name);
        customerDTO.setAddress(address);
        customerDTO.setMobile(mobile);
        return customerDTO;
    }

    public ItemDTO getItemDTO(BufferedReader bufferedReader){
        JsonReader reader = Json.createReader(bufferedReader);
        JsonObject item = reader.readObject();
        int code = item.getInt("code");
        String name = item.getString("name");
        double price = Double.parseDouble(item.get("price").toString());
        double qty = Double.parseDouble(item.get("qty").toString());
        ItemDTO itemDTO=new ItemDTO();
        itemDTO.setCode(code);
        itemDTO.setName(name);
        itemDTO.setPrice(price);
        itemDTO.setQty(qty);
        return itemDTO;
    }

    public OrderDTO getOrderDTO(BufferedReader bufferedReader){
        JsonReader reader = Json.createReader(bufferedReader);
        JsonObject orders = reader.readObject();

        List<OrderDetailDTO> orderDetailDTOS=new ArrayList<>();

        int oid=orders.getInt("oid");
        Date date=Date.valueOf(orders.getString("date"));
        double total=Double.parseDouble(orders.get("total").toString());
        int cid = orders.getInt("cid");
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setOid(oid);
        orderDTO.setDate(date);
        orderDTO.setTotal(total);
        orderDTO.setCid(cid);
        JsonArray jsonArray = orders.getJsonArray("orderDetailDTOS");

        for (JsonValue jsonValue:jsonArray) {
            JsonObject jsonObject = jsonValue.asJsonObject();
            int code = jsonObject.getInt("code");
            double unitPrice = Double.parseDouble(jsonObject.get("unitPrice").toString());
            double qty = Double.parseDouble(jsonObject.get("qty").toString());
            OrderDetailDTO orderDetailDTO=new OrderDetailDTO();
            orderDetailDTO.setCode(code);
            orderDetailDTO.setOid(oid);
            orderDetailDTO.setQty(qty);
            orderDetailDTO.setUnitPrice(unitPrice);
            orderDetailDTOS.add(orderDetailDTO);
        }
        orderDTO.setOrderDetailDTOS(orderDetailDTOS);
        return orderDTO;
    }

}
