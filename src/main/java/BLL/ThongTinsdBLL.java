/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.ThongTinsdDAL;
import DTO.ThanhVien;
import DTO.ThongTinsd;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author minht
 */
public class ThongTinsdBLL {
     ThongTinsdDAL thongtinsd;
     public ThongTinsdBLL() {
        thongtinsd = new ThongTinsdDAL();
    }
    
     public List LoadThongTinsd(int page) throws SQLException {
        int numofrecords = 30;
        List list = thongtinsd.loadThongTinsd();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        return list.subList(from, Math.min(to, size));
    }
     
     public int getmaTTfrommaTV(int matv){
        return thongtinsd.getmaTTfrommaTV(matv);
    }
    
    public boolean createNew(ThongTinsd c){
        boolean result = thongtinsd.createNew(c);
        return result;
    }
    
    public boolean update(ThongTinsd c){
        boolean result = thongtinsd.update(c);
        return result;
    }
    
    public boolean delete(int maTT) {
    	boolean result = thongtinsd.delete(maTT);
    	return result;
    }
     
    public List LoaddsThongTinsd(){       
        List list = thongtinsd.loadThongTinsd();      
        return list;
    }
    public ThongTinsd getTTSDfrommaTT(int maTT){
        return thongtinsd.getTTSDfrommaTT(maTT);
    }
}
