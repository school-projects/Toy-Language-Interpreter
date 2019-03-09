package Domain.Expressions;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.Exception.Expressions.ExpExc;

public class NotExp implements IExp{

    public NotExp(IExp exp) {
        this.exp = exp;
    }

    private IExp exp;

    @Override
    public int eval(IMyDict<String, Integer> sTable, IMyHeap heap) throws ExpExc {
        if(exp.eval(sTable,heap) == 0)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString(){
            return "Not(" + exp.toString() + ")";
    }
}
