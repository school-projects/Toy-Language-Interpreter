package Tests;

import Controller.Controller;
import Domain.ADT.*;
import Domain.Exception.ADT.ADTExc;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.ConstExp;
import Domain.Expressions.HeapReadExp;
import Domain.Expressions.VarExp;
import Domain.PrgState;
import Domain.Statements.*;
import Repo.Repo;
import org.junit.Test;

public class ControllerTester {

    @Test
    public void TestGarbageCollector(){
        IStmt ex5 = new CompStmt(new CompStmt(
                new NewStmt("a",new ConstExp(3)),
                new HeapWriteStmt(new VarExp("a"), new ConstExp(29))),
                new AssignStmt("a", new ConstExp(200)));

        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap() ,ex5);
        Repo repo5 = new Repo();
        repo5.addPrg(prg5);
        Controller ctr5 = new Controller(repo5);

        try {
            ctr5.allStep();
            prg5.getHeap().lookup(1);
            assert(false);
        } catch (StmtExc stmtExc) {
            assert(false);
        } catch (ADTExc adtExc) {
            assert(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
