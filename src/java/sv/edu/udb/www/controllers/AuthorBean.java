/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.controllers;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sv.edu.udb.www.models.AuthorModel;
import sv.edu.udb.www.models.AuthorPojo;
import sv.edu.udb.www.models.LiteraryGenre;


@ManagedBean
@SessionScoped
public class AuthorBean implements Serializable {
    
    private AuthorPojo author;
    
    private int genero;
    
    private int count;
    
    private AuthorModel authorModel = new AuthorModel();

    private LiteraryGenre literaryGenreModel = new LiteraryGenre();
    
    private List<AuthorPojo> listaAutores = new ArrayList<AuthorPojo>();

    /**
     * Creates a new instance of authorBean
     */
    public AuthorBean() throws SQLException {
        this.author = new AuthorPojo();
        genero = 0;
        listAutores();
    }
    
    public void addAuthor() throws SQLException{
            authorModel.addAuthor(author);
            FacesContext.getCurrentInstance().addMessage("successMessage", new
            FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Exitosamente", "Agregado"));
            author = new AuthorPojo();
            listAutores();           
            
    }
    
    public void updateAuthor() throws SQLException{
   
        authorModel.update(author);
        FacesContext.getCurrentInstance().addMessage("successMessage", new
        FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Exitosamente", "Modificación"));    
        author = new AuthorPojo();
        listAutores();
        
    }
    
    public void deleteAuthor(AuthorPojo author) throws SQLException{
        authorModel.delete(author);
        listAutores();
    }
    
    public void countAuthor(String name) throws SQLException{
        if(authorModel.findSameNameAuhor(name)>0){
            FacesContext.getCurrentInstance().addMessage("errorMessage", new
            FacesMessage(FacesMessage.SEVERITY_INFO, "Este autor ya existe", "Author"));
        }
    }
    
    public void countAuthors() throws SQLException{
        this.count = listaAutores.size();
    }
    
    public void listAutores() throws SQLException{
        
        listaAutores = new ArrayList<>();
        
        if(genero == 0){
            listaAutores.addAll(authorModel.getAuthors());
        }else{
            listaAutores.addAll(authorModel.getAuthorsByGenre(genero));
        }
        
        //countAuthors();
    }
    
    public void findAuthorByID(int id) throws SQLException{
        this.author = authorModel.findAuthor(id);
        System.out.println(author.getIdAutor());
        System.out.println(author.getNombre());    
    }
    
    public void clearForm() throws SQLException{
        author =new AuthorPojo();
    }

    public AuthorPojo getAuthor() {
        return author;
    }

    public void setAuthor(AuthorPojo author) {
        this.author = author;
    }

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }

    public LiteraryGenre getLiteraryGenreModel() {
        return literaryGenreModel;
    }

    public void setLiteraryGenreModel(LiteraryGenre literaryGenreModel) {
        this.literaryGenreModel = literaryGenreModel;
    }    

    public List<AuthorPojo> getListaAutores() {
        return listaAutores;
    }

    public void setListaAutores(List<AuthorPojo> listaAutores) {
        this.listaAutores = listaAutores;
    }

    public int  getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
