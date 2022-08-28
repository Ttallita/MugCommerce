package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public Connection getConexao() throws SQLException, ClassNotFoundException{

        final String driver = "org.postgresql.Driver";
        final String url = "jdbc:postgresql://localhost/emug";
        final String user = "postgres";

        //Mudar senha para conexão com banco
        //password = "post24SQL05";
        String password = "hitagi710";

        Class.forName(driver);

        return DriverManager.getConnection(url, user, password);
    }

    public void fecharConexao(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            }catch (Exception e) {
               System.err.println(e.getMessage());
            }
        }
    }

}
