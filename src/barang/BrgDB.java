/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barang;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class BrgDB {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    
    public BrgDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tokojava","root","");
            System.out.println("Koneksi berhasil");  
        }catch(ClassNotFoundException ex){
             Logger.getLogger(BrgDB.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
             Logger.getLogger(BrgDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insertDB(String kode_barang,String nama_barang,String stok,String harga){
        try {
            String sql = "insert into barang values(?,?,?,?)";
            pst = con.clientPrepareStatement(sql);
            
            pst.setString(1, kode_barang);
            pst.setString(2, nama_barang);
            pst.setString(3, stok);
            pst.setString(4, harga);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BrgDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ResultSet selectDB(){
        
        try {
            String sql = "select * from barang";
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BrgDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    public void updateDB(String kode_barang,String nama_barang,String stok,String harga){
        try {
            String sql = "update barang set nama_barang =?,stok=?,harga=? where kode_barang=?";
            pst = con.clientPrepareStatement(sql);
            
            pst.setString(4, kode_barang);
            pst.setString(1, nama_barang);
            pst.setString(2, stok);
            pst.setString(3, harga);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BrgDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDB(String kode_barang){
        try {
            String sql = "delete from barang where kode_barang =?";
            pst = con.prepareStatement(sql);
            pst.setString(1,kode_barang);  
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BrgDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        

      
    
}
