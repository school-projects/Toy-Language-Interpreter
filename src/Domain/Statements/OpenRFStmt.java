package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyFileTable;
import Domain.ADT.IMyPair;
import Domain.ADT.MyPair;
import Domain.Exception.MyExc;
import Domain.Exception.Statements.FileNotFoundExc;
import Domain.Exception.Statements.StmtExc;
import Domain.PrgState;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.Buffer;

public class OpenRFStmt implements IStmt {

    private String varname;
    private String filename;

    @Override
    public PrgState execute(PrgState state) throws StmtExc {
        IMyFileTable fT = state.getFileTable();

        BufferedReader fd;
        try {
            fd = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundExc("Can't open file!");
        }

        Integer id = fT.add(new MyPair<String, BufferedReader>(filename, fd));

        IMyDict<String, Integer> sTab = state.getsTable();
        if(sTab.exists(varname))
            sTab.update(varname, id);
        else
            sTab.add(varname,id);
        return null;
    }

    public OpenRFStmt(String varname, String filename) {
        this.varname = varname;
        this.filename = filename;
    }

    @Override
    public String toString(){
        return "Open " + varname + " " + filename;
    }
}
