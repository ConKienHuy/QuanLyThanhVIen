package BLL;

import java.sql.SQLException;
import java.util.List;

import DAL.XuLyDAL;

import DTO.XuLy;

public class XuLyBLL {
	XuLyDAL xuly;
    public XuLyBLL() {
    	xuly = new XuLyDAL();
    }
    
    public List LoadXuLy(int page) throws SQLException {
        int numofrecords = 30;
        List list = xuly.loadXuLy();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        return list.subList(from, Math.min(to, size));
    }
    public boolean addXuLy(XuLy c) throws SQLException {
        boolean result = xuly.addXuLy(c);
        return result;
    }
    public boolean updateXuLy(XuLy c) throws SQLException {
        boolean result = xuly.updateXuLy(c);
        return result;
    }
    public boolean deleteXuLy(int maXL) throws SQLException {
        boolean result = xuly.deleteXuLy(maXL);
        return result;
    }
}
