/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Empleado extends Persona {
     Conexion cn;
    private String codigo;
    private int id,id_puestos;

    public Empleado() {
        
    }

    public Empleado(String codigo, int id, int id_puestos, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo;
        this.id = id;
        this.id_puestos = id_puestos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_puesto() {
        return id_puestos;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puestos = id_puesto;
    }
    
     @Override
public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new Conexion();
        cn.abrir_conexion();
        // Utiliza id_puesto en empleados y id_puestos en puestos
        String query = "SELECT id_empleados as id, codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento, CONCAT(empleados.id_puestos, ') ', puestos.puesto) as puesto " +
                       "FROM empleados INNER JOIN puestos ON empleados.id_puestos = puestos.id_puestos;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        String encabezado[] = {"id", "Codigo", "Nombres", "Apellidos", "Direccion", "Telefono", "Nacimiento", "Puesto"};
        tabla.setColumnIdentifiers(encabezado);
        String datos[] = new String[8];
        while (consulta.next()) {
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("codigo");
            datos[2] = consulta.getString("nombres");
            datos[3] = consulta.getString("apellidos");
            datos[4] = consulta.getString("direccion");
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("fecha_nacimiento");
            datos[7] = consulta.getString("puesto");
     
            tabla.addRow(datos);
        }
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    return tabla;
}


public DefaultComboBoxModel leer_puesto() {
    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    try {
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        query = "SELECT id_puestos as id, puesto FROM puestos";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        combo.addElement("0) Elija Puesto");
        while (consulta.next()) {
            combo.addElement(consulta.getString("id") + ") " + consulta.getString("puesto"));
        }
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    return combo;
}

    public void Agregar(){
      try{
          PreparedStatement parametro;
          cn = new Conexion ();
          cn.abrir_conexion();
          String query;
          query = "insert into empleados (codigo,nombres,apellidos,direccion,telefono,fecha_nacimiento,id_puestos) values (?,?,?,?,?,?,?);";
          parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
          parametro.setString(1, getCodigo());
          parametro.setString(2, getNombres());
          parametro.setString(3, getApellidos());
          parametro.setString(4, getDireccion());
          parametro.setString(5, getTelefono());
          parametro.setString(6, getFecha_nacimiento());
          parametro.setInt(7, getId_puesto());
        
          int executar = parametro.executeUpdate();
          System.out.println(" Se inserto :" + Integer.toString(executar) + " Registro" );
          cn.cerrar_conexion();
      }catch(SQLException ex){
          System.out.println("Error:" + ex.getMessage());
      
      }
  }  
     @Override
    public void actualizar (){
      try{
          PreparedStatement parametro;
          cn = new Conexion ();
          cn.abrir_conexion();
          String query;
          query = "update empleados set codigo=?,nombres=?,apellidos=?,direccion=?,telefono=?,fecha_nacimiento=?,id_puestos=? where id_empleados = ?;";
          parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
          parametro.setString(1, getCodigo());
          parametro.setString(2, getNombres());
          parametro.setString(3, getApellidos());
          parametro.setString(4, getDireccion());
          parametro.setString(5, getTelefono());
          parametro.setString(6, getFecha_nacimiento());
          parametro.setInt(7, getId_puestos());
          parametro.setInt(8, getId());
          int executar = parametro.executeUpdate();
          System.out.println(" Se Actualizo :" + Integer.toString(executar) + " Registro" );
          cn.cerrar_conexion();
      }catch(SQLException ex){
          System.out.println("Error:" + ex.getMessage());
      
      }
  }  
    public void Eliminar (){
      try{
          PreparedStatement parametro;
          cn = new Conexion ();
          cn.abrir_conexion();
          String query;
          query = "delete from empleados where id_empleados = ?;";
          parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
          parametro.setInt(1, getId());
          int executar = parametro.executeUpdate();
          System.out.println(" Se Elimino :" + Integer.toString(executar) + " Registro" );
          cn.cerrar_conexion();
      }catch(SQLException ex){
          System.out.println("Error:" + ex.getMessage());
      
      }
  }  

    private int getId_puestos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void Eliminar(int id_empleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void eliminar(int idEmpleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
