package Domain.Statements;

import Domain.ADT.MyStack;
import Domain.Exception.Statements.StmtExc;
import Domain.PrgState;

public class ForkStmt implements IStmt {

    IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtExc {
        PrgState newPrg =  new PrgState(new MyStack<IStmt>(), state.getsTable().deepcopy(),  state.getFileTable(), state.getOut(), state.getHeap(),state.getLatchTable() , state.getID()*10, stmt);
        return newPrg;
    }

    @Override
    public String toString() {
        return "Fork " + stmt.toString();
    }

}
