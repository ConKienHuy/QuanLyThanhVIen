/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author minht
 */
@Data
@Entity
@Table(name = "thongtinsd")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Thongtinsd.findAll", query = "SELECT t FROM Thongtinsd t")
//    , @NamedQuery(name = "Thongtinsd.findByMaTT", query = "SELECT t FROM Thongtinsd t WHERE t.maTT = :maTT")
//    , @NamedQuery(name = "Thongtinsd.findByTGVao", query = "SELECT t FROM Thongtinsd t WHERE t.tGVao = :tGVao")
//    , @NamedQuery(name = "Thongtinsd.findByTGMuon", query = "SELECT t FROM Thongtinsd t WHERE t.tGMuon = :tGMuon")
//    , @NamedQuery(name = "Thongtinsd.findByTGTra", query = "SELECT t FROM Thongtinsd t WHERE t.tGTra = :tGTra")})
public class ThongTinsd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Chế độ tự động tăng
    //@Basic(optional = false)
    @Column(name = "MaTT")
    private Integer maTT;
    
    @Column(name = "TGVao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tGVao;
    
    @Column(name = "TGMuon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tGMuon;
    
    @Column(name = "TGTra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tGTra;
    
    @JoinColumn(name = "MaTV", referencedColumnName = "MaTV")
    @ManyToOne(optional = false)
    private ThanhVien maTV;
    
    @JoinColumn(name = "MaTB", referencedColumnName = "MaTB")
    @ManyToOne
    private ThietBi maTB;

    public ThongTinsd() {
    }

    public ThongTinsd( ThanhVien maTV, ThietBi maTB, Date tGVao, Date tGMuon, Date tGTra) {
		this.tGVao = tGVao;

		this.tGMuon = tGMuon;
		this.tGTra = tGTra;
		this.maTV = maTV;
		this.maTB = maTB;
	}

    /*public ThongTinsd(int MaTT ,ThanhVien maTV, ThietBi maTB, Date tGVao, Date tGMuon, Date tGTra) {
		this.maTT = MaTT;
                this.tGVao = tGVao;
		this.tGMuon = tGMuon;
		this.tGTra = tGTra;
		this.maTV = maTV;
		this.maTB = maTB;
	}*/

	public ThongTinsd(Integer maTT) {
        this.maTT = maTT;
    }



    public Integer getMaTT() {
        return maTT;
    }

    public void setMaTT(Integer maTT) {
        this.maTT = maTT;
    }

    public Date getTGVao() {
        return tGVao;
    }

    public void setTGVao(Date tGVao) {
        this.tGVao = tGVao;
    }

    public Date getTGMuon() {
        return tGMuon;
    }

    public void setTGMuon(Date tGMuon) {
        this.tGMuon = tGMuon;
    }

    public Date getTGTra() {
        return tGTra;
    }

    public void setTGTra(Date tGTra) {
        this.tGTra = tGTra;
    }

    public ThanhVien getMaTV() {
        return maTV;
    }

    public void setMaTV(ThanhVien maTV) {
        this.maTV = maTV;
    }

    public ThietBi getMaTB() {
        return maTB;
    }

    public void setMaTB(ThietBi maTB) {
        this.maTB = maTB;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maTT != null ? maTT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThongTinsd)) {
            return false;
        }
        ThongTinsd other = (ThongTinsd) object;
        if ((this.maTT == null && other.maTT != null) || (this.maTT != null && !this.maTT.equals(other.maTT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.ThongTinsd[ maTT=" + maTT + " ]";
    }
    
}
