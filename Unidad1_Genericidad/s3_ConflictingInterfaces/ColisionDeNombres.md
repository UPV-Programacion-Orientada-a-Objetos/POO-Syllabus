---
marp: true
title: Colisión de nombres
description: Slides principales para el tema de Interfaces de la materia Programación Orientada a Objetos, impartida por el Dr. Said Polanco Martagón.
# theme: gaia
paginate: true
_paginate: false
---
# Programación Orientada a Objetos
## Colisión de nombres
### Dr. Said P. Martagón
---

## Ampliación de la interfaz mediante herencia

Podemos añadir fácilmente nuevas declaraciones de métodos a una interfaz utilizando los mecanismos de herencia, y también podemos combinar varias interfaces mediante herencia para crear una nueva interfaz. En ambos casos, obtendremos una interfaz nueva, como se ve en el siguiente ejemplo:

```Java
public interface Monster {
    void menace();
}

interface DangerousMonster extends Monster {
    void destroy();
}

interface Lethal {
    void kill();
}

class DragonZilla implements DangeorusMonster {
    public void menace() {}
    public void destroy() {}
}
```

---

```Java
interface Vampire extends DangerousMonster, Lethal {
    void drinkBlood();
}

class veryBadVampire implements Vampire {
    public void menace() {}
    public void destroy() {}
    public void kill() {}
    public void drinkBlood() {}
}

public class HorroShow{
    static void u(Monster b) { b.menace(); }
    static void v(DangerousMonster d) {
        d.menace();
        d.destroy();
    }

    static void w(Lethal l) { l.kill(); }

    public static void main(String[] args) {
        DangerousMonster barney = new DragonZilla();
        u(barney);
        v(barney);
        Vampire EdwardCullen = new VeryBadVampire();
        u(EdwardCullen);
        v(EdwardCullen);
        w(EdwardCullen);

    }
}
```
---
`DangerousMonster` es una extensión simple de `Monster` que produce una nueva interfaz. Ésta se implementa en `DragonZilla`.

La sintaxis empleada en `Vampire` sólo funciona cuando se heredan interfaces.

---
## Colisión de nombres

Veamos el siguiente código:

```Java
interface CanFight {
    void fight();
}

interface CanSwim {
    void swim();
}

interface CanFly {
    void fly();
}

class ActionCharacter {
    public void fight() {}
}

class Hero extends ActionCharacter implements CanFight, CanSwim, CanFly {
    public void swim() {}
    public void fly() {}
}

public class Adventure {
    public static void t(CanFight x) { x.fight(); }
    public static void u(CanSwim x) { x.swim(); }
    public static void v(CanFly x) { x.fly(); }
    public static void w(ActionCharacter x) { x.fight(); }
    public static void main(String[] args) {
        Hero h = new Hero();
        t(h);
        u(h);
        v(h);
        w(h);
    }
}
```
---
Sin embargo, podemos encontrarnos con un pequeño problema a la hora de implementar múltiples interfaces. En el ejemplo anterior, tanto `CanFight`como `ActionCharacter` tienen métodos idénticos `void fight()`. El que haya dos métodos idénticos no resulta problemático, pero ¿qué sucede si los métodos difieren en cuanto a signatura o en cuanto a tipo de retorno?

---

```Java
interface I1 { void f(); }
interface I2 { int f(int i); }
interface I3 { int f(); }

class C { public int f() { return 1; } }

class C2 implements I1, I2 {
    public void f();
    public int f(int i) { return 1; } // sobrecargado
}

class C4 extends C implements I3 {
    // Idéntico, no hay problema
    public int f() { return 1; }
}
// Los métodos sólo difieren en el tipo de retorno
class C5 extends C impelents I1 {} // Error
interface I4 extends I1, I3 {}
```
---

La dificultad surge porque los mecanismos de anulación, de implementación y de sobrecarga se entremezclan de forma compleja. Asimismo, los métodos sobrecargados no pueden diferir sólo en cuanto al tipo de retorno.

Las dos últimas líneas los mensajes de error nos informa del problema:
  > ```InterfaceCollision.java ... ```

Asimismo, utilizar los mismos nombres de método en diferentes interfaces que vayan a ser combinadas suele aumentar la confusión en lo que se respecta ala lagibilidad del código.

--- 
## Anidamiento de interfaces

