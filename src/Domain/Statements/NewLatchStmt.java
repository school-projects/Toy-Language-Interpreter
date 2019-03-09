package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.ADT.IMyLatchTable;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Statements.InvalidExpExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.IExp;
import Domain.PrgState;

public class NewLatchStmt implements IStmt{
    String varname;
    IExp exp;

    public NewLatchStmt(String varname, IExp exp) {
        this.varname = varname;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtExc {
        IMyDict<String, Integer> sT = state.getsTable();
        IMyLatchTable l = state.getLatchTable();

        try {
            sT.add(varname, l.add(exp.eval(sT, state.getHeap())));
        } catch (ExpExc expExc) {
            throw new InvalidExpExc("New statement: invalid expression");
        }
        return null;
    }

    @Override
    public String toString(){
        return "NewLatch " + varname + " " + exp.toString();
    }
}
