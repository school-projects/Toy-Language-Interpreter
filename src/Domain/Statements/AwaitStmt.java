package Domain.Statements;

import Domain.ADT.*;
import Domain.Exception.ADT.ADTExc;
import Domain.Exception.Statements.StmtExc;
import Domain.PrgState;

public class AwaitStmt implements IStmt {

    public AwaitStmt(String var) {
        this.var = var;
    }

    private String var;

    @Override
    public PrgState execute(PrgState state) throws StmtExc {

        IMyStack<IStmt> stk = state.getExeStack();
        IMyDict<String, Integer> sTable = state.getsTable();
        IMyLatchTable lTable = state.getLatchTable();

        Integer lval = 0;
        try {
             lval = lTable.lookup(sTable.lookup(var));
        } catch (ADTExc adtExc) {
            throw new StmtExc("Latch not found!");
        }

        if(lval != 0)
            stk.push(this);

        return null;
    }

    @Override
    public String toString(){
        return "Await(" + this.var + ")";
    }
}
