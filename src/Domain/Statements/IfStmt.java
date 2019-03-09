package Domain.Statements;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.ADT.IMyStack;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.MyExc;
import Domain.Exception.Statements.InvalidExpExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.IExp;
import Domain.PrgState;

public class IfStmt implements IStmt {

    private IExp exp;
    private IStmt thenS, elseS;

    public IfStmt() {
    }

    public IfStmt(IExp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public String toString(){
        return "If " + exp.toString() + " Then " + thenS.toString() + " Else " + elseS.toString();
    }


    @Override
    public PrgState execute(PrgState state) throws StmtExc {

        IMyStack<IStmt> stk = state.getExeStack();
        IMyDict<String, Integer> sTab = state.getsTable();
        IMyHeap heap = state.getHeap();

        try {
            if(exp.eval(sTab,heap) != 0)
                stk.push(thenS);
            else
                stk.push(elseS);
        } catch (ExpExc expExc) {
            throw new InvalidExpExc("Invalid expression: " + exp.toString());
        }

        return null;
    }
}
