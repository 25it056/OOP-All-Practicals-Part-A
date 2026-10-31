class Box<T> {
    private T value;
    public void set(T value) { this.value = value; }
    public T get() { return value; }
}
class Pair<A, B> {
    private final A first;
    private final B second;
    public Pair(A first, B second) { this.first = first; this.second = second; }
    public A getFirst() { return first; }
    public B getSecond() { return second; }
}

public class GenericDemo {
    public static <T> T getFirst(T[] array) {
        if (array == null || array.length == 0) throw new IllegalArgumentException("Array must not be empty");
        return array[0];
    }
    public static void main(String[] args) {
        Box<Integer> box1 = new Box<>(); box1.set(100);
        Box<String> box2 = new Box<>(); box2.set("Java");
        System.out.println("Integer Box: " + box1.get());
        System.out.println("String Box: " + box2.get());
        Pair<String, Integer> pair = new Pair<>("Bhavya", 25);
        System.out.println("Name: " + pair.getFirst());
        System.out.println("ID: " + pair.getSecond());
        String[] names = {"Amit", "Rahul", "Priya"};
        System.out.println("First Element: " + getFirst(names));
    }
}
