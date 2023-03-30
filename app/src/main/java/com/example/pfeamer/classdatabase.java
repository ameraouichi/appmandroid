package com.example.pfeamer;

public class classdatabase {
    private  String userid;
    private  String nom;
    private  String prenom;
    private  String email;
    private  String password;
    private  String type;

    public classdatabase(String userid, String nom, String prenom, String email, String password, String type) {
        this.userid = userid;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public classdatabase() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "classdatabase{" +
                "userid='" + userid + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
