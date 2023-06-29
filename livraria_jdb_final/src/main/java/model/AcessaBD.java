package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessaBD {
    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/livraria?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "");
            
            Statement stmt = con.createStatement();
            
            // Adicionar uma nova editora
            String cnpj = "87.557.922/0001-82";
            String nomeEditora = "Seguinte";
            String queryEditora = String.format("INSERT INTO editora (CNPJ, Nome) VALUES ('%s', '%s')",
                    cnpj, nomeEditora);
            stmt.executeUpdate(queryEditora);
            
            // Adicionar um novo livro (O Dia do Curinga)
            String tituloLivro1 = "O Dia do Curinga";
            String autorLivro1 = "Jostein Gaarder";
            int anoLivro1 = 1996;
            float precoLivro1 = 29.99f;
            String editoraLivro1 = "Seguinte";
            String queryLivro1 = String.format("INSERT INTO livro (Titulo, Autor, Ano, Preco, Editora) " +
                    "VALUES ('%s', '%s', %d, %.2f, '%s')", tituloLivro1, autorLivro1, anoLivro1, precoLivro1, editoraLivro1);
            stmt.executeUpdate(queryLivro1);
            
            // Adicionar um novo livro (A Revolução dos Bichos)
            String tituloLivro2 = "A Revolução dos Bichos";
            String autorLivro2 = "George Orwell";
            int anoLivro2 = 2007;
            float precoLivro2 = 23.90f;
            String editoraLivro2 = "Companhia das Letras";
            String queryLivro2 = String.format("INSERT INTO livro (Titulo, Autor, Ano, Preco, Editora) " +
                    "VALUES ('%s', '%s', %d, %.2f, '%s')", tituloLivro2, autorLivro2, anoLivro2, precoLivro2, editoraLivro2);
            stmt.executeUpdate(queryLivro2);

            Statement stmt2 = con.createStatement();
            ResultSet rs = stmt2.executeQuery("select * from livro");
            while (rs.next()) {
                System.out.print(rs.getString("titulo"));
                System.out.print(", " + rs.getString("autor"));
                System.out.print(", " + rs.getInt("ano"));
                System.out.println(" (R$ " + rs.getFloat("preco") + ")");
            }
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
