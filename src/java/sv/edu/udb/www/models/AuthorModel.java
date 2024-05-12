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

public class AuthorModel extends Conexion {
    public AuthorModel(){
        super();
    }

    public List<AuthorPojo> getAuthors() throws SQLException{

        this.connect();

        PreparedStatement ps = con.prepareStatement("select a.idAuthor as idAuthor, a.nombre, a.fechaNacimiento, a.telefono,l.nombre_genero as genero "
        + "from author a "
        + "join literarygenre l "
        + "on l.id_genero = a.generoLiterario ");
          
        ResultSet result = ps.executeQuery();
        List<AuthorPojo> list = new ArrayList<AuthorPojo>();

        while(result.next()){
            AuthorPojo author = new AuthorPojo();
            author.setIdAutor(result.getInt("idAuthor"));
            author.setNombre(result.getString("nombre"));
            author.setFechaNacimiento(result.getDate("fechaNacimiento"));
            author.setTelefono(result.getString("telefono"));
            author.setGeneroLiterario(result.getString("genero"));
            list.add(author);
        }

        this.close();
        return list;

    }
    
    
    public List<AuthorPojo> getAuthorsByGenre(int id) throws SQLException{

        this.connect();

        PreparedStatement ps = con.prepareStatement("select a.idAuthor as idAuthor, a.nombre, a.fechaNacimiento, a.telefono,l.nombre_genero as genero "
        + "from author a "
        + "join literarygenre l "
        + "on l.id_genero = a.generoLiterario where l.id_genero = ? ");
          
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        List<AuthorPojo> list = new ArrayList<AuthorPojo>();

        while(result.next()){
            AuthorPojo author = new AuthorPojo();
            author.setIdAutor(result.getInt("idAuthor"));
            author.setNombre(result.getString("nombre"));
            author.setFechaNacimiento(result.getDate("fechaNacimiento"));
            author.setTelefono(result.getString("telefono"));
            author.setGeneroLiterario(result.getString("genero"));
            list.add(author);
        }

        this.close();
        return list;

    }

    public int findSameNameAuhor(String name) throws SQLException{

        this.connect();

        PreparedStatement ps = con.prepareStatement("select count(*) as total "
                                + "from author a "
                                + " where nombre like ? ");

        ps.setString(1, name);
        ResultSet result = ps.executeQuery();
        int count=0;

        while(result.next()){
            count= result.getInt("total");
        }

        this.close();
        return count;
    }

    public void addAuthor(AuthorPojo author) throws SQLException{

        this.connect();
        if(dataSource == null)
            throw new SQLException("Can't get data source");

        if(con == null)
            throw new SQLException("Can't get database connection");

        PreparedStatement ps = con.prepareStatement("insert into author (nombre,fechaNacimiento,telefono,generoLiterario) values (?,?,?,?)");
        ps.setString(1, author.getNombre());
        ps.setDate(2, new java.sql.Date(author.getFechaNacimiento().getTime()));
        ps.setString(3, author.getTelefono());
        ps.setString(4, author.getGeneroLiterario());
        ps.execute();
        this.close();

    }

    public void delete(AuthorPojo author) throws SQLException{
        this.connect();
        
        if(dataSource == null)
            throw new SQLException("Can't get data source");

        if(con == null)
            throw new SQLException("Can't get database connection");

        PreparedStatement ps = con.prepareStatement("delete from author where idAuthor = ?");

        ps.setInt(1, author.getIdAutor());
        ps.execute();
        this.close();
    }
    
    public AuthorPojo findAuthor(int id) throws SQLException{
        
        this.connect();
        AuthorPojo author = new AuthorPojo();

        PreparedStatement ps = con.prepareStatement("select a.idAuthor as idAuthor, a.nombre, a.fechaNacimiento, a.telefono,l.nombre_genero as genero "
        + "from author a "
        + "join literarygenre l "
        + "on l.id_genero = a.generoLiterario "
        + "where idAuthor = ?");
        
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        List<AuthorPojo> list = new ArrayList<AuthorPojo>();

        while(result.next()){            
            author.setIdAutor(result.getInt("idAuthor"));
            author.setNombre(result.getString("nombre"));
            author.setFechaNacimiento(result.getDate("fechaNacimiento"));
            author.setTelefono(result.getString("telefono"));
            author.setGeneroLiterario(result.getString("genero"));
            list.add(author);
        }
        this.close();
        return author;
    }
    
    public boolean update(AuthorPojo author) throws SQLException{
        this.connect();
        if(dataSource == null)
            throw new SQLException("Can't get data source");

        if(con == null)
            throw new SQLException("Can't get database connection");

        PreparedStatement ps = con.prepareStatement("UPDATE author SET nombre = ?, fechaNacimiento = ?, telefono = ?, generoLiterario = ? WHERE idAuthor = ?");
        ps.setString(1, author.getNombre());
        ps.setDate(2, new java.sql.Date(author.getFechaNacimiento().getTime()));
        ps.setString(3, author.getTelefono());
        ps.setString(4, author.getGeneroLiterario());
        ps.setInt(5, author.getIdAutor());
        boolean result = ps.execute();
        this.close();
        return result;
    }
}
