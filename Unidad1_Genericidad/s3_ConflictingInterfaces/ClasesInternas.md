---
marp: true
title: Clases Internas
description: Slides principales para el tema de Interfaces de la materia Programación Orientada a Objetos, impartida por el Dr. Said Polanco Martagón.
# theme: gaia
paginate: true
_paginate: false
---

---
# Clases Internas

## Dr. Said Polanco Martagón

A primera vista, las clases internas parecen un simple mecanismo de ocultación de código: colocamos las clases dentro de otras clases. sin embargo, como veremos, las clases internas sirven para lmás que eso: la clase interna conoce los detalles de la clase contenedora y puede comunicarse con ella. Asimismo, el tipo de código que puede escribirse con las clases internas es mas elegante.

<!-- footnote: Programación Orientada a Objetos -->

---

## Creación de clases internas

Para crear una clase interna: la definición de las clase se incuye dentro de otr clase contedora:

```Java
public class Parcell {
    class Contents {
        private int i = 11;
        public int value() { return i; }
    }

    class Destination {
        private String label;
        Destination (String whereTo)
        {
            label = whereTo;
        }

        String readLabel() { return label; }
    }

    public void ship(String dest)
    {
        Contents c = new Contents();
        Destination d = new Destination(dest)
        System.out.println(d.readLabel());
    }
    
    public static void main(String[] args)
    {
        Parcell p = new Parcell();
        p.ship("Tasmania");
    }
}
```

---


Lo más normal es que una clase externa tenga un método que devuelva una referencia a una clase interna, como puede verse en los métos `to()` y `contents()`:
```Java
public class Parcel2 {
    class Contents {
        private int i = 11;
        public int value() { return i; }
    }

    class Destination {
        private String label;
        Destination (String whereTo)
        {
            label = whereTo;
        }

        String readLabel() { return label; }
    }

    public Destination to(String s) {
        return new Destination(s);
    }
    public Contents contents() {
        return new Contents();
    }
    
    public void ship(String dest)
    {
        Contents c = new Contents();
        Destination d = new Destination(dest)
        System.out.println(d.readLabel());
    }

    public static void main(String[] args)
    {
        Parcel2 p = new Parcel2();
        p.ship("Tasmania");
        Parcel2 q = new Parcel2();
        // Definición de referencias a clases internas
        Parcel2.Contents c = q.contents();
        Parcel2.Destination = d = q.to("Borneo");
    }
} // fin class Parcel2
```

---
Se queremos construir un objeto de la clase interna en cualquier lugar que nosea dentro de un método no estático de la clase externa, debemos especificar el tipo de objeto como `NombreClaseExterna.ClaseInterna` como se oberva en `main()`.

---

## Enlace con la clase externa

Cuando se crea una clase interna, cada objeto de esa clase interna dispone de un *enlace al objeto contenedor que lo ha creado*, por lo cual puede acceder a los miembros de dicho objeto contenedor sin utilizar ningun cualificador especial. Además, las claves internas tienen derechos de acceso a todos los elementos de la case contenedora.

---

```Java
interface Selector {
    boolean end();
    Object current();
    void next();
}

public class Sequence {
    private Object[] items;
    private int next = 0;
    public Sequence(int size) { items = new Object[size]; }
    public void add(Object x) {
        if (next < items.length) items[next++] = x;
    }

    private class SequenceSelector implements Selector {
        private int = 0;
        public boolean end() { return i == items.lenth; } // acceso a items
        public Object current() { return item[i]; }
        public void next() { if (i < item.length) i++; }
    }

    public Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);

        for (int i=0; i < 10; i++) {
            sequence.add(Integer.toString(i)); 
        }
        Selector selector = sequence.selector();
        while (!selector.end())
        {
            System.out.println(selector.current() + " ");
            selector.next()
        }
    }
}
```

---

## Utilización de `.this` y `.new`

Si se necesita genera una referencia al objeto de la clase externa, basta con indicar `ClaseExterna.this`. La referencia resultante tendrá automáticametne el tipo correcto, que se conoce y se comprueba en tiempo de compilación, por lo que no hay ningún gasto adicional en tiempo de ejecusión.

```Java
public class DotThis {
    void f() { System.out.println("DotThis.f()"); }
    public class Inner {
        public DotThis outer() {
            return DotThis.this; // Un this haría referencia al this de Inner
        }
    }

    public Inner inner() { return new Inner(); }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.outer().f();
    }
}
```

---
Algunas veces, necesitamos que un objeto cree otro objeto de una de sus clases internas. Para hacer esto es necesario proporcionar una referencia al objeto de la clase externa en la expresión `new`, utilizando la sintexis `.new`:

```Java
public class DotNew {
    public class Inner { /* yohabiaponidoaqui.jpg mi clase interna */ }

    public static void main(String[] args) {
        DotNew dn = new DotNew();
        DotNew.Inner dni = dn.new Inner();
    }
}
```
---

