package lk.ijse.absd.servlets.repository.spec.impl;

import lk.ijse.absd.servlets.entity.Customer;
import lk.ijse.absd.servlets.repository.spec.CustomerRepo;
import lk.ijse.absd.servlets.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepoimpl implements CustomerRepo {

    @Override
    public boolean add(Customer c) throws SQLException {
        CrudUtil crudUtil = new CrudUtil();
        boolean b = crudUtil.executeUpdate("INSERT INTO customer VALUES(?,?,?,?)", c.getCid(), c.getName(), c.getAddress(), c.getMobile());
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public boolean update(Customer c) throws SQLException {
        CrudUtil crudUtil = new CrudUtil();
        boolean b = crudUtil.executeUpdate("UPDATE customer SET name=?,address=?,mobile=? WHERE cid=?", c.getName(), c.getAddress(), c.getMobile(), c.getCid());
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public boolean delete(Integer cid) throws SQLException {
        CrudUtil crudUtil = new CrudUtil();
        boolean b = crudUtil.executeUpdate("DELETE FROM customer WHERE cid=?", cid);
        crudUtil.closeConnection();
        return b;
    }

    @Override
    public Customer search(Integer cid) throws SQLException {
        CrudUtil crudUtil = new CrudUtil();
        ResultSet resultSet = crudUtil.executeQuery("SELECT * FROM customer WHERE cid=?", cid);
        if(resultSet.next()){
            Customer customer=new Customer();
            customer.setCid(cid);
            customer.setName(resultSet.getString("name"));
            customer.setAddress(resultSet.getString("address"));
            customer.setMobile(resultSet.getString("mobile"));
            crudUtil.closeConnection();
            return customer;
        }
        crudUtil.closeConnection();
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        CrudUtil crudUtil = new CrudUtil();
        ResultSet resultSet = crudUtil.executeQuery("SELECT * FROM customer");
        ArrayList<Customer>customers=new ArrayList<>();
        while (resultSet.next()){
            Customer customer=new Customer();
            customer.setCid(resultSet.getInt("cid"));
            customer.setName(resultSet.getString("name"));
            customer.setAddress(resultSet.getString("address"));
            customer.setMobile(resultSet.getString("mobile"));
            customers.add(customer);
        }
        crudUtil.closeConnection();
        return customers;
    }
}
