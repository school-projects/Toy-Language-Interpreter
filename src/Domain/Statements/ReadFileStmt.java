package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyFileTable;
import Domain.ADT.IMyHeap;
import Domain.Exception.ADT.ADTExc;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Statements.FileNotOpenExc;
import Domain.Exception.Statements.ReadFileExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.VarExp;
import Domain.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt{
    private String varname;
    private VarExp fileid;
    public ReadFileStmt(VarExp fileid, String varname) {
        this.fileid = fileid;
        this.varname = varname;
    }


    @Override
    public PrgState execute(PrgState state) throws StmtExc {
        IMyDict<String, Integer> sTab = state.getsTable();
        IMyFileTable fT = state.getFileTable();
        IMyHeap heap = state.getHeap();

        BufferedReader b = null;
        try {
            b = fT.lookup(fileid.eval(sTab,heap)).getSecond();
        } catch (ExpExc | ADTExc myExc) {
            throw new FileNotOpenExc("");
        }

        String l = null;
        try {
            l = b.readLine();
        } catch (IOException e) {
            throw new ReadFileExc("Problem with file!");
        }

        Integer ret = 0;
        if(l != null)
            ret = Integer.parseInt(l);

        if(sTab.exists(varname))
            sTab.update(varname,ret);
        else
            sTab.add(varname, ret);

        return null;
    }

    @Override
    public String toString(){
        return "Read " + fileid + " " + varname;
    }
}
