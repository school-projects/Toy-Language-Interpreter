package GUI;

import Controller.Controller;
import Domain.ADT.*;
import Domain.Expressions.*;
import Domain.PrgState;
import Domain.Statements.*;
import Repo.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class ViewCtrl {

    private  ObservableList<IStmt> prgs= FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        prgs.add(new CompStmt(
                new CompStmt(new OpenRFStmt("fname","test.in"),
                        new ReadFileStmt(new VarExp("fname"),"val")),
                new CompStmt(
                        new PrintStmt(new VarExp("val")),
                        new CompStmt(new IfStmt(new VarExp("val"),
                                new CompStmt(new ReadFileStmt(new VarExp("fname"),"val"),new PrintStmt(new VarExp("val"))),
                                new PrintStmt(new ConstExp(0))),
                                new CloseRFileStmt(new VarExp("fname"))))));

        prgs.add(new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(
                new AssignStmt("v", new ConstExp(10)),
                new NewStmt("v", new ConstExp(20))),
                new NewStmt("a", new ConstExp(22))),
                new HeapWriteStmt(new VarExp("a"), new ConstExp(30))),
                new PrintStmt(new VarExp("a"))),
                new PrintStmt(new HeapReadExp("a"))),
                new AssignStmt("a", new ConstExp(0))
        ));

        prgs.add(new CompStmt(new CompStmt(
                new AssignStmt("v", new ConstExp(6)),
                new WhileStmt(new CompExp(">", new VarExp("v"), new ConstExp(4)),
                        new CompStmt(
                                new PrintStmt(new VarExp("v")),
                                new AssignStmt("v", new ArithExp('-',new VarExp("v"),new ConstExp(1)))))),
                new PrintStmt(new VarExp("v"))));

        prgs.add(new CompStmt(
                new CompStmt(
                        new CompStmt(
                                new CompStmt(new AssignStmt("v", new ConstExp(10)),
                                        new NewStmt("a", new ConstExp(30))),
                                new ForkStmt(new CompStmt(new CompStmt(new CompStmt(new HeapWriteStmt(new VarExp("a"),new ConstExp(30)), new AssignStmt("v", new ConstExp(32))),new PrintStmt(new VarExp("v"))),new PrintStmt(new HeapReadExp("a")))))
                        ,new PrintStmt(new VarExp("v")))
                ,new PrintStmt(new HeapReadExp("a"))));

        prgs.add(
                new CompStmt(
                 new CompStmt(
                         new CompStmt(
                                 new CompStmt(
                                         new CompStmt(
                                                 new CompStmt(
                                                         new CompStmt(
                                                                 new CompStmt(
                                                                         new NewStmt("v1", new ConstExp(2))
                                                                         ,new NewStmt("v2", new ConstExp(3))
                                                                 ),
                                                                 new NewStmt("v3", new ConstExp(4))
                                                         )
                                                         ,new NewLatchStmt("cnt", new HeapReadExp("v2"))
                                                 ),
                                                 new ForkStmt(
                                                         new CompStmt(
                                                                 new CompStmt(
                                                                         new CompStmt(
                                                                                 new HeapWriteStmt(new VarExp("v1"), new ArithExp('*',new HeapReadExp("v1"),new ConstExp(10)))
                                                                                 ,new PrintStmt(new HeapReadExp("v1"))
                                                                         )
                                                                         ,new CountDownStmt("cnt")
                                                                 )
                                                                 ,new ForkStmt(
                                                                 new CompStmt(
                                                                         new CompStmt(
                                                                                 new CompStmt(
                                                                                         new HeapWriteStmt(new VarExp("v2"), new ArithExp('*',new HeapReadExp("v2"),new ConstExp(10)))
                                                                                         ,new PrintStmt(new HeapReadExp("v2"))
                                                                                 )
                                                                                 ,new CountDownStmt("cnt")
                                                                         )
                                                                         ,new ForkStmt(
                                                                                 new CompStmt(
                                                                                         new CompStmt(
                                                                                                 new HeapWriteStmt(new VarExp("v3"), new ArithExp('*',new HeapReadExp("v3"),new ConstExp(10)))
                                                                                                 ,new PrintStmt(new HeapReadExp("v3"))
                                                                                         )
                                                                                         ,new CountDownStmt("cnt")
                                                                                 )
                                                                         )
                                                                 ))
                                                 ))
                                         ),
                                         new AwaitStmt("cnt")
                                 ),
                                 new PrintStmt(new ConstExp(100))
                         ),
                         new CountDownStmt("cnt")
                 )
                ,new PrintStmt(new ConstExp(100)))
        );

        prgList.setItems(prgs);
    }


    @FXML
    private ListView prgList;

    @FXML
    private void startButPressed(ActionEvent e) throws Exception
    {

        IStmt ex = (IStmt) prgList.getSelectionModel().getSelectedItem();
        PrgState prg = new PrgState(ex);
        Repo repo = new Repo();
        repo.addPrg(prg);
        Controller ctr = new Controller(repo);

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view2.fxml"));
        Parent root = loader.load();

        View2 c = loader.getController();

        c.setCtrl(ctr);

        stage.setTitle("");
        stage.setScene(new Scene(root));
        ((Node)e.getSource()).getScene().getWindow().hide();
        stage.show();

    }

}
