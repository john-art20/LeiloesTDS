
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    


    public static Connection connectDB() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String usuario = "root";
        String senha = "@Joao1710";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO (Driver): " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + e.getMessage());
        }

        return conn;
    }
}
    