Para crear un objeto de la clase interna directamente, no se utiliza esta mismo forma haciendo referencia al nombre de la clase externa **DotNew** como cabría esperar, sino que en su lugar es necesario utilizar un *objeto* de la clase externa para crear un objeto de la clase interna, como podemos ver en el ejemplo anterior.

No es posible crear un objeto de la clase interna a menos que ya se disponga de un objeto de la clase externa. Estos se debe a que el objeto de la clase interna se conecta de manera transparente al de la clase externa que lo haya creado. Sin embargo, si definimos una *clase anidada*, (una clase interna estática), entonces no será necesaria la referencia la objeto de la clase externa.

---

```Java
public class Parcel3 {
    class Contents {
        private int i = 11;
        public int value() { return i; }
    }

    class Destination {
        private String label;
        Destination(String whereTo) { label = whereTo; }
        String readLabel() { return label; }
    }
    public static void main(String[] args) {
        Parcel3 p = new Parcel3();
        // Hay que usar una instancia de la clase externa
        // para crear una instancia de la clase interna
        Parcel3.Contents c = p.new Contents();
        Parcel3.Destination d = p.new Destination("Tasmania");
    }
}
```

---

## Clases internas y generalización

Las clases internas muestran su utilidad real cuando comenzamos a generalizar a una clase base o a una interfaz. La razón es que entonces la clase interna (la implementación de la interfaz) puede ser no visible y estar no disponible, lo cual resulta muy útil para ocultar la implementación. Lo único que se obtiene es una referencia a la clase base o a la interfaz.

```Java
public interface Destination {
    String readLabel();
}
public interface Contents {
    int value();
}
```
---
Cuando obtenemos una referencia a la clase base o a la interfaz, es posible que no podamos averiguar el tipo exacto: 

```Java
class Parcel4 {
    private class PContents implements Contents {
        private int i = 11;
        public int value() { return i; }
    }
    protected class PDestination implements Destination {
        private String label;
        private PDestination(String whereTo) {
            label = whereTo;
        }
        public String readLabel() { return label; }
    }
    public Destination destination(String s) {
        return new PDestination(s);
    }
    public Contents contents() {
        return new PContents();
    }
}

public class TestParcel {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contents();
        Destination d = p.destination("Tasmania");
        // Ilegal -- no se puede acceder a la clase privada
        //! Parcel4.PContents pc = p.new PContents();
    }
}
```
---
En `Parcel4` hemos añadido algo nuevo. La clase interna `PContents`es **private**, así que sólo puede acceder a ella `Parcel4`.

Las clases normales (no internas) no pueden ser privadas o protegidas; sólo pueden tener acceso público o de paquete. `PDestination` es protegida, por lo que sólo pueden acceder a ella `Parcel4`, las clases contenidas en el mismo paquete (ya que **protected** también proporciona acceso de paquete) y las clases que hereden de `Parcel4`. Esto quiere decir que el programador de clientes tiene un conocimiento de estos miembros y un acceso a los mismos restringido. De hecho, no se puede ni siquiera realizar una especialización a una clase interna privada (ni a una clase interna interna protegida, a menos que estemos usando una clase que herede de ella), porque no se puede acceder al nombre, como podemos ver en `class TestParcel`.

---

Por lo tanto, las clases internas privadas proporcionan una forma para que los diseñadores de clase eviten completamente las dependencias de la codificación de tipos y oculten totalmente los detalles relativos a la implementación. Además, la extensión de una interfaz resulta inútil desde la perspectiva del programador de clientes, ya que éste no puede acceder a ningún método adicional que no forme parte de la interfaz pública. Esto también proporciona una oportunidad para que el compilador de Java genere código más eficiente.

---

# Clases internas en los métodos y ámbitos

La sintaxis de las clases internas abarca varias otras técnicas más complejas. Las clases internas pueden crearse de un método o incluso de un ámbito arbitrario. Existen dos razones para hacer ésto:

  1. Se puede implementar una interfaz de algún tipo para poder crear y devolver una referencia.
  1. Pdemos estar tratando de resolver un problema complicado y queremos crear una clase qu enos ayude a encontrar la solución, pero sin que la clase esté publicamente.

---

El siguiente ejemplo muestra la creación de una clase dentro del ámbito de un método (en lugar de dentro del ámbito de otra clase). Esto se denomina *clase interna local*:

```Java
public class Parcel5 {
    public Destination destination(String s) {
        class PDestination implements Destination { // ejemplos anteriores
            private String label;
            private PDestination(String whereTo) {
                label = whereTo;
            }
            public String readLabel() { return label; }
        }
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Tasmania");
    }
}
```
---

La clase `PDestination` es parte de `destination()` en lugar de ser parte de `Parcel5`. Por lo tanto, no se puede acceder a `PDestination` fuera de `destination()`. Observamos la generalización que tiene lugar en la instrucción `return`: lo único que sale de `destination()` es una referencia a `Destination`, que es el tipo base. Por supuesto, el hecho de que el nombre de la clase PDestination se coloque dentro de `destination()` no quiere decir que `PDestination` no se a un objeto válido una vez que `destination()` termina.

