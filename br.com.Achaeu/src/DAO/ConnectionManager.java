package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection conexaoAtiva;
        
    private ConnectionManager(){
        
    }
    
    public static Connection getConexao() throws SQLException{
        if(conexaoAtiva == null){
            return DriverManager.getConnection(ConnectionConstants.url,ConnectionConstants.user,ConnectionConstants.password);
        }
        else{
            return conexaoAtiva;
        }
    }
    
}
