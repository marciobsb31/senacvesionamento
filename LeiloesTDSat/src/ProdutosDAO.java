import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto(ProdutosDTO produto) {
        // Implementação do cadastro do produto
        // conn = new conectaDAO().connectDB();
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        return listagem;
    }
    
    public void venderProduto(int produtoId) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        
        try {
            // Estabelece a conexão com o banco de dados
            conn = new conectaDAO().connectDB();
            
            // Prepara a declaração SQL para execução
            prep = conn.prepareStatement(sql);
            
            // Define os parâmetros da query
            prep.setString(1, "Vendido");
            prep.setInt(2, produtoId);
            
            // Executa a atualização
            int rowsAffected = prep.executeUpdate();
            
            // Verifica se a atualização foi bem-sucedida
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao vender o produto. Verifique o ID do produto.");
            }
            
        } catch (Exception e) {
            // Trata possíveis exceções
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        } finally {
            try {
                // Fecha a conexão com o banco de dados
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
