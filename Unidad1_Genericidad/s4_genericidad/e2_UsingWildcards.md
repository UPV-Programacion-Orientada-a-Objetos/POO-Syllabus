---
marp: true
title: Introducción a la genericidad en Java
description: Slides principales para el tema de Interfaces de la materia Programación Orientada a Objetos, impartida por el Dr. Said Polanco Martagón.
# theme: gaia
paginate: true
_paginate: false
---

# Progamación Orientada a Objetos
## Using Wildcards
### Dr. Said P. Martagón

---

## Usinga Wildcard arguments

As useful as type safety is, sometimes it can get in the way of perfectly acceptable constructs. For example, given the `Stats` class.

```Java
class Stats<T extends Number>
{
    T[] nums; // array of Number or subclass

    // Pass the constructor a reference to an array of type Number or subclass
    Stats[T[] o]
    {
        nums = 0;
    }

    // Return type double in all cases
    double average()
    {
        double sum = 0.0;

        for (int i=0; i < nums.length; i++)
        {
            sum += sum[i].doubleValue();

            return sum / nums.lenth;
        }
    }
}
```

---

```Java
class BoundsDemo {
    public static void main(String args[])
    {
        Integer inums[] = {1, 2, 3, 4, 5};
        Stats<Integer> iob = new Stats<Integer>(inums);
        double v = iob.average();
        System.out.println("iob average is " + v);

        Double dnums[] = {1.1, 2.2, 3.3, 4.4, 5.5};
        Stats<Double> dob = new Stats<Double>(dnums);
        double w = dob.average();
        System.out.println("dob average is " + w);

        // This won't compile because String is not a subclass of Number
        String strs[] = {"1", "2", "3", "4", "5"};
        Stats<String> strob = new Stats<String>(strs);

        double x = strob.average();
        System.our.println("strob average is " + v);
    }
}
```

---

Assume that you want to add a method called sameAvg( ) that determines if two Stats objects contain arrays that yield the same average, no matter what type of numeric data each object holds.

One way to implement `sameAvg( )` is to pass it a `Stats` argument, and then compare the average of that argument against the invoking object, returning true only if the averages are the same.

---

```Java
Integer inums[] = { 1, 2, 3, 4, 5};
Double dnums[] = {1.1, 2.2, 3.3, 4.4, 5.5};

Stats<Integer> iob = new Stats<Integer>(inums);
Stats<Double> dob = new Stats<Double>(dnums);

if (iob.sameAvg(dob)) {
    System.out.println("Average are the same.");
}
else {
    System.out.println("Averages differ.");
}
```

Unfortunately, trouble starts as soon as you try to declare a parameter of type `Stats`. Because Stats is a parameterized type, what do you specify for Stats’ type parameter when you declare a parameter of that type?

---

At first, you might think of a solution like this, in which `T` is used as the type parameter:
```Java
// This won't work!
// Determine if two average are the same.
boolean sameAvg(Stats<T> obj) {
    if (average() = ob.average()) {
        return true;
    }

    return false;
}
```

The trouble with this attempt is that it will work only with other Stats objects whose type is the same as the invoking object.

---

To create a generic `sameAvg()` method, you must use another feature of Java generics: the wildcard argument. The wildcard argument is specified by the `?`, and it represents an unknown type. Using a wildcard, here is one way to write the `sameAvg()` method
```Java
// Determine if twp averages are the same.
// Notice the use of the wildcard.
boolean sameAvg(Stats<?> ob) {
    if (average() == ob.average()) {
        return true;
    }
    else return false;
}
```

Here, `Stats<?>` matches any Stats object, allowing any two Stats objects to have their averages compared. See the next code

---

```Java
class Stats<T extends Number>
{
    T[] nums; // array of Number or subclass

    // Pass the constructor a reference to an array of type Number of sublcass
    Stats(T[] o)
    {
        nums = o;
    }

    // Return type double in all cases.
    double average()
    {
        double sum = 0.0;

        for (int i=0; i < nums.length; i++) {
            sum += nums[i].doubleValue();
        }
        return sum / nums.length;
    }

    // Determine if two average are the same.
    // Notice the use of the wildcard
    boolean sameAvg(Stats<?> ob)
    {
        if (average() == ob.average()) {
            return true;
        }
        
        return false;
    }
}
```

---

```Java
class BoundsDemo {
    
    public static void main(String args[])
    {
        Integer inums[] = {1, 2, 3, 4, 5};
        Stats<Integer> iob = new Stats<Integer>(inums);
        double v = iobAverage();
        System.out.println("iob average is " + v);

        Double dnums[] = {1.1, 2.2, 3.3, 4.4, 5.5};
        Stats<Double> dob = new Stats<Double>(dnums);
        double w = dob.average();
        System.out.println("dob average is " + w);

        Float fnums = {1.0F, 2.0F, 3.0F, 4.0F, 5.0F};
        Stats<Float> fob = new Stats<Float>(fnums);
        double x = fob.average();
        System.out.println("fob average is " + x);

        // See which arrays have same average.
        System.out.print("Average of iob and dob ");
        if (iob.sameAvg(dob)) {
            System.out.println("are the same");
        }
        else {
            System.out.println("differ.");
        }

        System.out.println("Averages of iob and fob");
        if (iob.sameAvg(fob)) {
            System.out.println("are the same");
        }
        else {
            System.out.println("differ.");
        }
    }
}
```

---

One last point: It is important to understand that the wildcard does not affect what type of Stats objects can be created. This is governed by the extends clause in the Stats declaration. The wildcard simply matches any valid Stats object.