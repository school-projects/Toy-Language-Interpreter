package Controller;

import Domain.ADT.*;
import Domain.Exception.ADT.ADTExc;
import Domain.Exception.Statements.StmtExc;
import Domain.PrgState;
import Repo.IRepo;
import Repo.Repo;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    private IRepo repo;
    private ExecutorService executor;

    public Controller() {
        this.repo = new Repo();
        executor = Executors.newFixedThreadPool(2);}

    public Controller(IRepo repo) {
        this.repo = repo;
        executor = Executors.newFixedThreadPool(2);
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

    public Boolean oneStepForAll() throws InterruptedException {
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgLst().getContent());
        if(prgList.size() > 0) {

            //Garbage Collector
            repo.getPrgLst().get(0).getHeap().setContent(conservativeGarbageCollector(
                    repo.getPrgLst().get(0).getsTable().getContent().values(),
                    repo.getPrgLst().get(0).getHeap().getContent()));

            //Actual Step
            prgList.forEach(prg -> repo.logPrgState(prg));

            List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (() -> {
                return p.oneStep();
            })).collect(Collectors.toList());

            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            return null;
                        }
                    }).filter(p -> p != null).collect(Collectors.toList());

            prgList.addAll(newPrgList);
            prgList.forEach(prg -> repo.logPrgState(prg));
            repo.setPrgLst(new MyList(prgList));
            return true;
        }
        else {
            executor.shutdownNow();
            executor = Executors.newFixedThreadPool(2);

            closeFillesGarbageCollector(repo.getPrgLst().get(0));

            repo.setPrgLst(new MyList<PrgState> (prgList));
            return false;
        }
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
        Boolean yes = true;
        while(yes){
           yes = oneStepForAll();
       }
    }

    public PrgState getById(Integer i) {
        for(PrgState p : repo.getPrgLst().getContent())
            if(p.getID().equals(i)) {
                return p;
            }
        return null;
    }
    public List<PrgState> getPrgList(){
        return repo.getPrgLst().getContent();
    }
}
