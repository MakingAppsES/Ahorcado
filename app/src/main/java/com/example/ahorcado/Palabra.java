package com.example.ahorcado;



public class Palabra {

    private String espaniol;
    private String ingles;
    private int dificultad;

    public Palabra(String espaniol, String ingles, int dificultad) {
        this.espaniol = espaniol;
        this.ingles = ingles;
        this.dificultad = dificultad;
    }

    public String getEspaniol() {
        return espaniol;
    }

    public String getIngles() {
        return ingles;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setEspaniol(String espaniol) {
        this.espaniol = espaniol;
    }

    public void setIngles(String ingles) {
        this.ingles = ingles;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
