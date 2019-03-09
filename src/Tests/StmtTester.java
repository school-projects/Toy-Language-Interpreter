package Tests;


import Controller.Controller;
import Domain.ADT.*;
import Domain.Exception.ADT.ADTExc;
import Domain.Exception.ADT.KeyNotFoundExc;
import Domain.Exception.Expressions.ExpExc;
import Domain.Exception.MyExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Expressions.*;
import Domain.PrgState;
import Domain.Statements.*;
import Repo.Repo;
import org.junit.Test;

public class StmtTester {

    @Test
    public void TestOpenRFStmt(){
        IStmt ex5 = new OpenRFStmt("marian","test.in");
        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap() ,ex5);
        Repo repo5 = new Repo();
        repo5.addPrg(prg5);
        Controller ctr5 = new Controller(repo5);

        try {
            ctr5.allStep();
        } catch (StmtExc stmtExc) {
            assert(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert(prg5.getsTable().exists("marian"));
    }

    @Test
    public void TestReadFileStmt(){
        IStmt ex5 = new CompStmt(new OpenRFStmt("marian","test.in"), new ReadFileStmt(new VarExp("marian"), "papi"));
        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap() ,ex5);
        Repo repo5 = new Repo();
        repo5.addPrg(prg5);
        Controller ctr5 = new Controller(repo5);

        try {
            ctr5.allStep();
            assert(prg5.getsTable().lookup("papi") == 15);
        } catch (MyExc stmtExc) {
            assert(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestCloseRFileStmt(){
        IStmt ex5 = new CompStmt(new OpenRFStmt("marian","test.in"), new CompStmt( new ReadFileStmt(new VarExp("marian"), "papi"), new CloseRFileStmt(new VarExp("marian"))));
        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap() ,ex5);
        Repo repo5 = new Repo();
        repo5.addPrg(prg5);
        Controller ctr5 = new Controller(repo5);

        try {
            ctr5.allStep();
            prg5.getFileTable().lookup(prg5.getsTable().lookup("marian"));
        } catch (KeyNotFoundExc stmtExc) {
            assert(true);
        }
        catch (MyExc exc)
        {
            assert(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestNewStmt(){
        IStmt ex5 = new NewStmt("a",new ConstExp(3));
        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap() ,ex5);
        Repo repo5 = new Repo();
        repo5.addPrg(prg5);
        Controller ctr5 = new Controller(repo5);

        try {
            ctr5.allStep();
            assert(prg5.getHeap().lookup(1) == 3);
        } catch (StmtExc | ADTExc stmtExc) {
            assert(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestHeapWriteStmt(){
        IStmt ex5 = new CompStmt(new NewStmt("a",new ConstExp(3)), new HeapWriteStmt(new VarExp("a"), new ConstExp(29)));
        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap() ,ex5);
        Repo repo5 = new Repo();
        repo5.addPrg(prg5);
        Controller ctr5 = new Controller(repo5);

        try {
            ctr5.allStep();
            assert(prg5.getHeap().lookup(1) == 29);
            assert(new HeapReadExp("a").eval(prg5.getsTable(), prg5.getHeap()) == 29);
        } catch (StmtExc | ADTExc | ExpExc stmtExc) {
            assert(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
    @Test
    public void TestWhileStmt(){
        IStmt ex5 = new CompStmt(new AssignStmt("v",new ConstExp(0)),WhileStmt(new CompExp("<", new VarExp("v"), new ConstExp(3)))),)
        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap() ,ex5);
        Repo repo5 = new Repo();
        repo5.addPrg(prg5);
        Controller ctr5 = new Controller(repo5);

    }

*/
}
