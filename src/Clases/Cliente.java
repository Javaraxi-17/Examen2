/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author HP
 */

public class Cliente extends Persona {
    private String carnet;
    private int id;
    Conexion cn;
    
    
public Cliente() {}

    public Cliente(int id,String carnet,String nombres, String direccion, String apellidos, String telefono,String email, String genero, String fn) {
        super(carnet,nombres, direccion, apellidos, telefono, email, genero, fn);
        this.carnet = carnet;
        this.id = id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public DefaultTableModel leer (){
    DefaultTableModel tabla = new DefaultTableModel ();
    try {
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        query = "Select id_estudiantes as carnet,nombres,apellidos,direccion,telefono,email,genero,fecha_nacimiento from clientes";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        
        String encabezado []={"carnet","nombres","apellidos","direccion","telefono","email","genero","fecha_nacimiento"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos[]= new String[9];
        while(consulta.next()){
            datos[0]= consulta.getString("Carnet");
            datos[1]= consulta.getString("nombres");
            datos[2]= consulta.getString("apellidos");
            datos[3]= consulta.getString("direccion");
            datos[4]= consulta.getString("telefono");
            datos[5]= consulta.getString("email");
            datos[6]= consulta.getString("genero");
            datos[7]= consulta.getString("fecha_nacimiento");
            tabla.addRow(datos);
            
        }
        cn.cerrar_conexion();
        
    }catch (SQLException ex){
        cn.cerrar_conexion();
        System.out.println("Error..."+ex.getMessage());
    }
    return tabla;
    }

    @Override
    public void agregar(){
        try {
            
        PreparedStatement parametro;
        String query = "INSERT INTO db_empresa.estudiantes(nit,nombres,apellidos,direccion,telefono,email,genero,fecha_nacimiento) VALUES(?,?,?,?,?,?,?,?);";
        cn = new Conexion ();
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getCarnet());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getEmail());
        parametro.setString(7, getGenero());
        parametro.setString(8, getFn());
        int executar = parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar)+ "Registro ingresado", "agregar",JOptionPane.INFORMATION_MESSAGE );
        }
        
        catch(HeadlessException | SQLException ex){
        System.out.println("Error..."+ex.getMessage());
        }
    }
    @Override
    public void actualizar(){
     try {
            
        PreparedStatement parametro;
        String query = "update db_empresa.estudiantes set carnet = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?,email = ?,genero = ?, fecha_nacimiento = ? "+"where id_estudiantes = ?";
        cn = new Conexion ();
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getCarnet());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getEmail());
        parametro.setString(7, getGenero());
        parametro.setString(8, getFn());
        parametro.setInt(9, getId());
        int executar = parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar)+ "Registro ingresado", "agregar",JOptionPane.INFORMATION_MESSAGE );
        }
        
        catch(HeadlessException | SQLException ex){
        System.out.println("Error..."+ex.getMessage());
        }
    }
    @Override
    public void eliminar(){
    try {
            
        PreparedStatement parametro;
        String query = "delete db_empresa.estudiantes where id_estudiantes = ?";
        cn = new Conexion ();
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, getId());
        int executar = parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar)+ "Registro eliminado", "agregar",JOptionPane.INFORMATION_MESSAGE );
        }
        
        catch(HeadlessException | SQLException ex){
        System.out.println("Error..."+ex.getMessage());
        }
    }

}
