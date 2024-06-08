package DTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author Admin
 */
@Data
@Entity
@Table (name ="xuly")
public class XuLy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Chế độ tự động tăng
    @Column(name = "MaXL")
    private int maXL;

    public int getMaXL() {
		return maXL;
	}

	public void setMaXL(int maXL) {
		this.maXL = maXL;
	}

	public int getMaTV() {
		return MaTV;
	}

	public void setMaTV(int maTV) {
		MaTV = maTV;
	}

	public String getHinhThucXL() {
		return hinhThucXL;
	}

	public void setHinhThucXL(String hinhThucXL) {
		this.hinhThucXL = hinhThucXL;
	}

	public Integer getSoTien() {
		return soTien;
	}

	public void setSoTien(Integer soTien) {
		this.soTien = soTien;
	}

	public Date getNgayXL() {
		return ngayXL;
	}

	public void setNgayXL(Date ngayXL) {
		this.ngayXL = ngayXL;
	}

	public Integer getTrangThaiXL() {
		return trangThaiXL;
	}

	public void setTrangThaiXL(Integer trangThaiXL) {
		this.trangThaiXL = trangThaiXL;
	}

	@Column
    private int MaTV ;
    
    @Column(name = "HinhThucXL", length = 250, nullable = true)
    private String hinhThucXL;

    @Column(name = "SoTien", nullable = true)
    private Integer soTien; // Using Integer to handle possible null values for SoTien

    @Temporal(TemporalType.TIMESTAMP) // This maps to a SQL datetime
    @Column(name = "NgayXL", nullable = true)
    private Date ngayXL;

    @Column(name = "TrangThaiXL", nullable = true)
    private Integer trangThaiXL; 
        


}