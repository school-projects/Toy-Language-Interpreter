package Domain.ADT;

import Domain.Exception.ADT.KeyNotFoundExc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyDict<K,V> implements IMyDict<K,V> {

    private HashMap<K,V> dict;

    public MyDict() { dict = new HashMap<K,V>();}

    @Override
    public void add(K key, V val) {
        dict.put(key,val);
    }

    @Override
    public void update(K key, V val){
        dict.put(key,val);
    }

    @Override
    public V lookup(K id) throws KeyNotFoundExc {
        if(dict.get(id) != null){
            return dict.get(id);
        }
        throw new KeyNotFoundExc("Lookup failed!");
    }

    @Override
    public void remove(K id) throws KeyNotFoundExc {
        if(dict.get(id) == null)
            throw new KeyNotFoundExc("Remove failed!");
        dict.remove(id);
    }

    @Override
    public boolean exists(K id) {
        return dict.get(id) != null;
    }

    @Override
    public HashMap<K, V> getContent() {
        return dict;
    }

    @Override
    public void setContent(HashMap<K, V> dct) {
        dict = dct;
    }

    @Override
    public IMyDict<K, V> deepcopy() {
        MyDict<K,V> ret = new MyDict<K,V>();
        for(HashMap.Entry<K,V> e : dict.entrySet())
        {
            ret.add(e.getKey(), e.getValue());
        }
        return ret;
    }

    @Override
    public ArrayList<MyPair<K, V>> getEntrySet() {
        ArrayList<MyPair<K,V>> ret = new ArrayList<>();
        for(HashMap.Entry<K,V> e : dict.entrySet())
        {
            ret.add(new MyPair<K,V>(e.getKey(), e.getValue()));
        }

        return ret;
    }


    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for(HashMap.Entry<K, V> e : dict.entrySet())
        {
            returnString.append(e.getKey().toString());
            returnString.append("-->");
            returnString.append(e.getValue().toString());
            returnString.append("\n");
        }
        return returnString.toString();
    }
}
