package lk.ijse.absd.servlets.repository.spec.impl;

import lk.ijse.absd.servlets.entity.OrderDetail_PK;
import lk.ijse.absd.servlets.entity.OrderDetails;
import lk.ijse.absd.servlets.repository.spec.OrderDetailRepo;
import lk.ijse.absd.servlets.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailRepoimpl implements OrderDetailRepo {
    @Override
    public boolean add(OrderDetails od) throws SQLException {
        CrudUtil crudUtil= new CrudUtil();
        boolean b = crudUtil.executeUpdate("INSERT INTO orderDetails VALUES(?,?,?,?)",
                od.getOrderDetail_pk().getCode(), od.getOrderDetail_pk().getOid(), od.getUnitPrice(), od.getQty());
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public boolean update(OrderDetails od) throws SQLException {
        CrudUtil crudUtil= new CrudUtil();
        boolean b = crudUtil.executeUpdate("UPDATE orderDetails SET unitPrice=?, qty=? WHERE oid=? AND code=?",
                od.getUnitPrice(), od.getQty(), od.getOrderDetail_pk().getOid(), od.getOrderDetail_pk().getCode());
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public boolean delete(OrderDetail_PK orderDetail_pk) {
        return false;
    }

    @Override
    public OrderDetails search(OrderDetail_PK orderDetail_pk) {
        return null;
    }

    @Override
    public List<OrderDetails> getAll() throws SQLException {
        CrudUtil crudUtil= new CrudUtil();
        List<OrderDetails>ordersList=new ArrayList<>();
        ResultSet resultSet = crudUtil.executeQuery("SELECT * FROM orderDetails");
        while (resultSet.next()){
            OrderDetails orderDetails=new OrderDetails();
            orderDetails.setOrderDetail_pk(
                    new OrderDetail_PK(
                            resultSet.getInt("code"),
                            resultSet.getInt("oid")
                    )
            );
            orderDetails.setQty(resultSet.getDouble("qty"));
            orderDetails.setUnitPrice(resultSet.getDouble("unitPrice"));
            ordersList.add( orderDetails);
        }
        crudUtil.closeConnection();
        return ordersList;
    }
}
