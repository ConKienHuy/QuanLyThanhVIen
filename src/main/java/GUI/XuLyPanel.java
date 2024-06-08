package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.ThanhVienBLL;
import BLL.XuLyBLL;
import DTO.ThanhVien;
import DTO.XuLy;

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

import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class XuLyPanel extends JPanel {

    private JTable jtb_thanhvien;
    private JTextField jtf_hinhthuc;
    private JTextField jtf_sotien;
    private JComboBox comboBox_tv = new JComboBox();
    private JComboBox comboBox_tt = new JComboBox();
    DefaultTableModel model;

    public XuLyPanel() {
        setBackground(new Color(254, 255, 255));
        setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "X\u1EED l\u00FD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        setLayout(null);

        jtb_thanhvien = new JTable();
        jtb_thanhvien.setBounds(6, 265, 988, 448);
        jtb_thanhvien.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        jtb_thanhvien.setModel(new DefaultTableModel(
                new Object[][]{}, // Empty data vector
                new String[]{"MÃ XL", "MÃ TV", "HÌNH THỨC XL", "SỐ TIỀN", "NGÀY XL", "TRẠNG THÁI"}
        ));
        model = (DefaultTableModel) jtb_thanhvien.getModel();

        updateTableData(1);

        JScrollPane spn_thanhvien = new JScrollPane();
        spn_thanhvien.setBounds(6, 217, 988, 448);
        spn_thanhvien.setViewportView(jtb_thanhvien);
        add(spn_thanhvien);

        JLabel jlb_matv = new JLabel("Mã TV");
        jlb_matv.setBounds(35, 35, 61, 30);
        add(jlb_matv);

        JLabel jlb_hinhthuc = new JLabel("Hình thức");
        jlb_hinhthuc.setBounds(368, 35, 64, 30);
        add(jlb_hinhthuc);

        jtf_hinhthuc = new JTextField();
        jtf_hinhthuc.setColumns(10);
        jtf_hinhthuc.setBounds(450, 35, 180, 30);
        add(jtf_hinhthuc);

        JLabel jlb_sotien = new JLabel("Số tiền");
        jlb_sotien.setBounds(35, 90, 61, 30);
        add(jlb_sotien);

        jtf_sotien = new JTextField();
        jtf_sotien.setColumns(10);
        jtf_sotien.setBounds(108, 91, 180, 30);
        add(jtf_sotien);

        JLabel jlb_ngay = new JLabel("Ngày");
        jlb_ngay.setBounds(711, 35, 61, 30);
        add(jlb_ngay);

        JLabel jlb_trangthai = new JLabel("Trạng thái");
        jlb_trangthai.setBounds(368, 90, 70, 30);
        add(jlb_trangthai);

        JButton btn_add = new JButton("Thêm");
        btn_add.setBounds(220, 150, 120, 36);
        add(btn_add);

        JButton btn_edit = new JButton("Sửa");
        btn_edit.setBounds(420, 150, 120, 36);
        add(btn_edit);

        JButton btn_delete = new JButton("Xoá");
        btn_delete.setBounds(620, 150, 120, 36);
        add(btn_delete);

        comboBox_tv.setBounds(108, 36, 180, 30);
        loadMaTVOptions();
        add(comboBox_tv);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(755, 35, 141, 30);
        add(dateChooser);

        comboBox_tt.setBounds(450, 90, 180, 30);
        add(comboBox_tt);
        comboBox_tt.addItem(0); // Giá trị 0
        comboBox_tt.addItem(1); // Giá trị 1

        jtb_thanhvien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lấy chỉ số của hàng được chọn
                int rowIndex = jtb_thanhvien.getSelectedRow();

                // Lấy giá trị của các ô trong hàng được chọn
                Object maXL = jtb_thanhvien.getValueAt(rowIndex, 0);
                Object maTV = jtb_thanhvien.getValueAt(rowIndex, 1);
                Object hinhThucXL = jtb_thanhvien.getValueAt(rowIndex, 2);
                Object soTien = jtb_thanhvien.getValueAt(rowIndex, 3);
                Object ngayXL = jtb_thanhvien.getValueAt(rowIndex, 4);
                Object trangThaiXL = jtb_thanhvien.getValueAt(rowIndex, 5);

                // Hiển thị giá trị lên các JTextField tương ứng
                comboBox_tv.setSelectedItem(maTV);
                jtf_hinhthuc.setText(hinhThucXL != null ? hinhThucXL.toString() : "");
                jtf_sotien.setText(soTien != null ? soTien.toString() : "");
                comboBox_tt.setSelectedItem(trangThaiXL);
                // Tiếp tục hiển thị giá trị của các cột khác lên các JTextField tương ứng nếu cần
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date ngayXuLyDate = dateFormat.parse(ngayXL.toString());
                    dateChooser.setDate(ngayXuLyDate);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi chuyển đổi ngày: " + ex.getMessage());
                }
            }
        });
        //xóa 
        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy chỉ số của hàng được chọn
                int[] selectedRows = jtb_thanhvien.getSelectedRows();

                // Kiểm tra xem có hàng nào được chọn không
                if (selectedRows.length == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một hàng để xóa.");
                    return;
                }

                // Hiển thị hộp thoại xác nhận
                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

                // Kiểm tra xem người dùng đã xác nhận xóa hay không
                if (option == JOptionPane.YES_OPTION) {
                    // Lặp qua các hàng được chọn và xóa chúng
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        // Lấy giá trị của cột MaXL từ hàng được chọn
                        int maXL = (int) jtb_thanhvien.getValueAt(selectedRows[i], 0);

                        // Gọi phương thức BLL để xóa dữ liệu
                        try {
                            XuLyBLL xuLyBLL = new XuLyBLL();
                            xuLyBLL.deleteXuLy(maXL);
                            // Cập nhật lại bảng sau khi xóa
                            updateTableData(1);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa xử lý: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        //
        btn_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy chỉ số của hàng được chọn
                int rowIndex = jtb_thanhvien.getSelectedRow();

                // Kiểm tra xem hàng nào đã được chọn
                if (rowIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa.");
                    return;
                }

                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Thu thập dữ liệu từ các thành phần nhập
                    Object maXL = jtb_thanhvien.getValueAt(rowIndex, 0);
                    int maTV = (int) comboBox_tv.getSelectedItem();
                    String hinhThucXuLy = jtf_hinhthuc.getText();
                    String soTienStr = jtf_sotien.getText();
                    int trangThaiXL = (int) comboBox_tt.getSelectedItem();
                    java.util.Date ngayXuLy = dateChooser.getDate();

                    // Kiểm tra các giá trị không được trống
                    if (hinhThucXuLy.isEmpty() || ngayXuLy == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                        return; // Thoát khỏi phương thức nếu có bất kỳ trường nào trống
                    }

                    // Kiểm tra nếu số tiền không phải là một số
                    Integer soTien = 0;
                    if (!soTienStr.isEmpty()) {

                        try {
                            soTien = Integer.parseInt(soTienStr.trim());
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Số tiền phải là một số.");
                            return; // Thoát khỏi phương thức nếu số tiền không phải là một số

                        }

                    }

                    // Tạo một đối tượng XuLy mới với thông tin đã chỉnh sửa
                    XuLy xuLySua = new XuLy();
                    xuLySua.setMaXL((int) maXL);
                    xuLySua.setMaTV((int) maTV);
                    xuLySua.setHinhThucXL(hinhThucXuLy);
                    xuLySua.setSoTien(soTien);
                    xuLySua.setNgayXL(ngayXuLy);
                    xuLySua.setTrangThaiXL((int) trangThaiXL);

                    // Gọi phương thức BLL để cập nhật dữ liệu
                    try {
                        XuLyBLL xuLyBLL = new XuLyBLL();
                        xuLyBLL.updateXuLy(xuLySua);
                        // Cập nhật lại bảng sau khi sửa
                        updateTableData(1);
                        jtf_hinhthuc.setText("");
                        jtf_sotien.setText("");
                        dateChooser.setDate(null);
                        JOptionPane.showMessageDialog(null, "Sửa thành công!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi sửa xử lý: " + ex.getMessage());
                    }
                }
            }
        });
        // Lắng nghe sự kiện khi nhấn nút "Thêm"
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thu thập dữ liệu từ các thành phần nhập
                int maTV = (int) comboBox_tv.getSelectedItem();
                String hinhThucXuLy = jtf_hinhthuc.getText();
                String soTienStr = jtf_sotien.getText();
                int trangThaiXL = (int) comboBox_tt.getSelectedItem();
                java.util.Date ngayXuLy = dateChooser.getDate();

                // Kiểm tra các giá trị không được trống
                if (hinhThucXuLy.isEmpty() || ngayXuLy == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                    return; // Thoát khỏi phương thức nếu có bất kỳ trường nào trống
                }

                // Kiểm tra nếu số tiền không phải là một số
                Integer soTien = 0;
                if (!soTienStr.isEmpty()) {
                    try {
                        soTien = Integer.parseInt(soTienStr.trim());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Số tiền phải là một số.");
                        return; // Thoát khỏi phương thức nếu số tiền không phải là một số
                    }
                }

                // Tạo đối tượng mới với dữ liệu đã thu thập
                XuLy xuLyMoi = new XuLy();
                xuLyMoi.setMaTV(maTV);
                xuLyMoi.setHinhThucXL(hinhThucXuLy);
                xuLyMoi.setSoTien(soTien);
                xuLyMoi.setNgayXL(ngayXuLy);
                xuLyMoi.setTrangThaiXL(trangThaiXL);

                // Gọi BLL để xử lý thêm vào cơ sở dữ liệu
                try {
                    XuLyBLL xuLyBLL = new XuLyBLL();
                    xuLyBLL.addXuLy(xuLyMoi);
                    // Cập nhật lại bảng sau khi thêm
                    updateTableData(1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm xử lý: " + ex.getMessage());
                }
            }
        });
    }

    private void updateTableData(int page) {
        try {
            XuLyBLL xuLyBLL = new XuLyBLL();
            List<XuLy> listXuLy = xuLyBLL.LoadXuLy(page); // Sửa tên phương thức cho đúng

            // Xóa dữ liệu hiện có trong bảng
            model.setRowCount(0);

            // Định dạng ngày
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Chuyển đổi dữ liệu thành mảng Object và thêm vào tableModel
            for (XuLy xl : listXuLy) {
                Object[] row = new Object[]{
                    xl.getMaXL(),
                    xl.getMaTV(),
                    xl.getHinhThucXL(),
                    xl.getSoTien(),
                    dateFormat.format(xl.getNgayXL()), // Định dạng ngày
                    xl.getTrangThaiXL()
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadMaTVOptions() {
        // Phương thức giả định này sẽ lấy danh sách các "Mã TV" từ cơ sở dữ liệu
        // Đảm bảo rằng ComboBox rỗng trước khi thêm items
        comboBox_tv.removeAllItems();

        ThanhVienBLL thanhVienBLL = new ThanhVienBLL();
        List<ThanhVien> thanhVienList = thanhVienBLL.getThanhVienList();

        // Kiểm tra xem danh sách có rỗng không
        if (thanhVienList.isEmpty()) {
            System.out.println("Không có dữ liệu Mã TV.");
            return;
        }

        // Nếu có dữ liệu, thêm vào JComboBox
        for (ThanhVien tv : thanhVienList) {
            comboBox_tv.addItem(tv.getMaTV());
        }

        // Bắt buộc cập nhật UI để phản ánh sự thay đổis
        comboBox_tv.revalidate();
        comboBox_tv.repaint();
    }

    // Phương thức để thêm dữ liệu vào bảng
    public void addRowToTable(Object[] rowData) {

        model.addRow(rowData);
    }
}
