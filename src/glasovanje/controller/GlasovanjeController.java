/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.controller;

import glasovanje.model.GlasovanjeModel;
import java.sql.SQLException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Sanja
 */
public class GlasovanjeController {
    
    @FXML
    CheckBox izbor1;
    @FXML
    CheckBox izbor2;
    @FXML
    CheckBox izbor3;
    @FXML
    CheckBox izbor4;
    @FXML
    CheckBox izbor5;
    @FXML
    CheckBox izbor6;
    
    @FXML
    Button pohrani;
    @FXML
    Button odjava;
    
    String glas;
    String pohraniGlas;

    Boolean flag = false;
    
    //Interface -> A set that allows observers to track changes when they occur
    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();

    //Binding calculates a value that depends on one or more sources
    //binding observes its dependencies for changes and updates its value automatically
    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

    private final int maxNumSelected =  1; 
    
   
    public String initialize() {
        odjava.setVisible(false);
        
        if(configureCheckBox(izbor1)){
            glas = "Petar Sosa";
        }else if(configureCheckBox(izbor2)){
            glas = "Ivica Moro";
        }else if(configureCheckBox(izbor3)){
            glas = "Marica Moro";
        }else if( configureCheckBox(izbor4)){
            glas = "Stjepan Pezer";
        }else if( configureCheckBox(izbor5)){
            glas = "Marinko Vicko";
        }else if( configureCheckBox(izbor6)){
            glas = "Asvaltina Boto";
        }
        
        //ako nije nista odabrao onemoguci dugme za pohranu
        pohrani.setDisable(true);

        //dodavanje listener-a na svaki checkbox da pratim promjene
        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            //ako je broj odabranih veci ili jednak od max prodji kroz
            //sve neodabrane i onemoguci ih
            //onemoguci dugme
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
                pohrani.setDisable(false);
            } else {
             //inace za svaki checbox omoguci 
             //omoguci dugme
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
                pohrani.setDisable(true);
            }
        });
        
      return glas;
    }
    //provjerava za svaki checkbox je li odabran
    private Boolean configureCheckBox(CheckBox checkBox) {
        
        //ako je odabran dodaj ga u collection odabranih
        if (checkBox.isSelected()) {
            selectedCheckBoxes.add(checkBox);
            flag = true;
        } else {
        //inace collection  neodabranih       
            unselectedCheckBoxes.add(checkBox);
            flag = false;
        }

        //listener ako se promjeni stanje nekog od checkbox - pr. ako neki checkbox
        //nije odabran, pa ga odabere
        //moramo ga izbaciti is collection neodabranih i dodati u collection odabranih
        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedCheckBoxes.remove(checkBox);
                selectedCheckBoxes.add(checkBox);
                flag = true;
            } else {
        //inace ako je odabran pa ponisti odabir
        //izbacimo ga iz collection odabranih
        //ubacimo u collection neodabranih
                selectedCheckBoxes.remove(checkBox);
                unselectedCheckBoxes.add(checkBox);
                flag = false;
            }

        });

        return flag;
    }
    
     
    @FXML
    public void glasovao() throws SQLException{
      
      pohraniGlas = initialize();
      GlasovanjeModel noviGlas = new GlasovanjeModel(pohraniGlas);
      noviGlas.pohraniGlas();
      
      odjava.setVisible(true);
    }
    
    @FXML
    public void odjaviSe(){
        pohrani.getScene().getWindow().hide();
    }
    
    }
    
 
