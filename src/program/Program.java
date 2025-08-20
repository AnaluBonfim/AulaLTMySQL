/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program;

import db.DB;
import java.sql.Connection;
/**
 *
 * @author curso
 */
public class Program {
    
    public static void main(String[] args) {
        Connection conn = DB.getConnection();
        
        System.out.println("Conexao realizada com sucesso!");
        
        DB.closeConnection();
    }
    
}
