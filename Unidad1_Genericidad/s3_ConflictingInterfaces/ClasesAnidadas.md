---
marp: true
title: Clases Anidadas
description: Slides principales para el tema de Interfaces de la materia Programación Orientada a Objetos, impartida por el Dr. Said Polanco Martagón.
# theme: gaia
paginate: true
_paginate: false
---

# Programación Orientada a Objetos
## Clases Anidadas
### Dr. Said P. Martagón
---

Si no es necesario disponer de una conexión entre el objeto de la clas interna y el objeto de la clase externa, podemos definir la clase interna como estática. Esto es lo que comúnmente se denomina una **clase anidada**.

Una clase anidada significa:

  1. Que no es necesario un objeto de la clase externa para crear un objeto de la clase anidada.
  1. Que no se puede acceder a un objeto no estático de la clase externa desde un objeto de una clase anidada.

---

## Otras consideraciones

Los campos y los métodos en las clases internas normales sólo pueden encontrarse en el nivel externo de una clase, por lo que las clases internas normales no pueden tener datos estáticos, compos estáticos o clases anidadas. Sin embargo, las clases anidadas pueden tener cualquiera de estos elementos.

---

```Java
public class Parcel11 {
    private static class ParcelContents implements Contents {
        private int i=11;
        public int value() { return i; }
    }

    protected static class ParcelDestination implements Destination {
        private String label;
        private ParcelDestination(String whereTo) {
            label = whereTo;
        }
        public String readLabel() { return label; }

        // Las clases anidadas pueden contener otros elementos estáticos:
        public static void f() {}
        static int x = 10;
        static class AnotherLevel {
            public static void f() {}
            static int x = 10;
        }
    }

    public static Destination destination(String s) {
        return new ParcelDestination(s);
    }

    public static Contents contents() {
        return new ParcelContents();
    }

    public static void main(String[] args) {
        Contents c = contents();
        Destination d = destination("Tasmania");
    }
}
```

---

Como hemos visto, en una clase interna (no estática), el vínculo con el objeto de clase externa se utiliza empleando una referencia `this` especial. Una clase anidada no tiene referencia `this` especial, lo que hace que sea análoga a una método estático.

---

## Clases dentro de interfaces

Normalmente no podemos incluir cualquier código dentro de una interfaz, pero una clase anidada *puede* ser parte de una interfaz. Cualquier clase que coloquemos dentro de una interfaz será automáticamente pública y estática. Puesto que la clase es estática no viola las reglas de las interfaces: simplemente, la case anidada se incluye dentro del espacio de nombre de la interfaz. Podemos incluso implementar la interfaz contenedora dentro de la clase contenedora de la forma siguiente:

```Java
public interface ClassInterface {
    void howdy();
    class Test implements ClassInterface {
        public void howdy() {
            System.out.println("Howdy!");
        }
        public static void main(String[] args) {
            new Test().howdy();
        }
    }
}
```

---

Resulta muy útil anidar una clase dentro de una interfaz cuando queremos crear un código compún que haya que emplear con todas las diferentes implementaciones de dicha interfaz.

---

## Acceso al exterior desde una clase multi-anidada

No importa con qué profundidad pueda estar anidada una clase interna: la case anidada podrá acceder transparentemente a todos los miembros de todas las clases dentro de las cuales esté anidada, como  podemos ver en el siguiente ejemplo:

```Java
class MNA {
    private void f() {}
    class A {
        private void g() {}
        public class B {
            void h() {
                g();
                f();
            }
        }
    }
}

public class MultiNestingAccess {
    public static void main(String[] args) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();
    }
}
```

---

Puede ver que en `MNA.A.B`, los métodos `g()` y `f()` son invocables sin necesidad de ninguna cualificación (a pesar del hecho de que son privados). Este ejemplo también ilustra la sintaxis necesaria para crear objetos de clases internas multianidadas cuando se crean los objetos en una clase diferente. La sintaxis `.new` genera el ámbito correcto, por lo que no hace falta cualificar el nombre de la clase dentro de la llamada al constructor.

---

## Clases internas locales

Una clase interna local no puede tener un especificador de acceso, porque no forma parte de la clase externa, pero que tiene acceso a las variables finales del bloque de código actual y a todos los miembros de la clase contenedora.
```Java

public interface Counter {
    int next();
}

public class LocalInnerClass {
    private int count=0;
    Counter getCounter(final String name) {
        // una clase interna local
        class LocalCounter implements Counter {
            public LocalCounter() {
                // la clase interna local puede tener u constructor
                print("LocalCounter()");
            }
            public int next() {
                printnb(name); // acceso a variable local final
                return count++;
            }
        }
        return new LocalCounter();
    }

    // Lo mismo con una clase interna anónima:
    Counter getCounter2(final String name) {
        return new Counter() {
            // La clase interna anónima no puede tener un constructor
            // nominado, sino sólo un inicializador de instancia:
            {
                print("Counter()");
            }
            public int next() {
                print(name); // Acceso a una variable local final
                return count++;
            }
        };
    }
    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter
            c1 = lic.getCounter("Local inner "),
            c2 = lic.getCounter("Anonymous inner ");
        for (int i=0; i < 5; i++)
            print(c1.next());
        for(int i=0; i<5; i++)
            print(c2.next());
    }
}
```
---

**Cunter** devuelve el siguiente valor de una secuencia. Está implementando como una clase local y como una clase interna anónima, teniendo ambas los mismos comportamientos y capacidades. Puesto que el nombre de la clase interna local no es accesible fuera del método, la única justificación para utilizar una clase interna local en lugar de una clase interna anónima es que necesitemos un constructor nominado y/o un constructor sobrecargado, ya que una clase interna anónima sólo puede utilizar un mecanismo de inicialización de instancia.

Otra razón para definir una clase internal local en lugar de una clase interna anónima es que necesitemos construir más de un objeto de dicha clase.