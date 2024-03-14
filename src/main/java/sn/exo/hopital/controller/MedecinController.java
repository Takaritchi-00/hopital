package sn.exo.hopital.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sn.exo.hopital.entities.Medecin;

import javax.persistence.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MedecinController implements Initializable {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnValider;

    @FXML
    private TableColumn<Medecin, String> colGrade;

    @FXML
    private TableColumn<Medecin, Integer> colID;

    @FXML
    private TableColumn<Medecin, String> colNom;

    @FXML
    private TableColumn<Medecin, String> colPrenom;

    @FXML
    private TableColumn<Medecin, Integer> colSalaire;

    @FXML
    private TextField tfdGrade;

    @FXML
    private TextField tfdNom;

    @FXML
    private TextField tfdPrenom;

    @FXML
    private TextField tfdSalaire;

    @FXML
    private TableView<Medecin> tviewMedecin;
    int id;


    @FXML
    void ClearFunction(ActionEvent event) {

    }

    @FXML
    void DeleteFunction(ActionEvent event) {

    }

    @FXML
    void RefreshFunction(ActionEvent event) {

    }

    @FXML
    void SaveFunction(ActionEvent event) {
        try{
            et.begin();
            Medecin medecin = new Medecin();
            medecin.setNom(tfdNom.getText());
            medecin.setPrenom(tfdPrenom.getText());
            medecin.setGrade(tfdGrade.getText());
            medecin.setSalaire(Integer.parseInt(tfdSalaire.getText()));
            em.persist(medecin);
            et.commit();
            loadTable();

        }finally {
            if (et.isActive())
                et.rollback();
        }

    }

    @FXML
    void ValiderFunction(ActionEvent event) {

    }

    @FXML
    void getData(MouseEvent event) {
        Medecin medecin = tviewMedecin.getSelectionModel().getSelectedItem();
        id = medecin.getId();
        tfdNom.setText(medecin.getNom());
        tfdPrenom.setText(medecin.getPrenom());
        tfdGrade.setText(medecin.getGrade());
        tfdSalaire.setText(String.valueOf(medecin.getSalaire()));
        btnSave.setDisable(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadTable(){
        tviewMedecin.setItems(getMedecins());
        colID.setCellValueFactory(new PropertyValueFactory<Medecin, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Medecin, String>("Nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Medecin, String>("Prenom"));
        colGrade.setCellValueFactory(new PropertyValueFactory<Medecin, String>("Grade"));
        colSalaire.setCellValueFactory(new PropertyValueFactory<Medecin, Integer>("Salaire"));
    }

    public ObservableList<Medecin> getMedecins(){
        ObservableList<Medecin> medecins = FXCollections.observableArrayList();
        TypedQuery<Medecin> query = em.createNamedQuery("listMedecins", Medecin.class);
        medecins.addAll(query.getResultList());
        return medecins;
    }
}
