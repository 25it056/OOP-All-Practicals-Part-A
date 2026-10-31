class ArrayStack<T> {
    private final Object[] array;
    private int top = -1;
    public ArrayStack(int size) {
        if (size <= 0) throw new IllegalArgumentException("Stack size must be positive");
        array = new Object[size];
    }
    public void push(T value) {
        if (top == array.length - 1) { System.out.println("Stack is full."); return; }
        array[++top] = value;
    }
    @SuppressWarnings("unchecked")
    public T pop() {
        if (top == -1) { System.out.println("Stack is empty."); return null; }
        T value = (T) array[top];
        array[top--] = null;
        return value;
    }
    public boolean isEmpty() { return top == -1; }
}

public class GenericStack {
    public static void main(String[] args) {
        ArrayStack<Integer> intStack = new ArrayStack<>(5);
        intStack.push(10); intStack.push(20); intStack.push(30);
        System.out.println("Integer Stack:");
        System.out.println(intStack.pop());
        System.out.println(intStack.pop());
        ArrayStack<String> stringStack = new ArrayStack<>(5);
        stringStack.push("Java"); stringStack.push("Python");
        System.out.println("String Stack:");
        System.out.println(stringStack.pop());
        System.out.println(stringStack.pop());
    }
}
