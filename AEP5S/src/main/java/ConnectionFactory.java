import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection(){
        String url = "jdbc:h2:./bancoAEP";
        String user = "root";
        String senha = "";

        try{
            return DriverManager.getConnection(url,user,senha);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
