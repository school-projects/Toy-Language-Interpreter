package Repo;

import Domain.ADT.IMyList;
import Domain.ADT.MyList;
import Domain.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repo implements IRepo{

    private IMyList<PrgState> lst;

    public Repo() {
        this.lst = new MyList<PrgState>();
    }

    @Override
    public void addPrg(PrgState state) {
        lst.add(state);
    }

    @Override
    public IMyList<PrgState> getPrgLst() {
        return lst;
    }

    @Override
    public void setPrgLst(IMyList<PrgState> l) {
        lst = l;
    }


    @Override
    public void logPrgState(PrgState prg) {

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("log.txt",true)))) {
            pw.write(prg.toString());
            System.out.println(prg.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
