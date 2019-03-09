package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyLatchTable;
import Domain.ADT.IMyStack;
import Domain.Exception.ADT.ADTExc;
import Domain.Exception.Statements.StmtExc;
import Domain.PrgState;

public class CountDownStmt implements IStmt {

    public CountDownStmt(String var) {
        this.var = var;
    }

    private String var;

    @Override
    public PrgState execute(PrgState state) throws StmtExc {

        IMyStack<IStmt> stk = state.getExeStack();
        IMyDict<String, Integer> sTable = state.getsTable();
        IMyLatchTable lTable = state.getLatchTable();

        Integer ind = 0;
        try {
            ind = sTable.lookup(var);
        } catch (ADTExc adtExc) {
            throw new StmtExc("Latch not found!");
        }

        Integer lval = 0;
        try {
            lval = lTable.lookup(ind);
        } catch (ADTExc adtExc) {
            return null;
        }

        if(lval != 0)
        {
            lTable.update(ind,lval-1);
            state.getOut().add(state.getID());
        }

        return null;
    }

    @Override
    public String toString(){
        return "Await(" + this.var + ")";
    }
}
