package com.bloodcare.bloodcare;
import java.sql.Date;

public class Permintaan {
    private int id;
    private String namaRS;
    private String golDarah;
    private String rhesus;
    private int jumlah;
    private Date tanggal;
    private String status;

    public Permintaan() {}
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getNamaRS() { return namaRS; } public void setNamaRS(String namaRS) { this.namaRS = namaRS; }
    public String getGolDarah() { return golDarah; } public void setGolDarah(String golDarah) { this.golDarah = golDarah; }
    public String getRhesus() { return rhesus; } public void setRhesus(String rhesus) { this.rhesus = rhesus; }
    public int getJumlah() { return jumlah; } public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public Date getTanggal() { return tanggal; } public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
    public String getStatus() { return status; } public void setStatus(String status) { this.status = status; }
}


