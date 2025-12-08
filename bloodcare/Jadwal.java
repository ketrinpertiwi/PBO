package com.bloodcare.bloodcare;
import java.sql.Date;

public class Jadwal {
    private int id;
    private String lokasi;
    private Date tanggal;
    private String jamMulai;
    private String jamSelesai;

    public Jadwal() {}
    public Jadwal(int id, String lokasi, Date tanggal, String jamMulai, String jamSelesai) {
        this.id = id; this.lokasi = lokasi; this.tanggal = tanggal; 
        this.jamMulai = jamMulai; this.jamSelesai = jamSelesai;
    }
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getLokasi() { return lokasi; } public void setLokasi(String lokasi) { this.lokasi = lokasi; }
    public Date getTanggal() { return tanggal; } public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
    public String getJamMulai() { return jamMulai; } public void setJamMulai(String jamMulai) { this.jamMulai = jamMulai; }
    public String getJamSelesai() { return jamSelesai; } public void setJamSelesai(String jamSelesai) { this.jamSelesai = jamSelesai; }
}

