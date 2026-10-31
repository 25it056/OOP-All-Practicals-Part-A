class MyResource implements AutoCloseable {
    MyResource() { System.out.println("Resource opened."); }
    void use() {
        System.out.println("Using resource.");
        throw new RuntimeException("Error inside try block.");
    }
    @Override public void close() { System.out.println("Resource closed."); }
}

public class AutoCloseableResourceDemo {
    public static void main(String[] args) {
        try (MyResource resource = new MyResource()) {
            resource.use();
        } catch (RuntimeException e) {
            System.out.println("Original error: " + e.getMessage());
        }
    }
}
