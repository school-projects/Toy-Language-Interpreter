package Domain.ADT;

import java.util.Stack;

public interface IMyStack<E> {
    E pop();
    void push(E e);
    boolean isEmpty();
    Stack<E> getContent();
    String toString();
}
