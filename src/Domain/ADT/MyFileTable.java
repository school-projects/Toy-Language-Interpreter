package Domain.ADT;

import Domain.Exception.ADT.ADTExc;
import Domain.Exception.MyExc;

import javax.swing.plaf.synth.SynthUI;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;

public class MyFileTable implements IMyFileTable {

    private IMyDict<Integer, IMyPair<String, BufferedReader>> dict;
    private Integer nextKey;

    public MyFileTable() {
        this.dict = new MyDict<>();
        generateKey();
    }

    private void generateKey(){
        Random r = new Random();
        while(dict.exists(this.nextKey) || this.nextKey == null)
            this.nextKey = r.nextInt() & Integer.MAX_VALUE;
    }

    @Override
    public Integer add(IMyPair<String, BufferedReader> pr)  {
        dict.add(nextKey, pr);
        Integer ret = nextKey;
        generateKey();
        return ret;
    }

    @Override
    public void remove(Integer k) throws ADTExc {
        dict.remove(k);
    }

    @Override
    public IMyPair<String, BufferedReader> lookup(Integer k) throws ADTExc {
        return dict.lookup(k);
    }

    @Override
    public IMyDict<Integer, IMyPair<String, BufferedReader>> getContent(){
        return dict;
    }

    @Override
    public ArrayList<MyPair<Integer,String>> getEntrySet() {
        ArrayList<MyPair<Integer, String>> ret = new ArrayList<>();
        for(MyPair<Integer, IMyPair<String, BufferedReader>> e : dict.getEntrySet())
        {
            ret.add(new MyPair<Integer,String>(e.getFirst(), e.getSecond().getFirst()));
        }
        return ret;
    }

    @Override
    public String toString(){
        return dict.toString();
    }
}
