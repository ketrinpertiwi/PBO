package com.bloodcare.bloodcare;

import com.bloodcare.bloodcare.App;
import com.bloodcare.bloodcare.DAO.*; // Import semua DAO (Jadwal, Stok, Pendonor, dll)
import com.bloodcare.bloodcare.Jadwal;
import com.bloodcare.bloodcare.Pendonor;
import com.bloodcare.bloodcare.RiwayatDonasi;
import com.bloodcare.bloodcare.StokDarah;
import java.io.IOException;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class PetugasController {

    @FXML private TextField txtNama, txtNik, txtTelepon, txtAlamat;
    @FXML private DatePicker dpLahir;
    @FXML private ComboBox<String> cbJK, cbGolDarah;
    
    @FXML private TableView<Pendonor> tablePendonor;
    @FXML private TableColumn<Pendonor, String> colNama, colNik, colGol, colTelp, colJK, colAlamat, colLahir;
    
    @FXML private ComboBox<Pendonor> cbPilihPendonor; 
    @FXML private DatePicker dpTanggalDonor;
    @FXML private TextField txtVolume;
    @FXML private ComboBox<String> cbJenisDarah, cbRhesus;
    @FXML private TableView<RiwayatDonasi> tableDonasi;
    @FXML private TableColumn<RiwayatDonasi, String> colRiwayatNama, colRiwayatTgl, colRiwayatVol, colRiwayatGol;

    @FXML private TableView<Jadwal> tableJadwal;
    @FXML private TableColumn<Jadwal, String> colLokasi, colTgl, colJamMulai, colJamSelesai;

    @FXML private TableView<StokDarah> tableStok;
    @FXML private TableColumn<StokDarah, String> colStokGol, colStokRhesus, colStokJml;

    @FXML private Label lblStatus;

    private PendonorDAO pendonorDAO = new PendonorDAO();
    private RiwayatDonasiDAO donasiDAO = new RiwayatDonasiDAO();
    private JadwalDAO jadwalDAO = new JadwalDAO(); // DAO Baru
    private StokDAO stokDAO = new StokDAO();       // DAO Baru
    
    private Pendonor selectedPendonor = null; 

    @FXML
    public void initialize() {
        setupCombo();
        setupTables();
        refreshData();
    }

    private void setupCombo() {
        cbJK.setItems(FXCollections.observableArrayList("L", "P"));
        cbGolDarah.setItems(FXCollections.observableArrayList("A", "B", "AB", "O"));
        cbJenisDarah.setItems(FXCollections.observableArrayList("Whole Blood", "Plasma", "Platelet"));
        cbRhesus.setItems(FXCollections.observableArrayList("+", "-"));
        
        cbPilihPendonor.setConverter(new StringConverter<Pendonor>() {
            @Override public String toString(Pendonor p) { return (p == null) ? null : p.getNama() + " (" + p.getGolDarah() + ")"; }
            @Override public Pendonor fromString(String string) { return null; }
        });
    }

    private void setupTables() {
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colNik.setCellValueFactory(new PropertyValueFactory<>("nik"));
        colLahir.setCellValueFactory(new PropertyValueFactory<>("tanggalLahir"));
        colJK.setCellValueFactory(new PropertyValueFactory<>("jenisKelamin"));
        colGol.setCellValueFactory(new PropertyValueFactory<>("golDarah"));
        colTelp.setCellValueFactory(new PropertyValueFactory<>("telepon"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));

        tablePendonor.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedPendonor = newVal;
                txtNama.setText(newVal.getNama());
                txtNik.setText(newVal.getNik());
                txtTelepon.setText(newVal.getTelepon());
                txtAlamat.setText(newVal.getAlamat());
                cbJK.setValue(newVal.getJenisKelamin());
                cbGolDarah.setValue(newVal.getGolDarah());
                dpLahir.setValue(newVal.getTanggalLahir());
                lblStatus.setText("Mode Edit: " + newVal.getNama());
            }
        });

        colRiwayatNama.setCellValueFactory(new PropertyValueFactory<>("namaPendonor"));
        colRiwayatTgl.setCellValueFactory(new PropertyValueFactory<>("tanggalDonor"));
        colRiwayatVol.setCellValueFactory(new PropertyValueFactory<>("volumeMl"));
        colRiwayatGol.setCellValueFactory(new PropertyValueFactory<>("golDarah"));

        colLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasi"));
        colTgl.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colJamMulai.setCellValueFactory(new PropertyValueFactory<>("jamMulai"));
        colJamSelesai.setCellValueFactory(new PropertyValueFactory<>("jamSelesai"));
      
        colStokGol.setCellValueFactory(new PropertyValueFactory<>("golDarah"));
        colStokRhesus.setCellValueFactory(new PropertyValueFactory<>("rhesus"));
        colStokJml.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
    }
   


    private void refreshData() {
        ObservableList<Pendonor> listP = FXCollections.observableArrayList(pendonorDAO.getAll());
        tablePendonor.setItems(listP);
        cbPilihPendonor.setItems(listP); 
        tableDonasi.setItems(FXCollections.observableArrayList(donasiDAO.getAll()));
        
        tableJadwal.setItems(FXCollections.observableArrayList(jadwalDAO.getAll()));
        tableStok.setItems(FXCollections.observableArrayList(stokDAO.getAll()));
    }

    @FXML
    private void handleSimpanPendonor() {
        if (selectedPendonor == null) {
            Pendonor p = new Pendonor(0, txtNama.getText(), txtNik.getText(), dpLahir.getValue(), 
                                      cbJK.getValue(), txtAlamat.getText(), txtTelepon.getText(), cbGolDarah.getValue());
            if (pendonorDAO.insert(p)) {
                lblStatus.setText("Pendonor Baru Disimpan!");
                clearFormPendonor();
                refreshData();
            }
        } else {
            selectedPendonor.setNama(txtNama.getText());
            selectedPendonor.setNik(txtNik.getText());
            selectedPendonor.setTanggalLahir(dpLahir.getValue());
            selectedPendonor.setJenisKelamin(cbJK.getValue());
            selectedPendonor.setAlamat(txtAlamat.getText());
            selectedPendonor.setTelepon(txtTelepon.getText());
            selectedPendonor.setGolDarah(cbGolDarah.getValue());
            
            if (pendonorDAO.update(selectedPendonor)) {
                lblStatus.setText("Data Pendonor Diupdate!");
                clearFormPendonor();
                refreshData();
            }
        }
    }
    @FXML
