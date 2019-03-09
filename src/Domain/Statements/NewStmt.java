package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Statements.InvalidExpExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.IExp;
import Domain.PrgState;

public class NewStmt implements IStmt {
    String varname;
    IExp exp;

    public NewStmt(String varname, IExp exp) {
        this.varname = varname;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtExc {
        IMyDict<String, Integer> sT = state.getsTable();
        IMyHeap heap = state.getHeap();

        try {
            sT.add(varname, heap.add(exp.eval(sT, heap)));
        } catch (ExpExc expExc) {
            throw new InvalidExpExc("New statement: invalid expression");
        }
        return null;
    }

    @Override
    public String toString(){
        return "New " + varname + " " + exp.toString();
    }
}
