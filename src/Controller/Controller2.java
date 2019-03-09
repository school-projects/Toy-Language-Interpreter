package Controller;
/*
import Domain.ADT.*;
import Domain.Exception.ADT.ADTExc;
import Domain.Exception.ADT.KeyNotFoundExc;
import Domain.Exception.MyExc;
import Domain.Exception.Statements.StmtExc;
import Domain.PrgState;
import Domain.Statements.IStmt;
import Repo.IRepo;
import Repo.Repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.prefs.PreferencesFactory;
import java.util.stream.Collectors;

public class Controller2 {

    private IRepo repo;
    private ExecutorService executor;

    public Controller2() {this.repo = new Repo();}

    public Controller2(IRepo repo) {
        this.repo = repo;
    }

    public void addPrgState(PrgState state)
    {
        repo.addPrg(state);
    }

    List<PrgState> removeCompletedPrg(List<PrgState> lst)
    {
        return lst.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
        return heap.entrySet().stream().filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    void oneStepForAll(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg->repo.logPrgState(prg));

        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>)(()->{return p.oneStep();})).collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future->{try{return future.get();} catch(Exception e){return null;}}).filter(p->p!=null).collect(Collectors.toList());

        prgList.addAll(newPrgList);
        prgList.forEach(prg->repo.logPrgState(prg));
        repo.setPrgLst(new MyList(prgList));

    }

    public PrgState closeFillesGarbageCollector(PrgState prg)
    {
        IMyFileTable fileTable = prg.getFileTable();

        fileTable.getContent().getContent().values().stream().flatMap(pair ->
        {
            try {
                pair.getSecond().close();
            } catch (IOException e) {
                System.out.println("Some errors occured when closing the files!");
            }
            return null;
        });
        for(Integer k:fileTable.getContent().getContent().keySet())
        {
            try {
                fileTable.remove(k);
            } catch (ADTExc adtExc) {
                adtExc.printStackTrace();
            }
        }
        return prg;
    }

    public void allStep() throws StmtExc, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgLst().getContent());
        while(prgList.size() > 0){

            repo.getPrgLst().get(0).getHeap().setContent(conservativeGarbageCollector(
                    repo.getPrgLst().get(0).getsTable().getContent().values(),
                    repo.getPrgLst().get(0).getHeap().getContent()));

            oneStepForAll(prgList);

            prgList = removeCompletedPrg(repo.getPrgLst().getContent());

        }
        executor.shutdownNow();

        closeFillesGarbageCollector(repo.getPrgLst().get(0));

        repo.setPrgLst(new MyList<PrgState> (prgList));

    }
}
*/