package com.company;

class Boek{
    private String titel;
    private String auteur;
    private String beoordeling;

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setBeoordeling(String beoordeling) {
        this.beoordeling = beoordeling;
    }

    @Override
    public String toString() {
        return "Boek{" +
                "titel='" + titel + '\'' +
                '}';
    }

}
