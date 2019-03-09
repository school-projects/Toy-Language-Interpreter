package Domain.Statements;

import Domain.Exception.MyExc;
import Domain.Exception.Statements.StmtExc;
import Domain.PrgState;
import Domain.ADT.*;

public class CompStmt implements IStmt
{
    private IStmt first;
    private IStmt snd;

    public CompStmt() {
    }

    public CompStmt(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }

    @Override
    public String toString() {

        return first.toString() + "; " + snd.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws StmtExc {

        IMyStack<IStmt> stk = state.getExeStack();
        stk.push(snd);
        stk.push(first);
        return null;
    }
}

