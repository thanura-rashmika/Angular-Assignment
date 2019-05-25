package lk.ijse.absd.servlets.util;

import lk.ijse.absd.servlets.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    private Connection connection;

    public CrudUtil() {
        this.connection =  DBConnection.getConnection();
    }

    private PreparedStatement getPreparedStatement(String sql,Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i+1,params[i]);
        }
        return preparedStatement;
    }

    public ResultSet executeQuery(String sql,Object... params) throws SQLException {
        return getPreparedStatement(sql,params).executeQuery();
    }

    public boolean executeUpdate(String sql,Object...params) throws SQLException {
        return getPreparedStatement(sql,params).executeUpdate()>0;
    }

    public void setConnection(Connection connection){
        this.connection=connection;
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }


}
