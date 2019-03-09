package Domain.ADT;

import java.util.Stack;

public class MyStack<E> implements IMyStack<E> {

    private Stack<E> stk;

    public MyStack(){stk = new Stack<E>();}

    @Override
    public E pop() {
        return stk.pop();
    }

    @Override
    public void push(E e) {
        stk.push(e);
    }

    @Override
    public boolean isEmpty() {
        return stk.isEmpty();
    }

    @Override
    public Stack<E> getContent() {
        return stk;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for(E e : stk)
        {
            returnString.append(e.toString());
            returnString.append("\n");
        }
        return returnString.toString();
    }
}
