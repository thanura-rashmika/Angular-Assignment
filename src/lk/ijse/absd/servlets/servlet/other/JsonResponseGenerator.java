package lk.ijse.absd.servlets.servlet.other;

import lk.ijse.absd.servlets.dto.CustomerDTO;
import lk.ijse.absd.servlets.dto.ItemDTO;

import javax.json.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tharindu
 * Date: 2019-04-06
 * Time: 06:57 PM
 */
public class JsonResponseGenerator {

    public JsonObject getByCustomerDTO(CustomerDTO customerDTO){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("cid",customerDTO.getCid());
        objectBuilder.add("name",customerDTO.getName());
        objectBuilder.add("address",customerDTO.getAddress());
        objectBuilder.add("mobile",customerDTO.getMobile());
        return objectBuilder.build();
    }

    public JsonArray getByCustomerDTOList(List<CustomerDTO> customerDTOS){
        JsonArrayBuilder jsonArrayBuilder=Json.createArrayBuilder();
        customerDTOS.forEach(customerDTO -> {
            jsonArrayBuilder.add(getByCustomerDTO(customerDTO));
        });
        return jsonArrayBuilder.build();
    }

    public JsonObject getByItemDTO(ItemDTO itemDTO){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("code",itemDTO.getCode());
        objectBuilder.add("name",itemDTO.getName());
        objectBuilder.add("price",itemDTO.getPrice());
        objectBuilder.add("qty",itemDTO.getQty());
        return objectBuilder.build();
    }

    public JsonArray getByItemDTOList(List<ItemDTO> itemDTOS){
        JsonArrayBuilder jsonArrayBuilder=Json.createArrayBuilder();
        itemDTOS.forEach(ItemDTO -> {
            jsonArrayBuilder.add(getByItemDTO(ItemDTO));
        });
        return jsonArrayBuilder.build();
    }

    public JsonObject getForCommonResponse(Integer code, String message){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("code",code);
        objectBuilder.add("message",message);
        return objectBuilder.build();
    }
}
