/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LiteraryGenre extends Conexion {
    
    public LiteraryGenre() {
        super();
    }
    
    public List<GeneroLiterarioPojo> getLiteraryGenre() throws SQLException{

        this.connect();

        PreparedStatement ps = con.prepareStatement("select id_genero, nombre_genero, descripcion from literarygenre ");;

        ResultSet result = ps.executeQuery();

        List<GeneroLiterarioPojo> list = new ArrayList<GeneroLiterarioPojo>();

        while(result.next()){
        GeneroLiterarioPojo literaryGenrePjo = new GeneroLiterarioPojo();
        literaryGenrePjo.setId(result.getInt("id_genero"));
        literaryGenrePjo.setName(result.getString("nombre_genero"));
        literaryGenrePjo.setDescripcion(result.getString("descripcion"));
        list.add(literaryGenrePjo);
        }

        this.close();
        return list;

    }
}
