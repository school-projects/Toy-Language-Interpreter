package Repo;

import Domain.ADT.IMyList;
import Domain.PrgState;

public interface IRepo {
    void addPrg(PrgState state);
    IMyList<PrgState> getPrgLst();
    void setPrgLst(IMyList<PrgState> l);
    void logPrgState(PrgState prg);
}
