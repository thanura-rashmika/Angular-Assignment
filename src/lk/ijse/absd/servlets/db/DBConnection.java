package lk.ijse.absd.servlets.db;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;


public class DBConnection {

    public static Connection getConnection(){
        try {

            InitialContext initialContext=new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/pool");
            return dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
            return null;
            //throw new RuntimeException(Arrays.toString(e.getStackTrace()));
        }
    }
}
