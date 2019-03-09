package Domain.Expressions;

import Domain.ADT.IMyDict;
import Domain.ADT.IMyHeap;
import Domain.Exception.ADT.ADTExc;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Expressions.UnknownVariableNameExc;
import Domain.Exception.MyExc;

public class VarExp implements IExp
{
    private String id;

    public VarExp() {}

    public VarExp(String id)
    {
        this.id = id;
    }

    @Override
    public int eval(IMyDict<String, Integer> sTable, IMyHeap heap) throws ExpExc {
        try {
            return sTable.lookup(id);
        } catch (ADTExc myExc) {
            throw new UnknownVariableNameExc("Unknown variable name: " + id);
        }
    }

    @Override
    public String toString() {
        return id;
    }
}
