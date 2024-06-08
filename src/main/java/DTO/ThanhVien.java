package DTO;

import javax.persistence.*;
import java.io.Serializable;

import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "thanhvien")
public class ThanhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTV",nullable = false)
    private int maTV;

    public int getMaTV() {
		return maTV;
	}

	public void setMaTV(int maTV) {
		this.maTV = maTV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getKhoa() {
		return khoa;
	}

	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}

	public String getNganh() {
		return nganh;
	}

	public void setNganh(String nganh) {
		this.nganh = nganh;
	}

	public int getSdt() {
		return sdt;
	}

	public void setSdt(int sdt) {
		this.sdt = sdt;
	}


	@Column(name = "HoTen", length = 100)
    private String hoTen;

    @Column(name = "Khoa", length = 100)
    private String khoa;

    @Column(name = "Nganh", length = 100)
    private String nganh;
    
    @Column(name = "SDT",length = 10)
    private int sdt;

    // Assuming that a 'ThanhVien' can have multiple 'XuLy' records, we define a one-to-many relationship
    // Replace 'XuLy' with the actual name of the entity representing the 'xuly' table


    
 
    // No need for explicit constructors, getters, or setters due to @Data annotation from Lombok
}