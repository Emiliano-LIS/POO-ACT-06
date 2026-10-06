# ☕ Java: Paquetes, Imports y Documentación

> **Tema:** Organización del código en Java y comentarios
> **Idea central:** a medida que un proyecto crece, el problema deja de ser *escribir* código y pasa a ser *organizarlo y comunicarlo*. Todo lo de este documento resuelve ese problema.

---

## 📑 Contenido

1. [¿Para qué sirve `package`?](#1--para-qué-sirve-package)
2. [¿Para qué sirve `import`?](#2--para-qué-sirve-import)
3. [Relación entre paquete y directorio](#3--relación-entre-paquete-y-directorio)
4. [¿Qué es Javadoc?](#4--qué-es-javadoc)
5. [Diferencia entre `//` y `/** ... */`](#5--diferencia-entre--y---)
6. [Resumen rápido](#-resumen-rápido)
7. [Mini-reto para practicar](#-mini-reto-para-practicar)

---

## 1. 📦 ¿Para qué sirve `package`?

### Intuición

Imagina una biblioteca con 50,000 libros sin secciones: encontrar uno sería imposible y habría libros con el mismo título mezclados. Un **paquete** es una *sección* de esa biblioteca: agrupa clases relacionadas y les da un **espacio de nombres** propio.

### Detalle técnico

La instrucción `package` **declara a qué paquete pertenece la clase** del archivo. Sirve para tres cosas:

| Propósito | Explicación |
|---|---|
| **Organizar** | Agrupa clases por responsabilidad (`modelo`, `servicio`, `controlador`…). |
| **Evitar colisiones de nombres** | Dos clases pueden llamarse `Usuario` si viven en paquetes distintos. Su nombre completo (*fully qualified name*) es diferente. |
| **Controlar la visibilidad** | Si no pones modificador de acceso, el miembro es *package-private*: solo visible dentro del mismo paquete. |

### Ejemplo

```java
package mx.uv.tienda.modelo;   // 👈 siempre la PRIMERA instrucción del archivo

public class Producto {
    private String nombre;
    private double precio;
    // ...
}
```

El nombre completo de la clase es `mx.uv.tienda.modelo.Producto`.

### Reglas importantes

- Va **antes** de cualquier `import` y de la declaración de la clase.
- Solo puede haber **una** declaración `package` por archivo.
- Si no la escribes, la clase queda en el **paquete sin nombre** (*default package*). Sirve para pruebas rápidas, pero es una **mala práctica** en proyectos reales: no puedes importar clases del paquete por defecto desde otros paquetes.
- **Convención de nombres:** dominio invertido en minúsculas. Si tu proyecto fuera de `uv.mx`, sería `mx.uv.nombreproyecto...`. Así se garantiza que el nombre sea globalmente único.

> 💡 **Conexión con ingeniería de software:** los paquetes son tu primera herramienta de **modularidad** y **bajo acoplamiento**. Cuando más adelante veas arquitectura por capas o diseño de módulos, estarás aplicando esta misma idea a mayor escala.

---

## 2. 📥 ¿Para qué sirve `import`?

### Intuición

Si trabajas en la Universidad Veracruzana y quieres hablar con alguien de la Facultad de Informática, puedes decir "Juan" o, para ser exacto, "Juan de Informática, de la UV". Con `import` le dices al compilador: *"cuando diga `Scanner`, me refiero a **este** Scanner"*. Es un **atajo de escritura**.

### Detalle técnico

`import` permite usar una clase de **otro paquete** con su nombre simple, en lugar de su nombre completo.

**Sin import:**

```java
java.util.List<String> nombres = new java.util.ArrayList<>();
```

**Con import:**

```java
import java.util.List;
import java.util.ArrayList;

List<String> nombres = new ArrayList<>();
```

### Lo que `import` NO hace (error conceptual común)

❌ **No copia ni carga código.** A diferencia de `#include` en C/C++, `import` no inserta el contenido de nada. Es solo información para el compilador sobre cómo **resolver nombres**. Por eso no afecta el rendimiento ni el tamaño del programa.

### Variantes

```java
import java.util.List;           // una clase concreta (recomendado)
import java.util.*;              // todas las clases de ESE paquete
import static java.lang.Math.PI; // un miembro estático, sin escribir "Math."
```

### Reglas y detalles que sí caen en exámenes y entrevistas

- `java.lang` (`String`, `System`, `Math`, `Integer`…) se importa **automáticamente**.
- No necesitas importar clases del **mismo paquete**.
- `import java.util.*;` **no incluye subpaquetes**. `java.util.concurrent.*` es otra importación distinta.
- Si dos clases tienen el mismo nombre (por ejemplo `java.util.Date` y `java.sql.Date`), no puedes importar ambas. Usa el nombre completo para una de ellas:

```java
import java.util.Date;

Date hoy = new Date();                  // java.util.Date
java.sql.Date fechaSql = new java.sql.Date(0); // nombre completo
```

> 🧠 **Sobre `import ...*`:** funciona igual en tiempo de ejecución, pero muchos equipos lo evitan porque oculta *de dónde viene* cada clase y puede causar ambigüedades. Los IDE como IntelliJ o Eclipse administran los imports por ti.

---

## 3. 🗂️ Relación entre paquete y directorio

### Intuición

El nombre del paquete es como una **dirección postal**; el directorio es **dónde vive físicamente** la casa. Java exige que ambos coincidan para poder encontrarla.

### La regla

Cada punto del nombre del paquete corresponde a un **subdirectorio**:

```
paquete:   mx.uv.tienda.modelo
directorio: mx/uv/tienda/modelo/
```

Estructura de proyecto típica:

```
proyecto/
└── src/
    └── mx/
        └── uv/
            └── tienda/
                ├── Main.java            → package mx.uv.tienda;
                └── modelo/
                    └── Producto.java    → package mx.uv.tienda.modelo;
```

### ¿Por qué importa tanto?

La JVM y el compilador **buscan las clases siguiendo esa ruta** a partir del *classpath*. Si la estructura no coincide, aparecen errores como `ClassNotFoundException` o `NoClassDefFoundError`.

### Compilar y ejecutar (desde la raíz del proyecto)

```bash
# Compila y coloca los .class respetando la estructura de paquetes
javac -d out src/mx/uv/tienda/Main.java src/mx/uv/tienda/modelo/Producto.java

# Ejecuta usando el NOMBRE COMPLETO de la clase (no la ruta del archivo)
java -cp out mx.uv.tienda.Main
```

> ⚠️ **Error clásico:** entrar a la carpeta `tienda/` y ejecutar `java Main`. Falla, porque la clase se llama realmente `mx.uv.tienda.Main`, no `Main`.

### Matices que conviene tener claros

- La jerarquía de **directorios** es real, pero la jerarquía de **paquetes es solo nominal**: `mx.uv.tienda` y `mx.uv.tienda.modelo` **no tienen una relación especial de acceso**. Que uno "contenga" al otro en el disco no le da privilegios sobre el otro.
- Los archivos compilados (`.class`) también siguen esta estructura, y los `.jar` son básicamente carpetas comprimidas con ella.
- En proyectos reales, herramientas como **Maven** o **Gradle** fijan esta estructura por ti (`src/main/java/...`).

---

## 4. 📚 ¿Qué es Javadoc?

### Intuición

Piensa en el manual de una librería: no necesitas leer su código fuente para saber qué hace `ArrayList.add()`. Esa documentación en la web **se generó automáticamente a partir de comentarios** escritos en el código. Esa herramienta es **Javadoc**.

### Definición

**Javadoc** es la herramienta incluida en el JDK que lee comentarios especiales (`/** ... */`) de tu código y genera **documentación HTML** de tu API (clases, métodos, parámetros, excepciones…).

### Ejemplo

```java
/**
 * Calcula el precio final de un producto aplicando un descuento.
 *
 * @param precio    precio original; debe ser mayor o igual a 0
 * @param descuento porcentaje de descuento entre 0 y 100
 * @return el precio con el descuento aplicado
 * @throws IllegalArgumentException si el descuento está fuera de rango
 */
public double aplicarDescuento(double precio, int descuento) {
    if (descuento < 0 || descuento > 100) {
        throw new IllegalArgumentException("Descuento inválido: " + descuento);
    }
    return precio * (1 - descuento / 100.0);
}
```

### Etiquetas más usadas

| Etiqueta | Para qué sirve |
|---|---|
| `@param` | Describe un parámetro. |
| `@return` | Describe el valor que devuelve. |
| `@throws` | Excepciones que el método puede lanzar y cuándo. |
| `@author` | Autor de la clase. |
| `@since` | Versión en que apareció. |
| `@see` | Referencia a algo relacionado. |
| `{@code ...}` | Muestra texto como código dentro de la descripción. |

### Generar la documentación

```bash
javadoc -d docs -sourcepath src -subpackages mx.uv.tienda
```

Esto crea una carpeta `docs/` con un sitio HTML navegable (abre `index.html`).

### ¿Por qué importa en la industria?

- Los **IDE** muestran ese texto al pasar el cursor sobre un método.
- Documenta el **contrato** de tu código: qué espera, qué devuelve, cuándo falla. Eso es lo que otros desarrolladores (y tu yo del futuro) necesitan.
- Es la base de toda la documentación oficial de Java.

> 🎯 **Criterio de ingeniería:** documenta con Javadoc la **API pública** (lo que otros van a usar). No hace falta documentar cada detalle interno privado.

---

## 5. 💬 Diferencia entre `//` y `/** ... */`

Java tiene **tres** tipos de comentarios. Esta es la comparación completa:

| Tipo | Sintaxis | Alcance | ¿Lo usa Javadoc? |
|---|---|---|---|
| **De línea** | `// texto` | Hasta el final de la línea | ❌ No |
| **De bloque** | `/* texto */` | Varias líneas | ❌ No |
| **De documentación** | `/** texto */` | Varias líneas | ✅ **Sí** |

```java
// Comentario de una línea: explica algo puntual del código.
int intentos = 3;

/* Comentario de bloque:
   útil para desactivar temporalmente varias líneas
   o dar una explicación larga. */

/**
 * Comentario de documentación: describe el CONTRATO de la clase o método.
 * Javadoc lo convertirá en HTML.
 */
public class Ejemplo { }
```

### La diferencia de fondo

- Para el **compilador**, los tres son idénticos: los **ignora por completo**.
- La diferencia está en el **propósito y en quién los lee**:
  - `//` y `/* */` → son para **quien lee el código** (el *cómo* y el *porqué* de una decisión interna).
  - `/** */` → es para **quien usa tu código sin leerlo** (el *qué* hace y cómo usarlo).

### Detalles a tener en cuenta

- Un `/** */` solo se procesa como documentación si está **justo antes** de una declaración (clase, método, atributo…). Si lo pones suelto dentro de un método, Javadoc lo ignora.
- Los comentarios de bloque `/* */` **no se pueden anidar**: el primer `*/` cierra el comentario.
- **Buena práctica:** un comentario debe explicar el **porqué**, no repetir el **qué**.

```java
// ❌ Inútil: repite lo que ya dice el código
i++; // incrementa i

// ✅ Útil: explica una decisión que no es obvia
// Se reintenta 3 veces porque el servidor de pagos falla de forma intermitente
int intentos = 3;
```

---

## ✅ Resumen rápido

| Concepto | En una frase |
|---|---|
| `package` | Declara a qué espacio de nombres pertenece la clase. |
| `import` | Atajo para usar clases de otros paquetes sin su nombre completo (no copia código). |
| Paquete ↔ directorio | Cada punto del nombre del paquete es un subdirectorio. |
| Javadoc | Herramienta que genera documentación HTML desde comentarios `/** */`. |
| `//` vs `/** */` | El primero es una nota para quien lee el código; el segundo documenta la API para quien la usa. |

---

## 🧪 Mini-reto para practicar

Intenta resolverlo **antes** de buscar la respuesta. Si te atoras, usa las pistas.

**Situación:** tienes la clase `Alumno` y quieres que esté en el paquete `mx.uv.escuela.modelo`, y una clase `Main` en `mx.uv.escuela`.

1. ¿En qué rutas de carpetas deben estar los dos archivos `.java`?
2. ¿Qué líneas `package` e `import` necesita `Main` para poder usar `Alumno`?
3. ¿Qué comando usarías para ejecutarlo desde la raíz del proyecto?
4. Escribe el Javadoc de un método `double calcularPromedio(int[] calificaciones)`, incluyendo qué pasa si el arreglo está vacío.

<details>
<summary>💡 Pista 1</summary>

Convierte cada punto del paquete en una carpeta.

</details>

<details>
<summary>💡 Pista 3</summary>

Para ejecutar se usa el nombre completo de la clase con `main`, no la ruta del archivo.

</details>

<details>
<summary>💡 Pista 4</summary>

Piensa en `@param`, `@return` y `@throws`. ¿Qué debería hacer el método con un arreglo vacío? Esa es una decisión de diseño que debes documentar.

</details>

---

*Cuando tengas tu solución, mándamela y te la reviso: no solo si funciona, sino cómo la pensaste.* 🚀
