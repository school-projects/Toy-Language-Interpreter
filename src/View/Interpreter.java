package View;

import Controller.Controller;
import Domain.ADT.*;
import Domain.Expressions.*;
import Domain.PrgState;
import Domain.Statements.*;
import Repo.IRepo;
import Repo.Repo;
import View.Commands.ExitCommand;
import View.Commands.RunCommand;

import java.io.BufferedReader;


class Interpreter {
        public static void main(String[] args) {
/*
                IStmt ex5 = new CompStmt(
                        new CompStmt(new OpenRFStmt("fname","test.in"),
                                new ReadFileStmt(new VarExp("fname"),"val")),
                        new CompStmt(
                                new PrintStmt(new VarExp("val")),
                                new CompStmt(new IfStmt(new VarExp("val"),
                                        new CompStmt(new ReadFileStmt(new VarExp("fname"),"val"),new PrintStmt(new VarExp("val"))),
                                        new PrintStmt(new ConstExp(0))),
                                        new CloseRFileStmt(new VarExp("fname")))));

                PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap(), ex5);
                Repo repo5 = new Repo();
                repo5.addPrg(prg5);
                Controller ctr5 = new Controller(repo5);

                IStmt ex2 = new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(
                        new AssignStmt("v", new ConstExp(10)),
                        new NewStmt("v", new ConstExp(20))),
                        new NewStmt("a", new ConstExp(22))),
                        new HeapWriteStmt(new VarExp("a"), new ConstExp(30))),
                        new PrintStmt(new VarExp("a"))),
                        new PrintStmt(new HeapReadExp("a"))),
                        new AssignStmt("a", new ConstExp(0))
                );

                PrgState prg2 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap(), ex2);
                Repo repo2 = new Repo();
                repo2.addPrg(prg2);
                Controller ctr2 = new Controller(repo2);

                IStmt ex3 = new CompStmt(new CompStmt(
                        new AssignStmt("v", new ConstExp(6)),
                        new WhileStmt(new CompExp(">", new VarExp("v"), new ConstExp(4)),
                                new CompStmt(
                                new PrintStmt(new VarExp("v")),
                                new AssignStmt("v", new ArithExp('-',new VarExp("v"),new ConstExp(1)))))),
                        new PrintStmt(new VarExp("v")));

                PrgState prg3 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap(), ex3);
                Repo repo3 = new Repo();
                repo3.addPrg(prg3);
                Controller ctr3 = new Controller(repo3);

                IStmt ex4 = new CompStmt(
                        new CompStmt(
                                new CompStmt(
                                        new CompStmt(new AssignStmt("v", new ConstExp(10)),
                            new NewStmt("a", new ConstExp(30))),
                            new ForkStmt(new CompStmt(new CompStmt(new CompStmt(new HeapWriteStmt(new VarExp("a"),new ConstExp(30)), new AssignStmt("v", new ConstExp(32))),new PrintStmt(new VarExp("v"))),new PrintStmt(new HeapReadExp("a")))))
                           ,new PrintStmt(new VarExp("v")))
                           ,new PrintStmt(new HeapReadExp("a")));

                PrgState prg4 = new PrgState(new MyStack<IStmt>(), new MyDict<String, Integer>(), new MyList<Integer>(), new MyFileTable(), new MyHeap(), ex4);
                Repo repo4 = new Repo();
                repo4.addPrg(prg4);
                System.out.println(repo4.getPrgLst().getContent().size());
                Controller ctr4 = new Controller(repo4);


                TextMenu menu = new TextMenu();
                menu.addCommand(new ExitCommand("0", "exit"));
                menu.addCommand(new RunCommand("1","A2", ctr5));
                menu.addCommand(new RunCommand("2", "A3",ctr2));
                menu.addCommand(new RunCommand("3", "A4", ctr3));
                menu.addCommand(new RunCommand("4", "A5", ctr4));
                menu.show();
        */
        }


}
