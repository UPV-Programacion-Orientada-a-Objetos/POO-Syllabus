public interface Service<T,U> {
    T executeService(U... args);
}

public class MyService implements Service<String, Integer> {
    @Override
    public String executeService(Integer... args) {
        // do stuff
        return null;
    }
}