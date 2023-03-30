package com.example.pfeamer;

public class modelprofile {
    private String pimg, pnom,pprenom,paddrs,pnai,ptel,psexe;

    public modelprofile(String trim, String s) {
    }

    public modelprofile(String pimg, String pnom, String pprenom, String paddrs, String pnai, String ptel, String psexe) {
        this.pimg = pimg;
        this.pnom = pnom;
        this.pprenom = pprenom;
        this.paddrs = paddrs;
        this.ptel = ptel;
        this.pnai = pnai;
        this.psexe = psexe;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public String getPnom() {
        return pnom;
    }

    public void setPnom(String pnom) {
        this.pnom = pnom;
    }

    public String getPprenom() {
        return pprenom;
    }

    public void setPprenom(String pprenom) {
        this.pprenom = pprenom;
    }

    public String getPaddrs() {
        return paddrs;
    }

    public void setPaddrs(String paddrs) {
        this.paddrs = paddrs;
    }

    public String getPnai() {
        return pnai;
    }

    public void setPnai(String pnai) {
        this.pnai = pnai;
    }

    public String getPtel() {
        return ptel;
    }

    public void setPtel(String ptel) {
        this.ptel = ptel;
    }

    public String getPsexe() {
        return psexe;
    }

    public void setPsexe(String psexe) {
        this.psexe = psexe;
    }

    @Override
    public String toString() {
        return "modelprofile{" +
                "pimg='" + pimg + '\'' +
                ", pnom='" + pnom + '\'' +
                ", pprenom='" + pprenom + '\'' +
                ", paddrs='" + paddrs + '\'' +
                ", pnai='" + pnai + '\'' +
                ", ptel='" + ptel + '\'' +
                ", psexe='" + psexe + '\'' +
                '}';
    }
}
