package Domain.ADT;

import java.util.List;

public interface IMyList<E> {
    void add(E e);
    E get(int i);
    String toString();
    List<E> getContent();
    void setContent(List<E> lst);
}
