/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
    
    String sql = "INSERT INTO produtos ( nome, valor, status) VALUES (?,?,?)";
    
    try (Connection conn = conectaDAO.connectDB();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        
        stmt.setString(1,produto.getNome()); 
        stmt.setInt(2, produto.getValor());
        stmt.setString(3, produto.getStatus());
        
        int linhasAfetadas = stmt.executeUpdate();
        
        if (linhasAfetadas > 0) {
            System.out.println("->Cadastro concluido  com sucesso!");
             JOptionPane.showMessageDialog(null, "Cadastro concluido  com sucesso!"); 
        }
        
    } catch (SQLException e) {
        System.out.println("Erro ao realizar o cadastro: " + e.getMessage());
    }

    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    String sql = "SELECT id, nome, valor, status FROM produtos";

    try (Connection conn = conectaDAO.connectDB();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while(rs.next()){
        ProdutosDTO dto = new ProdutosDTO();
        
        dto.setId(rs.getInt("id"));
        dto.setNome(rs.getString("nome"));
        dto.setValor(rs.getInt("valor"));
        dto.setStatus(rs.getString("status"));
        listagem.add(dto);
        
        
        }
        
        
    }   catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      return listagem;
    }
    
    public ProdutosDTO buscarProdutoPorId(ProdutosDTO products) {
    String sql = "SELECT id, nome, valor, status FROM produtos WHERE id = ?";

    try (Connection conn = conectaDAO.connectDB();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, products.getId());

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                ProdutosDTO dto = new ProdutosDTO();
                dto.setId(rs.getInt("id"));
                dto.setNome(rs.getString("nome"));
                dto.setValor(rs.getInt("valor"));
                dto.setStatus(rs.getString("status"));
                return dto;
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar produto: " + e.getMessage());
    }

    return null;
}
    
public void venderProduto( ProdutosDTO products) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try (Connection conn = conectaDAO.connectDB();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, products.getId());

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("-> Produto vendido com sucesso!");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + ex.getMessage());
    }
}
    
    
    
    
    
}

