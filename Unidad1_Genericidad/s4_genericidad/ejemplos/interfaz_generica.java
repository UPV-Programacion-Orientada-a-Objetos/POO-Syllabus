public class MyBoundedInterface {
 
    public static void main(String a[]){
         
        
        BoundExmp<Y> bey = new BoundExmp<Y>(new Y());
        bey.doRunTest();
        
        BoundExmp<Z> bez = new BoundExmp<Z>(new Z());
        bez.doRunTest();
        
    }
}
 
class BoundExmp<T extends X>{
     
    private T objRef;
     
    public BoundExmp(T obj){
        this.objRef = obj;
    }
     
    public void doRunTest(){
        this.objRef.printClass();
    }
}
 
interface X{
    public void printClass();
}
 
class Y implements X{
    public void printClass(){
        System.out.println("I am in class Y");
    }
}
 
class Z implements X{
    public void printClass(){
        System.out.println("I am in class Z");
    }
}