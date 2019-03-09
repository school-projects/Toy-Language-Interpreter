package Domain.ADT;

import java.util.LinkedList;
import java.util.List;


public class MyList<E> implements IMyList<E> {

    private List<E> lst;

    public MyList() { lst = new LinkedList<E>();}

    public MyList(List<E> lst) { this.lst = lst; }

    @Override
    public void add(E e) {
        lst.add(e);
    }

    @Override
    public E get(int i) {
        return lst.get(i);
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for(E e : lst)
        {
            returnString.append(e.toString());
            returnString.append("\n");
        }
        return returnString.toString();
    }

    @Override
    public List<E> getContent() {
        return lst;
    }

    @Override
    public void setContent(List<E> lst) {
        this.lst = lst;
    }
}
