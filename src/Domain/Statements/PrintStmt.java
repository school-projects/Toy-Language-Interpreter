package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.ADT.IMyList;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.MyExc;
import Domain.Exception.Statements.InvalidExpExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.IExp;
import Domain.PrgState;

public class PrintStmt implements IStmt {

    private IExp exp;

    public PrintStmt() {
    }

    public PrintStmt(IExp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Print " + exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws StmtExc {
        IMyList<Integer> queue = state.getOut();
        IMyDict<String, Integer> sTable = state.getsTable();
        IMyHeap heap = state.getHeap();
        try {
            queue.add(exp.eval(sTable,heap));
        } catch (ExpExc expExc) {
            throw new InvalidExpExc("Invalid expression: " + exp.toString());
        }
        return null;
    }
}
