package BLL;

import java.sql.SQLException;
import java.util.List;

import DAL.ThanhVienDAL;
import DTO.ThanhVien;

public class ThanhVienBLL {
	ThanhVienDAL thanhvien;
	    public ThanhVienBLL() {
	    	thanhvien  = new ThanhVienDAL();
	    }
	    public List<ThanhVien> getThanhVienList() {
	        return thanhvien.getAllThanhVien();
	    }
}
