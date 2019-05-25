package lk.ijse.absd.servlets.repository.spec.impl;

import lk.ijse.absd.servlets.entity.Item;
import lk.ijse.absd.servlets.repository.spec.ItemRepo;
import lk.ijse.absd.servlets.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepoimpl implements ItemRepo {
    @Override
    public boolean add(Item i) throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        boolean b = crudUtil.executeUpdate("INSERT INTO item values(?,?,?,?)", i.getCode(), i.getName(), i.getPrice(), i.getQty());
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public boolean update(Item i) throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        boolean b = crudUtil.executeUpdate("UPDATE item SET name=?,price=?,qty=? WHERE code=?", i.getName(), i.getPrice(), i.getQty(), i.getCode());
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public boolean delete(Integer code) throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        boolean b = crudUtil.executeUpdate("DELETE FROM item WHERE code=?", code);
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public Item search(Integer code) throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        ResultSet resultSet = crudUtil.executeQuery("SELECT * FROM item WHERE code=?", code);
        if(resultSet.next()){
            Item item=new Item();
            item.setCode(code);
            item.setName(resultSet.getString("name"));
            item.setPrice(resultSet.getDouble("price"));
            item.setQty(resultSet.getDouble("qty"));
            crudUtil.closeConnection();
            return item;
        }
        crudUtil.closeConnection();
        return null;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        List<Item>items=new ArrayList<>();
        ResultSet resultSet = crudUtil.executeQuery("SELECT * FROM item");
        while (resultSet.next()){
            Item item=new Item();
            item.setCode(resultSet.getInt("code"));
            item.setName(resultSet.getString("name"));
            item.setPrice(resultSet.getDouble("price"));
            item.setQty(resultSet.getDouble("qty"));
            items.add(item);
        }
        crudUtil.closeConnection();
        return items;
    }
}
