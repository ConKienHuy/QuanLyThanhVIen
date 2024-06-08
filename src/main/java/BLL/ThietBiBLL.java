/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.ThietBiDAL;
import DTO.ThietBi;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huy
 */
public class ThietBiBLL {
	ThietBiDAL thietbi;
    ArrayList<ThietBi> dstb = new ArrayList<ThietBi>();
    
    public ThietBiBLL() {
        thietbi = new ThietBiDAL();
    }
    
    public List<ThietBi> getThietBiList() {
        return thietbi.listThietBi();
    }
    
    public ArrayList<ThietBi> getlistTB() {
        dstb = thietbi.listThietBi();
        return dstb;
    }
    
    public ArrayList<Integer> getlistmatb() {
        return thietbi.getlistmatb();
    }
    
    public String getThietBifromID(int matb) {
        return thietbi.getInfo(matb);
    }
    
    public List<ThietBi> getThietBifromName(String tentb){
    	return thietbi.getThietBifromName(tentb);
    }
    
    public List<ThietBi> LoadThietBi(int page) throws SQLException {
        int numofrecords = 30;
        List<ThietBi> list = thietbi.loadThietBi();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        return list.subList(from, Math.min(to, size));
    }
    
    public boolean addThietBi(ThietBi c) throws SQLException {
        boolean result = thietbi.addThietBi(c);
        return result;
    }
    
    public boolean updateThietBi(ThietBi c) throws SQLException {
        boolean result = thietbi.updateThietBi(c);
        return result;
    }
    
    public boolean deleteThietBi(int maTB) throws SQLException {
        boolean result = thietbi.deleteThietBi(maTB);
        return result;
    }
}
