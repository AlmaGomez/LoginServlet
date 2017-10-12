/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author almaangelica
 */
public class ConexionBD {
        Connection con=null;
        Statement sta=null;
        ResultSet reg=null;
    
    public void conectarBase() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/Usuarios", "root", "n0m3l0");
                sta = con.createStatement();
    }
    
    public String agregarUsuario(String nom, String pas){    
        String msg="";
        try{
            sta.executeUpdate("Insert into usuario (Nombre, Pass) values('"+nom+"','"+pas+"');");
            msg="Operacion Exitosa";
        }
        catch(SQLException error){
            msg=(error.toString());
        }
        return msg;
    }
    public int buscar (String nom, String pas) throws SQLException{
        int registros=0;
            reg=sta.executeQuery("select * from usuario;");
            while(reg.next())
            {
                if(nom.equals(reg.getString("Nombre")) && pas.equals(reg.getString("Pass")))
                {
                    registros=1;
                }
            } 
        return registros;
    }
}

