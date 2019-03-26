/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

/**
 *
 * @author Sanja
 */
public class KandidatModel {
    
    private int id;
    private String kandidat;

    public KandidatModel(int id, String kandidat){
        this.id = id;
        this.kandidat = kandidat;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKandidat() {
        return kandidat;
    }

    public void setKandidat(String kandidat) {
        this.kandidat = kandidat;
    }
    
    
}
