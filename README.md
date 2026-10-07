# Actividad 06

Descripción:
Aplicando la programción en pares (pair programing) construyan el "Sistema de Biblioteca" que se describe en diagrama UML. También, respondan las siguientes pregunta

Realizado por:
Jorge Emiliano Hernandez Martinez
Andy Corro Quezada

Desglose de preguntas:
1. ¿Para qué sirve package?
2. ¿Para qué sirve import?
3. ¿Qué relación existe entre paquete y directorio?
4. ¿Qué es Javadoc?
5. ¿Qué diferencia hay entre // y /** ... */?

## 1. ¿Para qué sirve `package`?

`package` indica a qué grupo pertenece una clase. Ayuda a ordenar el proyecto y evita conflictos entre clases que tienen el mismo nombre. Lo podemos entender como una estructura de carpetas.
```java
package biblioteca.modelo;

public class Libro {
    // Contenido de la clase
}
```

La declaración debe aparecer al inicio del archivo, antes de los `import`. Además, el nombre del paquete debe coincidir con la ubicación de la clase:

```text
src/biblioteca/modelo/Libro.java
                 └── package biblioteca.modelo;
```

## 2. ¿Para qué sirve `import`?

`import` permite usar una clase de otro paquete sin escribir su nombre completo cada vez.
Sin un import puede que no "alcancemos" una clase para usarla o instanciarla, por lo que import nos permite traerla también
```java
import biblioteca.modelo.Libro;

Libro libro = new Libro("El principito", "Antoine de Saint-Exupéry");
```

Sin el `import`, habría que escribir:

```java
biblioteca.modelo.Libro libro =
        new biblioteca.modelo.Libro("El principito", "Antoine de Saint-Exupéry");
```

Un `import` no copia ni ejecuta código; solo ayuda al compilador a identificar la clase.
## 3. ¿Qué relación hay entre el paquete y el directorio?

Cada parte del nombre del paquete representa una carpeta. Por ejemplo:

```text
biblioteca.modelo  →  biblioteca/modelo/
biblioteca.servicio  →  biblioteca/servicio/
biblioteca.app  →  biblioteca/app/
```

En este proyecto la estructura principal es:

```text
src/
└── biblioteca/
    ├── app/
    │   └── BibliotecaApp.java
    ├── modelo/
    │   ├── Biblioteca.java
    │   ├── Ejemplar.java
    │   ├── Libro.java
    │   └── Usuario.java
    └── servicio/
        └── Prestamo.java
```

Si el paquete y la ruta no coinciden, Java puede tener problemas para encontrar la clase.

## 4. ¿Qué es Javadoc?

Javadoc permite documentar clases y métodos públicos mediante comentarios que empiezan con /**.

```java
/**
 * Agrega un usuario a la biblioteca.
 *
 * @param usuario usuario que se desea registrar
 */
public void agregarUsuario(Usuario usuario) {
    usuarios.add(usuario);
}
```

Las etiquetas más comunes son:

- `@param`: describe un parámetro.
- `@return`: explica el valor retonado de un método.
- `@author`: indica el autor de un archivo

## 5. ¿Qué diferencia hay entre `//` y `/** ... */`?

`//` se usa para comentarios breves en código.

```java
// Cada ejemplar representa una copia física de un libro.
Ejemplar ejemplar = new Ejemplar("E001", EstadoEjemplar.disponible, libro);
```

`/** ... */` se coloca antes de una clase o método para comentarios extensivos:

```java
/**
 * Inicia una demostración sencilla del sistema de biblioteca.
 */
public class BibliotecaApp {
}
```

Los comentarios deben aportar contexto. No es necesario explicar instrucciones que ya son evidentes al leer el código.
