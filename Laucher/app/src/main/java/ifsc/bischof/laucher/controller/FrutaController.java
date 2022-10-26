package ifsc.bischof.laucher.controller;

import ifsc.bischof.laucher.model.Fruta;
import ifsc.bischof.laucher.model.Frutas;

import java.util.ArrayList;

public class FrutaController {
    Frutas frutasPersitence;
    public FrutaController() {
        frutasPersitence=new Frutas();
    }
    public Fruta[] getFrutas() {
        return frutasPersitence.FRUTAS;
    }
    public ArrayList<String>  getNomesFrutas(){
        ArrayList<String> nomesFrutras = new ArrayList<String>();
        for (Fruta fruta : this.getFrutas()) {
            nomesFrutras.add(fruta.getNome());
        }
        return nomesFrutras;
    }
}
