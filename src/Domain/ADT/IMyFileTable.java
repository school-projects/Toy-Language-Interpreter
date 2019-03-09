package Domain.ADT;

import Domain.Exception.ADT.ADTExc;
import Domain.Exception.ADT.KeyNotFoundExc;
import Domain.Exception.MyExc;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

public interface IMyFileTable {

    Integer add(IMyPair<String, BufferedReader> pr);
    void remove(Integer k) throws ADTExc;
    IMyPair<String, BufferedReader> lookup(Integer k) throws ADTExc;
    String toString();
    IMyDict<Integer, IMyPair<String, BufferedReader>> getContent();
    ArrayList<MyPair<Integer,String>> getEntrySet();

}
