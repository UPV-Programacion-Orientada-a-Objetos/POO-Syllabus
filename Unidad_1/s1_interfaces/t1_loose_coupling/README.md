---
marp: true
title: Interfaces
description: Slides principales para el tema de Interfaces de la materia Programación Orientada a Objetos, impartida por el Dr. Said Polanco Martagón.
# theme: gaia
paginate: true
_paginate: false
---

# Object Oriented Programming
## Interfaces
### Loose-coupling

---

### Loose-coupling

'Coupling' is a term that describes the relationship between two entities in a software system (usually classes).

When a class uses another class, or communicates with it, it's said to 'depend' on that other class, and so these classes are 'coupled'. At least one of them 'knows' about the other.

The idea is that we should try to keep the coupling between classes in our systems as 'loose' as possible: hence 'loose coupling' or sometimes 'decoupling'.

**So: what is loose-coupling versus strong coupling in practice, and why should we make entities loosely-coupled?**

---

Loose coupling is good because we don't want the components of our system to heavily depend on each other. We want to keep our system modular, **where we can safely change one part without affecting the other**.

*If entity A makes too many assumptions about how entity B operates or how it is built, than there is a high risk that a change in entity B will affect entity A.*

**An important technique to achieve loose coupling is Encapsulation**. The idea is that a class hides it's internal details from other classes, and offers a strictly defined interface for other classes to communicate with it.

for example, if you were defining a class Car, it's interface (public methods) would probably be `drive()`, `stop()`, `steerLeft()` , `steerRight()` , `getSpeed()` . These are the methods other objects can invoke on Car objects.

---

### Multiple inheritance

At some point, a programmer may be tempted to derive a single class of several classes. This is known as "multiple inheritance" and although it seems useful, it can cause problems, such as the important "diamond problem". This problem occurs when two classes inherit from the same class (as class B and C derive from class A), while another class (D) derives from B and C. When a D object is created, the system treats it as a type of base class.

In the diamond problem, the system can not determine decisively which class D is causing the problems.

---

### Diamond Problem example

```Java
public interface A {
    void m();
}

public interface B extends A {
    void x();
}

public interface C extends A {
    void y();
}

public class D implements B, C {
    ...
}
```
---

### Why java doesn’t accept multiple inheritance?

Multiple implementation inheritance is the ability to inherit method definitions of various classes.

**This type of inheritance is not possible in Java.**

Conflicts of names and ambiguity. When the compilers of the programming languages that support this type of multiple succession superclasses contain methods with the same name, sometimes they can not determine which member or method to access or invoke.