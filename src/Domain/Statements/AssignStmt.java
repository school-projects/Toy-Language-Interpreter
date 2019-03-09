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

public class AssignStmt implements IStmt {

    private String id;
    private IExp exp;

    public AssignStmt() {
    }

    public AssignStmt(String id, IExp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString(){

        return id +" = "+ exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws StmtExc {
        IMyDict<String, Integer> sTab = state.getsTable();
        IMyHeap heap = state.getHeap();
        int val = 0;
        try {
            val = exp.eval(sTab,heap);
        } catch (ExpExc expExc) {
            throw new InvalidExpExc("Invalid Expression: " + exp.toString());
        }
        if(sTab.exists(id))
            sTab.update(id,val);
        else
            sTab.add(id,val);
        return null;
    }

}
