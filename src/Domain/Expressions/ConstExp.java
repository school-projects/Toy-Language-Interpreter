package Domain.Expressions;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.MyExc;

public class ConstExp implements IExp{

    int nr;

    public ConstExp(){}

    public ConstExp(int nr)
    {
        this.nr = nr;
    }

    @Override
    public int eval(IMyDict<String, Integer> sTable, IMyHeap heap) throws ExpExc {
        return nr;
    }

    @Override
    public String toString() {
        return String.valueOf(nr);
    }
}
