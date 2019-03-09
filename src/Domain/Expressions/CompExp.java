package Domain.Expressions;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.Exception.Expressions.DivisionByZeroExc;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Expressions.UnknownOperationExc;

public class CompExp implements IExp {

    String op;
    IExp e1,e2;

    public CompExp() {}

    public CompExp(String op, IExp e1, IExp e2)
    {
        this.op = op;
        this. e2 = e2;
        this.e1 = e1;
    }

    @Override
    public int eval(IMyDict<String, Integer> sTable, IMyHeap heap) throws ExpExc {
        switch(op)
        {
            case "<":
                return (e1.eval(sTable, heap) < e2.eval(sTable, heap)) ? 1 : 0;
            case "<=":
                return (e1.eval(sTable, heap) <= e2.eval(sTable, heap)) ? 1 : 0;
            case "==":
                return (e1.eval(sTable, heap) == e2.eval(sTable, heap)) ? 1 : 0;
            case "!=":
                return (e1.eval(sTable, heap) != e2.eval(sTable, heap)) ? 1 : 0;
            case ">":
                return (e1.eval(sTable, heap) > e2.eval(sTable, heap)) ? 1 : 0;
            case ">=":
                return (e1.eval(sTable, heap) >= e2.eval(sTable, heap)) ? 1 : 0;
            default:
                throw(new UnknownOperationExc("CompExp: Unknown operation!"));

        }
    }

    @Override
    public String toString() {

        return e1.toString()+op+e2.toString();
    }

}
