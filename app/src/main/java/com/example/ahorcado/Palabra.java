package com.example.ahorcado;



public class Palabra {

    private String espaniol;
    private String ingles;
    private int dificultad;

    public Palabra(String espaniol, String ingles, int dificultad) {
        this.espaniol = espaniol.toLowerCase();
        this.ingles = ingles.toLowerCase();
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
        this.espaniol = espaniol.toLowerCase();
    }

    public void setIngles(String ingles) {
        this.ingles = ingles.toLowerCase();
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public String palabraToGuiones() {
        String oculta = "";

        for (int i = 0; i < ingles.length(); i++) {
            oculta += (ingles.charAt(i) != ' ') ? "-" : " ";
        }

        return oculta;
    }
}
