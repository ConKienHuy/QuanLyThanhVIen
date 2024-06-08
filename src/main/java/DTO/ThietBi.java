package DTO;

import lombok.Data;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name="thietbi")
public class ThietBi {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "maTB")
    private List<ThongTinsd> ThongTinsd;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaTB")
	private int MaTB;
	
	@Column(name = "TenTB")
	private String tenTB;
	
	@Column(name = "MoTaTB")
	private String motaTB;
	
	public ThietBi() {
		
	}

	public ThietBi(int maTB, String tenTB, String motaTB) {
		this.MaTB = maTB;
		this.tenTB = tenTB;
		this.motaTB = motaTB;
	}

	public int getMaTB() {
		return MaTB;
	}

	public void setMaTB(int maTB) {
		this.MaTB = maTB;
	}

	public String getTenTB() {
		return tenTB;
	}

	public void setTenTB(String tenTB) {
		this.tenTB = tenTB;
	}

	public String getMotaTB() {
		return motaTB;
	}

	public void setMotaTB(String motaTB) {
		this.motaTB = motaTB;
	}
	
}
