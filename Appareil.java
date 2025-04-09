package com.example.tp_poo_kennedy;

public class Appareil {
    private String type;
    private String numeroSerie;
    private String marque;

    public Appareil(String type, String numeroSerie, String marque) {
        this.type = type;
        this.numeroSerie = numeroSerie;
        this.marque = marque;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public String getMarque() {
        return marque;
    }

    public String getType() {
        return type;
    }
}
