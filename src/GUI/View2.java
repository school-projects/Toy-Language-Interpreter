package GUI;

import Controller.Controller;
import Domain.ADT.MyPair;
import Domain.Exception.Statements.StmtExc;
import Domain.PrgState;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class View2 {

    private Controller ctrl;

    public void setCtrl(Controller ctrl) {
        this.ctrl = ctrl;
    }

    private Integer currentState = 1;

    @FXML
    private VBox dad;

    @FXML
    private TextField prgCount;

    @FXML
    private ListView prgView;

    @FXML
    private TableView<MyPair<Integer,Integer>> heapView;

    @FXML
    private TableColumn<MyPair<Integer,Integer>,Integer> hAddress;

    @FXML
    private TableColumn<MyPair<Integer,Integer>,Integer> hValue;

    @FXML
    private TableView<MyPair<Integer,String>> fileTableView;

    @FXML
    private TableColumn<MyPair<Integer,String>, Integer> fID;

    @FXML
    private TableColumn<MyPair<Integer,String>, String> fName;

    @FXML
    private TableView<MyPair<String,Integer>> sTableView;

    @FXML
    private TableColumn<MyPair<String,Integer>, String> sName;

    @FXML
    private TableColumn<MyPair<String,Integer>, Integer> sValue;

    @FXML
    private ListView outView;

    @FXML
    private ListView exeStackView;



    @FXML
    private void initialize(){

        hAddress.setCellValueFactory(new PropertyValueFactory<MyPair<Integer,Integer>, Integer>("first"));
        hValue.setCellValueFactory(new PropertyValueFactory<MyPair<Integer,Integer>,Integer>("second"));


        sName.setCellValueFactory(new PropertyValueFactory<MyPair<String,Integer>, String>("first"));
        sValue.setCellValueFactory(new PropertyValueFactory<MyPair<String,Integer>,Integer>("second"));

        fID.setCellValueFactory(new PropertyValueFactory<MyPair<Integer,String>,Integer >("first"));
        fName.setCellValueFactory(new PropertyValueFactory<MyPair<Integer,String>,String>("second"));

    }

    private void refresh(){

        prgView.getItems().clear();
        for(PrgState p : ctrl.getPrgList())
            prgView.getItems().add(p.getID());

        prgCount.setText(String.valueOf(ctrl.getPrgList().size()));

        if(ctrl.getById(currentState) == null) {
            if (ctrl.getPrgList().size() == 0) {
                heapView.getItems().clear();
                sTableView.getItems().clear();
                fileTableView.getItems().clear();
                exeStackView.getItems().clear();
            }
            return;
        }

        heapView.getItems().clear();
        heapView.getItems().setAll(ctrl.getById(currentState).getHeap().getEntrySet());

        sTableView.getItems().clear();
        sTableView.getItems().setAll(ctrl.getById(currentState).getsTable().getEntrySet());

        fileTableView.getItems().clear();
        fileTableView.getItems().setAll(ctrl.getById(currentState).getFileTable().getEntrySet());

        outView.getItems().clear();
        outView.getItems().addAll(ctrl.getById(currentState).getOut().getContent().toArray());

        exeStackView.getItems().clear();
        exeStackView.getItems().addAll(ctrl.getById(currentState).getExeStack().getContent().toArray());





    }

    @FXML
    private void oneStepButPressed(ActionEvent e){
        try {
            ctrl.oneStepForAll();
            refresh();
        } catch (Exception ex) {
           ex.printStackTrace();
        }


    }



    @FXML
    private void listClicked(){
        currentState = (Integer) prgView.getSelectionModel().getSelectedItem();
        refresh();
    }
}
