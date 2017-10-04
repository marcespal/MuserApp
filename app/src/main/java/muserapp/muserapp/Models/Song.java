package muserapp.muserapp.Models;

/**
 * Created by oculu on 04/06/2017.
 */

public class Song {
    private String nombre;
    private String autor;
    private String disco;
    private String imagen;
    private String link;
    private String descripcion;


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDisco() {
        return disco;
    }
    public void setDisco(String disco) { this.disco = disco; }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) { this.imagen = imagen;}

    public String getLink() {
        return link;
    }
    public void setLink(String link) { this.link = link;}

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

}
