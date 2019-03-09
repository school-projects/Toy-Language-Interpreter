package Domain.ADT;

import Domain.Exception.ADT.ADTExc;
import Domain.Exception.ADT.KeyNotFoundExc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyHeap implements IMyHeap {

    private Map<Integer,Integer> dict;

    public MyHeap() {

        this.dict = new HashMap<Integer,Integer>();
    }

    @Override
    public Integer add(Integer val) {

        dict.put(dict.size()+1,val);
        return dict.size();
    }

    @Override
    public void update(Integer k, Integer v) {

        dict.put(k,v);
    }

    @Override
    public Integer lookup(Integer id) throws ADTExc {
        Integer ret;
        ret=dict.get(id);
        if(ret==null)
            throw new KeyNotFoundExc("Key not found!");
        return ret;
    }

    @Override
    public void remove(Integer id) throws ADTExc {
        if(dict.get(id)==null)
            throw new KeyNotFoundExc("Key not found!");
        dict.remove(id);
    }

    @Override
    public boolean exists(Integer id) {
        return dict.get(id) != null;
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return dict;
    }

    @Override
    public void setContent(Map<Integer, Integer> dct) {
        dict = dct;
    }

    @Override
    public ArrayList<MyPair<Integer,Integer>> getEntrySet() {
        ArrayList<MyPair<Integer, Integer>> ret = new ArrayList<>();
        for(HashMap.Entry<Integer, Integer> e : dict.entrySet())
        {
            ret.add(new MyPair<Integer, Integer>(e.getKey(), e.getValue()));
        }

        return ret;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for(HashMap.Entry<Integer, Integer> e : dict.entrySet())
        {
            returnString.append(e.getKey().toString());
            returnString.append("-->");
            returnString.append(e.getValue().toString());
            returnString.append("\n");
        }
        return returnString.toString();
    }
}
