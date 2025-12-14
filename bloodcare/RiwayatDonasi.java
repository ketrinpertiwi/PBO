package com.bloodcare.bloodcare;

import java.sql.Date;

public class RiwayatDonasi {
    private int id;
    private int pendonorId;
    private String namaPendonor;
    private Date tanggalDonor;
    private String jenisDarah;
    private String golDarah;
    private String rhesus;
    private int volumeMl;

    public RiwayatDonasi() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPendonorId() { return pendonorId; }
    public void setPendonorId(int pendonorId) { this.pendonorId = pendonorId; }

    public String getNamaPendonor() { return namaPendonor; }
    public void setNamaPendonor(String namaPendonor) { this.namaPendonor = namaPendonor; }

    public Date getTanggalDonor() { return tanggalDonor; }
    public void setTanggalDonor(Date tanggalDonor) { this.tanggalDonor = tanggalDonor; }

    public String getJenisDarah() { return jenisDarah; }
    public void setJenisDarah(String jenisDarah) { this.jenisDarah = jenisDarah; }

    public String getGolDarah() { return golDarah; }
    public void setGolDarah(String golDarah) { this.golDarah = golDarah; }

    public String getRhesus() { return rhesus; }
    public void setRhesus(String rhesus) { this.rhesus = rhesus; }

    public int getVolumeMl() { return volumeMl; }
    public void setVolumeMl(int volumeMl) { this.volumeMl = volumeMl; }
}