private void hapusPendonor() {
    Pendonor p = tablePendonor.getSelectionModel().getSelectedItem();

    if (p == null) {
        lblStatus.setText("Pilih pendonor dulu!");
        return;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Konfirmasi Hapus");
    alert.setHeaderText(null);
    alert.setContentText("Yakin ingin menghapus pendonor: " + p.getNama() + "?");

    if (alert.showAndWait().get() == ButtonType.OK) {
        if (pendonorDAO.delete(p.getId())) {
            lblStatus.setText("Pendonor berhasil dihapus!");
            refreshData();
            clearFormPendonor();
        } else {
            lblStatus.setText("Gagal menghapus pendonor!");
        }
    }
}

    
    @FXML void handleClearPendonor() { clearFormPendonor(); }

    private void clearFormPendonor() {
        txtNama.clear(); txtNik.clear(); txtTelepon.clear(); txtAlamat.clear();
        dpLahir.setValue(null); cbJK.setValue(null); cbGolDarah.setValue(null);
        selectedPendonor = null;
        lblStatus.setText("Form Bersih (Mode Tambah)");
    }

    @FXML
    private void handleSimpanDonasi() {
        Pendonor p = cbPilihPendonor.getValue();
        if (p == null || dpTanggalDonor.getValue() == null || txtVolume.getText().isEmpty()) {
            lblStatus.setText("Pilih Pendonor, Tanggal, dan Volume!");
            return;
        }

        RiwayatDonasi rd = new RiwayatDonasi();
        rd.setPendonorId(p.getId());
        rd.setTanggalDonor(Date.valueOf(dpTanggalDonor.getValue()));
        rd.setJenisDarah(cbJenisDarah.getValue());
        rd.setVolumeMl(Integer.parseInt(txtVolume.getText()));
        rd.setGolDarah(p.getGolDarah()); 
        rd.setRhesus(cbRhesus.getValue());

        if (donasiDAO.insert(rd)) {
            lblStatus.setText("Donasi Berhasil Dicatat!");
            refreshData(); // Ini otomatis akan merefresh Stok juga di tampilan Admin/Petugas lain
            txtVolume.clear();
        }
    }
   


    
@FXML 
private void logout()throws IOException { 
    App.setRoot("login"); 
  
    }
 }

