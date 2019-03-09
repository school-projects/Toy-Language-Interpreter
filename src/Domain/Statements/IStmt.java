package Domain.Statements;

import Domain.Exception.MyExc;
import Domain.Exception.Statements.StmtExc;
import Domain.PrgState;

public interface IStmt {
    String toString();
    PrgState execute(PrgState state) throws StmtExc;
}
