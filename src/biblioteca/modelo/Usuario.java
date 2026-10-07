package biblioteca.modelo;

public class Usuario {
    private String nombre;
    private String numeroIndentificacion;

    public Usuario(String nombre, String numeroIndentificacion){
        this.nombre = nombre;
        this.numeroIndentificacion = numeroIndentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroIndentificacion() {
        return numeroIndentificacion;
    }
}
