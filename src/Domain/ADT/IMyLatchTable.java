package Domain.ADT;

import Domain.Exception.ADT.ADTExc;

import java.util.ArrayList;
import java.util.Map;

public interface IMyLatchTable {

    Integer add(Integer val);
    void update(Integer k, Integer v);
    Integer lookup(Integer id) throws ADTExc;
    void remove(Integer id) throws ADTExc;
    boolean exists(Integer id);
    Map<Integer, Integer> getContent();
    void setContent(Map<Integer, Integer> dct);
    ArrayList<MyPair<Integer,Integer>> getEntrySet();

    String toString();
}