De hecho, Podemos utilizar el identificador de clase `PDestination` para nombrar cada clase interna dentro de un mismo subdirectorio sin que se produzcan colisiones de clases.

---

El siguiente ejemplo muestra cómo se puede anidar clases dentro de un ámbito arbitrario:
```Java
public class Parcel6 {
    private void internalTracking(boolean b) {
        if (b) {
            class TrackingSlip {
                private String id;
                TrackingSlip (String s) {
                    id = s;
                }
                String getSlip() { return id; }
            }
            TrackingSlip ts = new TrackingSlip("slip");
            String s = ts.getSlip();
        }
        // No se puede usar aqu. Fuera de ámbito:
        // TrackingSlip ts = new TrackingSlip("X");
    }
    public void track() { internalTracking(true); }
    public static void main(String[] args)
    {
        Parcel6 = new Parcel6();
        p.track();
    }    
}
```

---

## Clases internas anónimas

El siguiente ejemplo puede parecer un tanto extraño:
```Java
public class Parcel7 {
    public Contents contents() {
        return new contents() { // Inserte una definición de clase
            private int i = 11;
            public int value() { return i; }
        }; // En este caso SI hace falta un punto y coma
    }

    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
    }
}
```
---

La sintaxis de la clase interna anónima es una abreviatura de:
```Java
public class Parcel7b {
    class MyContents implements Contents {
        private int i = 11;
        public int value() { return i; }
    }
    public Contents contents() { return new MyContents(); }
    
    // yohabiaponidoaqui.jpg mi main
}
```

---

El siguiente código muestra lo que hacer si la clase base necesita un constructor con un argumento:
```Java
public class Parcel8 {
    public Wrapping wrapping(int x) {
        // Llamada al constructor de la clase base
        return new Wrapping(x) { // Pasar argumento del constructor
            public int value() {
                return super.value() * 47;
            }
        };
    }
    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);
    }
}
```

---
Es decir, simplemente pasamos el argumento apropiado al constructor de la clase base. La clase `Wrapping` esta siendo utilizada también como una "interfaz" con sus clases derivadas:
```Java
public class Wrapping {
    private int i;
    public Wrapping(int x) { i = x; }
    public int value() { return i; }
}
```
---

También se puede realizar la inicialización cuando se definen los campos en una clase anónima:
```Java
public class Parcel9 {
    // El argumento deber ser final para poder utilizarlo
    // dentro de la clase interna anónima.
    public Destination destination(final String dest) {
        return new Destination() {
            private String label = dest;
            public String readLabel() { return label; }
        };
    }
    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("Tasmania");
    }
}
```
---

Se estamos definiendo una clase interna anónima y queremos user un objeto que está definido fuera de la clase interna anónima, el compilador requiere que la ferencia al argumento sea **final**, como puede verse en el argumento de `destination()`, *Si nos olvidamos de hacer ésto, obtendremos un mensaje de error en tiempo de compilación.

¿Qué sucede si necesitamos realizar algún tipo de actividad similar a la de los constructores? No podemos disponder de un constructor nominado dentro de una clase anónima (ya que la clase no tiene ningún nombre), pero con el mecanismo de *inicialización de instancia* podemos crear un constructor para una clase interna anónima.

---

```Java
import static net.mindview.util.Print.*;

abstract class Base {
    public Base(int i) {
        print("Base constructor, i = " + i);
    }
    public abstract void f();
}

public class AnonymousConstructor {
    public static Base getBase(int i) {
        return new Base(i) {
            { print("Inside instance initializar"); }
            public void f() {
                print("In anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }
}
```
---

output:

```
Base constructor, i = 47
Inside instance initializar
In anonymous f()
```

En este caso, la variable `i`no tenía porque haber sido final. Aunque se pasa `i` al constructor base de la clase anónima, nunca se utiliza *dentro* de la clase anónima.

---

Ejemplo de inicialización de instancia:

```Java
public class Parcel10 {
    public Destination destination(final String dest, final float price) {
        return new Destination() {
            private int cost;
            // inicialización de instncia para cada objeto
            {
                cost = Math.round(price);
                if (cost > 100)
                    System.out.println("Over budget!");
            }
            private String label = dest;
            public String readLabel() { return label; }
        };
    }
    
    public static void main(String[] args) {
        Parcel10 p = new Parcel10();
        Destination d = p.destination("Tasmania", 101.395F);
    }
}
```
En la práctica un inicializador de instancia es el constructor de una clase interna anónima.

---

## Restricciones de las clases internas

Las clases internas anónimas están en cierta medida limitada si las comparamos con el mecanismo normal de herencia, por que tienen que extender una clase o implementar una interfaz, pero no pueden hacer ambas cosas al mismo timepo. Y, si implementamos una interfaz, sólo podemos impelentar una.