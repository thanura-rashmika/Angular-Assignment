package lk.ijse.absd.servlets.repository.spec.impl;

import lk.ijse.absd.servlets.entity.Orders;
import lk.ijse.absd.servlets.repository.spec.OrderRepo;
import lk.ijse.absd.servlets.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepoimpl implements OrderRepo {
    @Override
    public boolean add(Orders orders) throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        boolean b = crudUtil.executeUpdate("INSERT INTO orders VALUES(?,?,?,?)", orders.getOid(), orders.getDate(), orders.getTotal(), orders.getCid());
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public boolean update(Orders orders) throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        boolean b = crudUtil.executeUpdate("UPDATE orders SET date=?, total=?, cid=? WHERE oid=?", orders.getDate(), orders.getTotal(), orders.getCid(), orders.getOid());
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public boolean delete(Integer oid) throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        boolean b = crudUtil.executeUpdate("DELETE FROM orders WHERE oid=?", oid);
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public Orders search(Integer oid) throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        ResultSet resultSet = crudUtil.executeQuery("SELECT * FROM orders WHERE oid=?", oid);
        if(resultSet.next()){
            Orders orders=new Orders();
            orders.setOid(oid);
            orders.setDate(resultSet.getDate("date"));
            orders.setTotal(resultSet.getDouble("total"));
            orders.setCid(resultSet.getInt("cid"));
            return orders;
        }
        crudUtil.closeConnection();
        return null;
    }

    @Override
    public List<Orders> getAll() throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        List<Orders>ordersList=new ArrayList<>();
        ResultSet resultSet = crudUtil.executeQuery("SELECT * FROM orders");
        while (resultSet.next()){
            Orders orders=new Orders();
            orders.setOid(resultSet.getInt("oid"));
            orders.setDate(resultSet.getDate("date"));
            orders.setTotal(resultSet.getDouble("total"));
            orders.setCid(resultSet.getInt("cid"));
            ordersList.add( orders);
        }
        crudUtil.closeConnection();
        return ordersList;
    }

    @Override
    public int getLastOrderId() throws SQLException {
        CrudUtil crudUtil=new CrudUtil();
        ResultSet resultSet = crudUtil.executeQuery("SELECT oid as oid FROM orders ORDER BY oid DESC LIMIT 1");
        if(resultSet.next()){
            int oid = resultSet.getInt("oid");
            crudUtil.closeConnection();
            return oid;
        }
        crudUtil.closeConnection();
        return 0;
    }
}
