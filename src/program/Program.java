package program;

import db.DB;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Program {

    public static void main(String[] args) { //inserção simples no banco

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM department");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
            }

            System.out.println("------------------------------------------------");

            SimpleDateFormat niversario = new SimpleDateFormat("dd/MM/yyyy");
         

            ps = conn.prepareStatement(
                "INSERT INTO seller "
                + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                + "VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, "Fulanildo");
            ps.setString(2, "fulanildo@gmail.com");
            ps.setDate(3, new java.sql.Date(niversario.parse("24/12/1980").getTime()));
            ps.setDouble(4, 25000.00);
            ps.setInt(5, 3);

            int rowsAffected = ps.executeUpdate();
            System.out.println("Inserção realizada! Linhas afetadas: " + rowsAffected);
            
            if(rowsAffected > 0){
            rs = ps.getGeneratedKeys();
            
            while(rs.next()){
            int id = rs.getInt(1);
                System.out.println("Done! id = " +id);
            }
            }
            
            else{
                    System.out.println("Nenhuma linha alterada");
                    }
            

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            //DB.closeConnection();
        }
        
        System.out.println("----------UPDATE-----------");
        
        try{
        ps = conn.prepareStatement( //realiza uma atualização dos dados da tabela Seller, nesse caso, o BaseSalary de todos os pertencentes ao departamento 4
            "UPDATE seller "
                    + "SET BaseSalary = BaseSalary + ?"
                    + "WHERE " + "(DepartmentId = ?)");
        
        ps.setDouble(1, 20000.0);
        ps.setInt(2,4);
        
        int rowsAffected = ps.executeUpdate();
        System.out.println("Finalizado!" + rowsAffected);
        
        } catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}