package Domain;

import Domain.ADT.*;
import Domain.Exception.ADT.KeyNotFoundExc;
import Domain.Exception.Statements.StmtExc;
import Domain.Statements.IStmt;

import java.io.BufferedReader;

public class PrgState {
    IMyStack<IStmt> exeStack;
    IMyDict<String, Integer> sTable;
    IMyFileTable fileTable;
    IMyList<Integer> out;
    IMyHeap heap;



    private IMyLatchTable latchTable;

    public PrgState(IStmt oProg) {
        this.exeStack = new MyStack<>();
        this.sTable = new MyDict<>();
        this.fileTable = new MyFileTable();
        this.out = new MyList<>();
        this.heap = new MyHeap();
        this.id = 1;
        this.originalProg = oProg;
        this.latchTable = new MyLatchTable();

        exeStack.push(oProg);
    }

    public PrgState(IMyStack<IStmt> exeStack, IMyDict<String, Integer> sTable, IMyFileTable fileTable, IMyList<Integer> out, IMyHeap heap, IMyLatchTable latchTable, Integer id, IStmt originalProg) {
        this.exeStack = exeStack;
        this.sTable = sTable;
        this.fileTable = fileTable;
        this.out = out;
        this.heap = heap;
        this.latchTable = latchTable;
        this.id = id;
        this.originalProg = originalProg;

        exeStack.push(originalProg);
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    Integer id;

    public IMyHeap getHeap() {
        return heap;
    }

    public void setHeap(IMyHeap heap) {
        this.heap = heap;
    }

    IStmt originalProg;

    public PrgState oneStep() throws KeyNotFoundExc, StmtExc {
        if(exeStack.isEmpty())
            throw(new KeyNotFoundExc("One Step: stack empty!"));
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }



    public IMyFileTable getFileTable() {
        return fileTable;
    }

    public IMyLatchTable getLatchTable() {
        return latchTable;
    }

    public Boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public IMyStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(IMyStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public IMyDict<String, Integer> getsTable() {
        return sTable;
    }

    public void setsTable(IMyDict<String, Integer> sTable) {
        this.sTable = sTable;
    }

    public IMyList<Integer> getOut() {
        return out;
    }

    public void setOut(IMyList<Integer> out) {
        this.out = out;
    }

    public IStmt getOriginalProg() {
        return originalProg;
    }

    public void setOriginalProg(IStmt originalProg) {
        this.originalProg = originalProg;
    }

    public String toString()
    {
        return "\n--------------------\nPrgState:\n ID: "+ id.toString() +" \nExeStack:\n" + exeStack.toString() + "\nsTable:\n"+ sTable.toString()  + "\nOut:\n" + out.toString() + "\nFileTable:\n" + fileTable.toString()+ "\nHeap:\n" + heap.toString();
    }
}
