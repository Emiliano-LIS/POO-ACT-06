package biblioteca.servicio;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import biblioteca.modelo.EstadoEjemplar;
import biblioteca.modelo.Usuario;
import biblioteca.modelo.Ejemplar;

public class Prestamo {
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion){
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public String realizarPrestamo(Usuario usuario, Ejemplar ejemplar) {
        if (ejemplar.getEstado() == EstadoEjemplar.disponible) {
            ejemplar.setEstado(EstadoEjemplar.prestado);
            return ("El libro esta siendo prestado a " + usuario.getNombre());
        } else {
            return ("El titulo " ejemplar.getTitulo()
        }
    }

    public void devolver(Ejemplar ejemplar){
            System.out.println("¿El libro está dañado? Y/N");
            Scanner scanner = new Scanner (System.in);
            char c = scanner.findInLine(".").charAt(0);
            if (c == 'Y') {
                ejemplar.setEstado(EstadoEjemplar.daniado);
            }
            if( ejemplar.getEstado() == EstadoEjemplar.prestado){

                ejemplar.setEstado(EstadoEjemplar.disponible);
            }
    }

    public boolean estaVencido(){
      if (fechaPrestamo.isAfter(LocalDate.now()) && fechaDevolucion.isBefore(LocalDate.now())){
          return true;
      }

      return false;
      }

}

