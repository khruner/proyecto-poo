package com.mycompany.tacotitosv2.Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class IngredienteDAO {
    protected Connection cn;
    protected Conexion conexion = new Conexion();
    protected static IngredienteDAO instance;
    protected PreparedStatement ps;
    
    public static IngredienteDAO getInstance() throws SQLException {
        if ( instance == null ) {
            instance = new IngredienteDAO();
        }
        return instance;
    }
    
    public void guardar(Ingrediente i) throws SQLException {
        if ( i.getId() == 0 ) {
            nuevo(i);
        } else {
            modificar(i);
        }
    }
    
    private void nuevo(Ingrediente i) throws SQLException {
        String sql = "INSERT INTO Ingredientes(nombre, precio, fk_tipo) VALUES(?, ?, ?);";
        try{
            cn = conexion.getConnection();
            ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, i.getNombre());
            ps.setDouble(2, i.getPrecio());
            ps.setInt(3, i.getTipo_ID());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if ( rs.next() ) {
                i.setId(rs.getInt(1));
            }
        } catch (Exception e){
            System.out.println("No se pudo agregar un ingrediente");
        }
    }
    
    public void modificar(Ingrediente i) throws SQLException {
        String sql = "UPDATE Ingredientes set nombre = ?, precio = ? WHERE id = ?;";
        try{
            cn = conexion.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, i.getNombre());
            ps.setDouble(2, i.getPrecio());
            ps.setInt(3, i.getId());
            ps.execute();
        } catch (Exception e){
            System.out.print(e.toString());
            System.out.println("No se pudo modificar");
        }
    }
    
    public Ingrediente porId(int id) throws SQLException {
        String sql = "SELECT nombre, precio FROM Ingredientes WHERE id = ?;";
        Ingrediente i = null;
        try {
            cn = conexion.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                i = new Ingrediente(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getDouble("precio"),
                                rs.getInt("fk_tipo"));
                i.setId(id);
            }
        } catch (Exception e){
        }
        return i;
    }
    
    public List<Ingrediente> listar(){
        List<Ingrediente> salida = new ArrayList();
        Ingrediente i;
        try{
            cn = conexion.getConnection();
            String sql = "SELECT id, nombre, precio, fk_tipo FROM Ingredientes";
            Statement st = cn.createStatement();
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()) {
                i = new Ingrediente(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getDouble("precio"),
                                rs.getInt("fk_tipo"));
                i.setId(rs.getInt("id"));
                salida.add(i);
            }
        } catch (Exception e){
        }        
        return salida;
    }
    
    public void borrar(int id){
        String sql = "DELETE FROM ingredientes WHERE id = ?;";
        try{
            cn = conexion.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e){
        }
    }
    public IngredienteDAO(){
    }
}
