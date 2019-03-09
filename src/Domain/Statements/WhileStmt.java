package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.ADT.IMyStack;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Statements.InvalidExpExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.IExp;
import Domain.PrgState;

public class WhileStmt implements IStmt {

    private IExp exp;

    public WhileStmt(IExp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    private IStmt stmt;

    @Override
    public PrgState execute(PrgState state) throws StmtExc {
        IMyDict<String, Integer> sT = state.getsTable();
        IMyHeap heap = state.getHeap();
        IMyStack<IStmt> stk = state.getExeStack();

        try {
            if(exp.eval(sT,heap) != 0){
                stk.push(this);
                stk.push(stmt);
            }
        } catch (ExpExc expExc) {
            throw new InvalidExpExc("while: invalid expression");
        }

        return null;
    }

    @Override
    public String toString(){
        return "while " + exp.toString() + ": " + stmt.toString();
    }
}
