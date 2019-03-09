package Domain.Expressions;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.Exception.ADT.ADTExc;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Expressions.UnknownVariableNameExc;

public class HeapReadExp implements IExp {

    private String varname;

    public HeapReadExp(String varname) {
        this.varname = varname;
    }

    @Override
    public int eval(IMyDict<String, Integer> sTable, IMyHeap heap) throws ExpExc {
        try {
            return heap.lookup(sTable.lookup(varname));
        } catch (ADTExc adtExc) {
            throw new UnknownVariableNameExc("Invalid variable name!");
        }
    }

    @Override
    public String toString(){
        return "HeapRead " + varname;
    }
}
