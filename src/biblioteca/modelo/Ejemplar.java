package biblioteca.modelo;

public class Ejemplar {
    private String codigo;
    private EstadoEjemplar estado;

    public Ejemplar(String codigo, EstadoEjemplar estado){

    }

    public String getCodigo() {
        return codigo;
    }

    public EstadoEjemplar getEstado() {
        return estado;
    }

    public void setEstado(EstadoEjemplar estado) {
        this.estado = estado;
    }
}
