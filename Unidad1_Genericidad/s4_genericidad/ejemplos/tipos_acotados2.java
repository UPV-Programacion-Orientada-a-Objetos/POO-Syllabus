public class MyBoundedClassEx {
 
    public static void main(String a[]){
        
        BoundEx<C> bec = new BoundEx<C>(new C());
        bec.doRunTest();
        
        BoundEx<B> beb = new BoundEx<B>(new B());
        beb.doRunTest();
        
        BoundEx<A> bea = new BoundEx<A>(new A());
        bea.doRunTest();
        
    }
}

class BoundEx<T extends A>{
     
    private T objRef;
     
    public BoundEx(T obj){
        this.objRef = obj;
    }
     
    public void doRunTest(){
        this.objRef.printClass();
    }
}
 
class A{
    public void printClass(){
        System.out.println("I am in super class A");
    }
}
 
class B extends A{
    public void printClass(){
        System.out.println("I am in sub class B");
    }
}
 
class C extends A{
    public void printClass(){
        System.out.println("I am in sub class C");
    }
}