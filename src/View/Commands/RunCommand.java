package View.Commands;

import Controller.Controller;
import Domain.Exception.MyExc;

public class RunCommand extends Command {
    private Controller ctr;
    public RunCommand(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() throws MyExc {
        try {
            ctr.allStep();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}