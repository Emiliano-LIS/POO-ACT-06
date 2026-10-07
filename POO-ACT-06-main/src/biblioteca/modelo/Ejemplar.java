package biblioteca.modelo;

public class Ejemplar {
    private String codigo;
    private EstadoEjemplar estado;
    private Libro libro;

    public Ejemplar(String codigo, EstadoEjemplar estado, Libro libro){
        this.codigo = codigo;
        this.estado = estado;
        this.libro = libro;
    }

    public String getCodigo() {
        return codigo;
    }

    public EstadoEjemplar getEstado() {
        return estado;
    }

    public Libro getLibro(){
        return libro;
    }

    public void setEstado(EstadoEjemplar estado) {
        this.estado = estado;
    }
}
