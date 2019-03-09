package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyFileTable;
import Domain.ADT.IMyHeap;
import Domain.Exception.ADT.ADTExc;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Statements.FileNotOpenExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.VarExp;
import Domain.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt {

    private VarExp filename;

    public CloseRFileStmt(VarExp filename) {
        this.filename = filename;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtExc {

        IMyDict<String, Integer> sTab = state.getsTable();
        IMyFileTable fT = state.getFileTable();
        IMyHeap heap = state.getHeap();

        BufferedReader b = null;
        try {
            b = fT.lookup(filename.eval(sTab,heap)).getSecond();
        } catch (ADTExc | ExpExc adtExc) {
            throw new FileNotOpenExc("Close failed!");
        }
        try {
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fT.remove(filename.eval(sTab,heap));
        } catch (ADTExc | ExpExc myExc) {
            throw new FileNotOpenExc("Close failed!");
        }

        return null;
    }

    @Override
    public String toString(){
        return "Close " + filename;
    }
}
