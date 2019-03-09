package Domain.Expressions;
import Domain.ADT.*;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.MyExc;

public interface IExp {
    int eval(IMyDict<String,Integer> sTable, IMyHeap heap) throws ExpExc;
    String toString();
}

