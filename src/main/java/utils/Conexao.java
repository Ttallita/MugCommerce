package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    public Connection getConexao() throws SQLException, ClassNotFoundException{

        driver = "org.postgresql.Driver";
        url = "jdbc:postgresql://localhost/fanLoot";
        user = "postgres";

        //Mudar senha para conexão com banco
        //password = "post24SQL05";
        password = "hitagi710";

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
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
