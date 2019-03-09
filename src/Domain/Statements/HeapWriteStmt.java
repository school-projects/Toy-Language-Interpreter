package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Statements.InvalidExpExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.IExp;
import Domain.Expressions.VarExp;
import Domain.PrgState;

public class HeapWriteStmt implements IStmt {

    private VarExp varname;
    private IExp exp;

    public HeapWriteStmt(VarExp varname, IExp exp) {
        this.varname = varname;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtExc {
        IMyHeap heap = state.getHeap();
        IMyDict<String,Integer> sT = state.getsTable();

        try {
            heap.update(varname.eval(sT, heap), exp.eval(sT,heap));
        } catch (ExpExc expExc) {
            throw new InvalidExpExc("Heap Write");
        }
        return null;
    }

    @Override
    public String toString(){
        return "HeapWriteStmt(" + varname.toString() + ", " + exp.toString()+")";
    }
}
