package GUI;

import BLL.ThanhVienBLL;

import BLL.ThietBiBLL;
import BLL.ThongTinsdBLL;
import BLL.XuLyBLL;
import DTO.ThanhVien;
import DTO.ThietBi;
import DTO.ThongTinsd;
import DTO.XuLy;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ThongTinSDPanel extends JPanel {

    private JTable jtb_thongtinsd;
    private JTextField jtf_mathongtin;
    private JComboBox comboBox_tv = new JComboBox();
    private JComboBox comboBox_tb = new JComboBox();
    DefaultTableModel model;

    public ThongTinSDPanel() {
        setBackground(new Color(254, 255, 255));
        setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Th\u00F4ng tin s\u1EED d\u1EE5ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        setLayout(null);

        jtb_thongtinsd = new JTable();
        jtb_thongtinsd.setBounds(6, 265, 988, 448);
        jtb_thongtinsd.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        jtb_thongtinsd.setModel(new DefaultTableModel(
                new Object[][]{}, // Empty data vector
                new String[]{"MÃ TT","MÃ TV", "MÃ TB", "TG Vào", "TG Mượn", "TG Trả"}
        ));
        model = (DefaultTableModel) jtb_thongtinsd.getModel();
        updateTableData(1);


        JScrollPane spn_thongtinsd = new JScrollPane();
        spn_thongtinsd.setBounds(6, 217, 988, 448);
        spn_thongtinsd.setViewportView(jtb_thongtinsd);
        add(spn_thongtinsd);

        JLabel jlb_matv = new JLabel("Mã TV");
        jlb_matv.setBounds(35, 35, 61, 30);
        add(jlb_matv);

        JLabel jlb_matb = new JLabel("MÃ TB");
        jlb_matb.setBounds(368, 35, 64, 30);
        add(jlb_matb);

        JLabel jlb_tgvao = new JLabel("TG Vào");
        jlb_tgvao.setBounds(35, 90, 61, 30);
        add(jlb_tgvao);

        
        JComboBox comboBox_tb = new JComboBox();
        comboBox_tb.setBounds(448, 36, 182, 30);
        add(comboBox_tb);
        
        JDateChooser chooserMuon = new JDateChooser();
        chooserMuon.setBounds(755, 35, 141, 30);
        add(chooserMuon);
        
        JDateChooser chooserVao = new JDateChooser();
        chooserVao.setBounds(106, 90, 182, 30);
        add(chooserVao);
        
        JDateChooser chooserTra = new JDateChooser();
        chooserTra.setBounds(448, 90, 182, 30);
        add(chooserTra);
        

        JLabel jlb_tgmuon = new JLabel("TG Mượn");
        jlb_tgmuon.setBounds(687, 35, 61, 30);
        add(jlb_tgmuon);

        JLabel jlb_tgtra = new JLabel("TG Trả");
        jlb_tgtra.setBounds(368, 90, 70, 30);
        add(jlb_tgtra);
        chooserTra.setEnabled(false);

        JButton btn_add = new JButton("Mượn");
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int maTV = (int) comboBox_tv.getSelectedItem();
                int maTB = (int) comboBox_tb.getSelectedItem();
                java.util.Date ngayMuon = chooserMuon.getDate();
                java.util.Date ngayVao = chooserVao.getDate();
                java.util.Date ngayTra = chooserTra.getDate();

                if (ngayMuon.before(ngayVao)) {
                    JOptionPane.showMessageDialog(null, "Ngày mượn phải sau ngày vào. Vui lòng chọn lại ngày.");
                    return;
                }

                if (isDeviceBorrowed(maTB)) {
                    JOptionPane.showMessageDialog(null, "Thiết bị này đã được mượn. Vui lòng chọn thiết bị khác.");
                    return; // Thoát khỏi phương thức nếu mã thiết bị đã được mượn
                }

                // Tạo đối tượng mới với dữ liệu đã thu thập
                ThanhVien tv = new ThanhVien();
                ThietBi tb = new ThietBi();
                tv.setMaTV(maTV);
                tb.setMaTB(maTB);
                ThongTinsd tsd = new ThongTinsd( tv, tb, ngayVao, ngayMuon, ngayTra);

                // Gọi BLL để xử lý thêm vào cơ sở dữ liệu
                try {
                    ThongTinsdBLL thongTinsdBLL = new ThongTinsdBLL();
                    thongTinsdBLL.createNew(tsd);
                    // Cập nhật lại bảng sau khi thêm
                    updateTableData(1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm xử lý: " + ex.getMessage());
                }
                chooserTra.setEnabled(false);
            }
        });

        btn_add.setBounds(220, 150, 120, 36);
        add(btn_add);

        JButton btn_edit = new JButton("Trả");
        btn_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy vị trí hàng được chọn
                int selectedRowIndex = jtb_thongtinsd.getSelectedRow();

                // Kiểm tra xem có hàng nào được chọn không
                if (selectedRowIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa");
                    return; // Kết thúc phương thức nếu không có hàng nào được chọn
                }

                // Lấy dữ liệu từ hàng được chọn trong bảng
                int maTT = (int) jtb_thongtinsd.getValueAt(selectedRowIndex, 0);
                int maTV = (int) jtb_thongtinsd.getValueAt(selectedRowIndex, 1);
                int maTB = (int) jtb_thongtinsd.getValueAt(selectedRowIndex, 2);
                java.util.Date ngayVao = chooserVao.getDate();
                java.util.Date ngayMuon = chooserMuon.getDate();
                java.util.Date ngayTra = chooserTra.getDate();
                if (ngayTra.before(ngayMuon)) {
                    JOptionPane.showMessageDialog(null, "Ngày trả phải sau ngày mượn. Vui lòng chọn lại ngày.");
                    return; 
                }

                ThanhVien tv = new ThanhVien();
                tv.setMaTV(maTV);

                ThietBi tb = new ThietBi();
                tb.setMaTB(maTB);

                ThongTinsd tsd = new ThongTinsd();
                tsd.setMaTT(maTT);
                tsd.setMaTV(tv);
                tsd.setMaTB(tb);
                tsd.setTGVao(ngayVao);
                tsd.setTGMuon(ngayMuon);
                tsd.setTGTra(ngayTra);

                try {
                    ThongTinsdBLL thongTinsdBLL = new ThongTinsdBLL();
                    thongTinsdBLL.update(tsd);

                    // Cập nhật dòng thông tin trong bảng
                    updateTableData(selectedRowIndex);
                    JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin sử dụng.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi sửa thông tin sử dụng: " + ex.getMessage());
                }
                chooserTra.setEnabled(false);
            }
        });




        btn_edit.setBounds(420, 150, 120, 36);
        add(btn_edit);

        JButton btn_delete = new JButton("Xoá");
        btn_delete.setBounds(620, 150, 120, 36);
        btn_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Lấy vị trí hàng được chọn
				int selectedRowIndex = jtb_thongtinsd.getSelectedRow();
				
				// Kiểm tra có hàng nào được chọn
				if(selectedRowIndex == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa");
					return;
				}
				int option = JOptionPane
				.showConfirmDialog(null, "Bạn có chắc chắn xóa?","Xác nhận xóa",JOptionPane.YES_NO_OPTION);
				
				if(option == JOptionPane.YES_OPTION) {
					// Lấy dữ liệu từ hàng được chọn
					int maTT = (int) jtb_thongtinsd.getValueAt(selectedRowIndex, 0);
					int maTV = (int) jtb_thongtinsd.getValueAt(selectedRowIndex, 1);
					int maTB = (int) jtb_thongtinsd.getValueAt(selectedRowIndex, 2);
					Date tgVao = chooserVao.getDate();
					Date tgMuon = chooserMuon.getDate();
					Date tgTra = chooserTra.getDate();
					
					ThanhVien tv = new ThanhVien(); tv.setMaTV(maTV);
					ThietBi tb = new ThietBi(); tb.setMaTB(maTB);
					try {
						ThongTinsdBLL tbll = new ThongTinsdBLL();
						tbll.delete(maTT);
						updateTableData(1);
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa thông tin");
						ex.printStackTrace();
					}
				}
                                chooserTra.setEnabled(false);
			}
		});
        add(btn_delete);

        comboBox_tv.setBounds(108, 36, 180, 30);
        DefaultComboBoxModel<Integer> tvModel = new DefaultComboBoxModel<Integer>();
        List<ThanhVien> tvList = new ThanhVienBLL().getThanhVienList();
        for(ThanhVien tVien : tvList) {
        	tvModel.addElement(tVien.getMaTV());
        }
        comboBox_tv.setModel(tvModel);
        add(comboBox_tv);
        
        DefaultComboBoxModel<Integer> tbModel = new DefaultComboBoxModel<Integer>();
        List<ThietBi> tbList = new ThietBiBLL().getThietBiList();
        for(ThietBi tBi : tbList) {
        	tbModel.addElement(tBi.getMaTB());
        }
        comboBox_tb.setModel(tbModel);
        
        // Hiển thị dữ liệu lên khi người dùng chọn 1 hàng trong bảng
        jtb_thongtinsd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lấy chỉ số của hàng được chọn
                int rowIndex = jtb_thongtinsd.getSelectedRow();

                // Lấy giá trị của các ô trong hàng được chọn
                Object maTT = jtb_thongtinsd.getValueAt(rowIndex, 0);
                Object maTV = jtb_thongtinsd.getValueAt(rowIndex, 1);
                Object maTB = jtb_thongtinsd.getValueAt(rowIndex, 2);
                Object TGVAO = jtb_thongtinsd.getValueAt(rowIndex, 3);
                Object TGmuon = jtb_thongtinsd.getValueAt(rowIndex, 4);
                Object TGtra = jtb_thongtinsd.getValueAt(rowIndex, 5);

                // Hiển thị giá trị lên các JTextField tương ứng
                comboBox_tv.setSelectedItem(maTV);
                comboBox_tb.setSelectedItem(maTB);
                chooserVao.setDate((Date) TGVAO);;
                chooserMuon.setDate((Date) TGmuon);
                chooserTra.setDate((Date) TGtra);
                chooserTra.setEnabled(true);
            }
        });
    }
        public boolean isDeviceBorrowed(int maTB) {
        try {
            ThongTinsdBLL thongTinsdBLL = new ThongTinsdBLL();
            List<ThongTinsd> thongTinList = thongTinsdBLL.LoadThongTinsd(1);

            for (ThongTinsd ttsd : thongTinList) {

                if (ttsd.getMaTB().getMaTB() == maTB && ttsd.getTGTra() == null) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
    // Mã thiết bị không được mượn hoặc đã được trả
    return false;
}

        private void updateRowData(ThongTinsd tsd, int rowIndex) {
    // Cập nhật dòng thông tin ở vị trí rowIndex trong bảng
    model.setValueAt(tsd.getMaTT(), rowIndex, 0);
    model.setValueAt(tsd.getMaTV().getMaTV(), rowIndex, 1);
    model.setValueAt(tsd.getMaTB().getMaTB(), rowIndex, 2);
    model.setValueAt(tsd.getTGVao(), rowIndex, 3);
    model.setValueAt(tsd.getTGMuon(), rowIndex, 4);
    model.setValueAt(tsd.getTGTra(), rowIndex, 5);
}
    private void updateTableData(int page) {
        try {
            ThongTinsdBLL tBll = new ThongTinsdBLL();
            List<ThongTinsd> listThongTin = tBll.LoadThongTinsd(1);

            // Xóa dữ liệu hiện có trong bảng
            model.setRowCount(0);
            

            // Chuyển đổi dữ liệu thành mảng Object và thêm vào tableModel
            for (ThongTinsd tts : listThongTin) {
                Object[] row = new Object[]{
                    tts.getMaTT(),
                    tts.getMaTV() == null ? 0: tts.getMaTV().getMaTV(),
                    tts.getMaTB() == null ? 0: tts.getMaTB().getMaTB(),
                    tts.getTGVao(),
                    tts.getTGMuon(),
                    tts.getTGTra()
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        
    }
}
