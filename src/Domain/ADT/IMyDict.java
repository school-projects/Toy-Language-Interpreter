package Domain.ADT;

import Domain.Exception.ADT.ADTExc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IMyDict<K,V> {
    void add(K key, V val);
    void update(K key, V val);
    V lookup(K id) throws ADTExc;
    void remove(K id) throws ADTExc;
    boolean exists(K id);
    HashMap<K,V> getContent();
    void setContent(HashMap<K,V> dct);
    IMyDict<K,V> deepcopy();
    ArrayList<MyPair<K,V>> getEntrySet();
    String toString();
}
