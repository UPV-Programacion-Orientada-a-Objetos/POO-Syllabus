public class MySimpleGenerics {
 
    public static void main(String a[]){
         
        
        SimpleGeneric<String> sgs = new SimpleGeneric<String>("JAVA2NOVICE");
        sgs.printType();
        
        SimpleGeneric<Boolean> sgb = new SimpleGeneric<Boolean>(Boolean.TRUE);
        sgb.printType();
    }
}
 

class SimpleGeneric<T>{
     
    
    private T objReff = null;
     
    
    public SimpleGeneric(T param){
        this.objReff = param;
    }
     
    public T getObjReff(){
        return this.objReff;
    }
     
    
    public void printType(){
        System.out.println("Type: "+objReff.getClass().getName());
    }
}