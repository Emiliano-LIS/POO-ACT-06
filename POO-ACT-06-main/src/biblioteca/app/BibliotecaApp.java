package biblioteca.app;

import biblioteca.modelo.Biblioteca;
import biblioteca.modelo.Ejemplar;
import biblioteca.modelo.EstadoEjemplar;
import biblioteca.modelo.Libro;
import biblioteca.modelo.Usuario;
import biblioteca.servicio.Prestamo;

import java.time.LocalDate;

public class BibliotecaApp {
    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca("USBI Coatzacoalcos");
        Usuario usuario1 = new Usuario("Andy", "zS25018136");
        Usuario usuario2 = new Usuario("Emiliano", "zS25018133");
        biblioteca.agregarUsuario(usuario1);
        biblioteca.agregarUsuario(usuario2);

        Libro libro = new Libro("Clean Code", "Robert C. Martin");
        Ejemplar ejemplar = new Ejemplar("EJ-001", EstadoEjemplar.disponible,libro);

        System.out.println("Libro: " + libro.getTitulo() + " Autor: " + libro.getAutor());
        System.out.println("Estado inicial: " + ejemplar.getEstado());

        Prestamo prestamo = new Prestamo(LocalDate.now(), LocalDate.now().plusDays(7));
        System.out.println(prestamo.realizarPrestamo(usuario1, ejemplar));
        System.out.println("Estado: " + ejemplar.getEstado());

        System.out.println(prestamo.realizarPrestamo(usuario2, ejemplar));

        System.out.println("Vencido: " + prestamo.estaVencido());

        prestamo.devolver(ejemplar);
        System.out.println("Estado final: " + ejemplar.getEstado());
    }
}