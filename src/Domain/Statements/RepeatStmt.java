package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.ADT.IMyStack;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Statements.InvalidExpExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.IExp;
import Domain.Expressions.NotExp;
import Domain.PrgState;

public class RepeatStmt implements IStmt{
    private IExp exp;

    public RepeatStmt(IExp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    private IStmt stmt;

    @Override
    public PrgState execute(PrgState state) throws StmtExc {
        IMyStack<IStmt> stk = state.getExeStack();

        stk.push(new CompStmt(stmt, new WhileStmt(new NotExp(exp), stmt)));

        return null;
    }

    @Override
    public String toString(){
        return "Repeat " + stmt.toString()  + " until " + exp.toString();
    }
}
