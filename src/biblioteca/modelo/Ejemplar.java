package biblioteca.modelo;

public class Ejemplar {
    private String codigo;
    private EstadoEjemplar estado;
    private Libro libro;

    public Ejemplar(String titulo, String autor, String codigo, EstadoEjemplar estado, Libro libro){
        super(titulo, autor);
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
