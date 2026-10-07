package biblioteca.modelo;
import java.util.ArrayList;

public class Biblioteca {
    private String nombre;
    private ArrayList<Usuario> usuarios;

    public Biblioteca(String nombre){
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
}
