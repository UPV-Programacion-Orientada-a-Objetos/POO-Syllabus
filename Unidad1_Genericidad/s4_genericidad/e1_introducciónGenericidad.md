---
marp: true
title: Introducción a la genericidad en Java
description: Slides principales para el tema de Interfaces de la materia Programación Orientada a Objetos, impartida por el Dr. Said Polanco Martagón.
# theme: gaia
paginate: true
_paginate: false
---

# Progamación Orientada a Objetos
## Introducción a la genericidad
### Dr. Said P. Martagón

---

## Clases fundamentales

Todas lcas clases de java poseen una superclase común, esa es la clase `Object`. Por eso los métodos de la clase `Object` son fundamentales, ya que todas las clases los heredan.

Esos métodos están pensados para todas las clases, pero hay que definirlos para que funcionen adecuadamente.

Es decir, `Object proporciona métodos que son heredados por todas las clases. La idea es que todas las clases utilicen el mismo nombre y prototípo de método para hacer operaciones comunes como **comprobr igualdad, clonar, etc**, y para ello habrá que definir esos métodos a fin de que se ajusten adecuadamente a cada clase.

---

## Comparar Objetos.

La clase `Object`proporciona un método para comprobar si dos objetos son iguales. Este método es `equals`. Este método recibe como parámetro un objeto con quien comparar y devuelve true si los dos objetos son iguales.

No es lo mismo equals que usar la comparación de igualdad. Ejemplos:

```Java
Coche uno = new cohce("Renault", "Megane", "P4324K");
Coche dos=uno;
boolean resultado=(uno.equals(dos)); // Resultado valdrá true
resultado = (uno==dos); // Resultado también valdrá true
dos = new new cohce("Renault", "Megane", "P4324K");
resultado = (uno.equals(dos)); // resultado valdrá true
resultado = (uno == dos); // Resultado ahora valdrá false
```

---

El operador `==` devuelve true si los dos objetos se refieren a la misma cosa (las dos referencias apuntan al mismo objeto). 

Realmente en el ejemplo anterior la respuesta del método equals sólo será válida si en la clase que se está comparando (Coche en el ejemplo) se ha redefinido el método equals. Esto no es opcional sino obligatorio si se quiere usar este método. El resultado de equals depende de cuándo consideremos nosotros que devolver verdadero o falso.

---

La clase Object define una serie de métodos que pueden utilizarse sobre todos los objetos que se creen en Java. Estos métodos son: `clone(), equals(), finalize(), getClass(), hashCode(), toString(), wait(), notify() y notifyAll()`.

El método `toString()` devuelve una cadena asociada al objeto. De esta forma todos los objetos en Java tienen una representación en forma de cadena de texto.

El método `hashCode()` devuelve un entero que puede utilizarse como identificador único del objeto en la máquina virtual.

---

## La madre de todas las clases `Object`

En Java todas las clases descienden de la clase Object.

![object_hierarchy](img/fig1.png)

Es como si el compilador añadiera **“extends Object”** a todas las clases que no extienden explícitamente a ninguna otra.

---

## Introducción: Genericidad

La programación orientada a objetos tiene como principales objetivos favorecer **la confiabilidad, reusabilidad y extensibilidad** del software.

Adoptar el enfoque propuesto por la programación orientada a objetos implica:
  * En la etapa de diseño reducir la complejidad en base a la descomposición del problema en piezas más simples a partir de la identificación de objetos y su organización en una estructura de clases.
  * En la etapa de implementación utilizar un lenguaje que permita retener la estructura de clases identificada en la etapa de diseño y encapsular la representación interna de modo que sea inaccesible desde el exterior.

---

### Genericidad

El **encapsulamiento** permite usar una clase considerando qué funcionalidad brinda, sin tener en cuenta cómo la implementa.

La **herencia** permite aumentar el nivel de abstracción mediante un proceso de clasificación en niveles.

El proceso consiste en abstraer lo que es común y esencial en un conjunto de entidades para formar un concepto general que comprenda a todas.

Una clase derivada puede pensarse como una **especialización** de una clase más general.

Alternativamente podemos pensar a una clase derivada como una **extensión** de la clase base.

---

La extensibilidad se refiere a reducir el impacto de los cambios. 

Las modificaciones con frecuencia pueden resolverse definiendo nuevas clases específicas, sin necesidad de cambiar las que ya han sido verificadas.

La reusabilidad evita escribir el mismo código repetidamente. Para lograrlo es necesario distinguir el comportamiento general del particular.

La genericidad favorece la reusabilidad.

**Una clase *genérica* encapsula a una estructura cuyo comportamiento es independiente del tipo de las componentes.**

La genericidad puede modelarse en Java de dos maneras diferentes: usando **tipos de datos parametrizados** o usando **herencia**.

---

La genericidad es un mecanismo que permite:
  * Escribir trozos de código genérico (subprogramas, clases, interfaces, packages), que incluye referencias a uno o variosnombres de tipos de datos o clases sin que exista una declaración de los mismos (se denominan parámetros de tipo).
  * Particularizar el código anterior, indicando los tipos concretos existentes en los que se convierten los parámetros de tipos, y generando código concreto (todo esto hecho por el compilador en tiempo de compilación).
  * Especificar restricciones para los paámetros de tipo del código genérico.

---

## Esquema de la genericidad

![esquema_genericidad](img/fig2.png)

---

### Ventajas de la genericidad

Simplicidad y legibilidad: permite agrupar comportamiento común y reducir el tamaño de los programas.

Fiabilidad: detección temprana de errores en el proceso de desarrollo.

Eficiencia: permite al compilador, en tiempo de compilación, generar código eficiente en tiempo y memoria adaptando a cada instanciación correcta del template.

---

La genericidad se usa para agrupar comportamiento común y evitar código distinto pero con aspectos comunes.

  * La herencia y el polimorfismo de herencia permiten lo mismo.
  * Los mecanismos anteriores son mecanismos básicamente resueltos en tiempo de ejecución.
  * La genericidad es un mecanismo en tiempo de compilación.
  * La herencia permite agrupar comportamiento mediante la extensión de tipos,  luego debe preveerse en el diseño de una clase.
  * La genericidad permite lo mismo sin mediar la extensión de tipos, luego impone menos condiciones al código y puede usarse para una clase después de ser diseñada.
  * En algunos lenguajes, la herencia y el polimorfismo de herencia suponen un coste en tiempo y memoria mayor que el que supone la genericidad.

---

## Ejemplo de genericidad en Java

La genericidad fue agregada en java 5 para prover chequeo en tiempo de compilación y eliminar el riesgo de excepciones de casteo (ClassCastException) muy común cuando se trabajaba con colecciones. El framework de colecciones fue totalemente renovado utilizando clases genéricas. Veamos cómo la genericidad nos ayuda en las colecciones.

```Java
List list = new ArrayList();
list.add("abc");
list.add(new Integer(5)); // OK

for (object obj: list) {
    String str = (String) obj; // type casting leading to ClassCastException at runtime
}
```

---

Después de java 5, podemos utilizar las clases colección de la siguiente manera:

```Java
List<String> list1 = new ArrayList<String>(); // Java 7 ? List<String> list1 = new ArrayList<>();
list1.add("abc");
//list1.add(new Integer(5)); // compile error

for (String str : list1) {
    // no type casting needed, avoids ClassCastException
}
```

---

## Genericidad en clases e Interfaces

Podemos definir nuestras propias clases e interfaces con tipos genéricos. Un tipo genérico es una clase o interfaz que parametrizada sobre tipos. Se utilizan los paréntesis angulares (<>) para especificar el parámetro de tipo.

```Java
public class GenericTypeOld {
    private Object t;

    public Object get() {
        return t;
    }

    public void set(Object t) {
        this.t = t;
    }

    public static void main(String args[]) {
        GenericTypeOld type = new GenericTypeOld();
        type.set("Pankaj");
        // type casting, error prone and can cause ClassCastException
        String str = (String) type.get(); 
    }
}
```