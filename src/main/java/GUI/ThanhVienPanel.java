package GUI;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ThanhVienPanel extends JPanel {
	private JTable jtb_thanhvien;
	private JTextField jtf_matv;
	private JTextField jtf_hoten;
	private JTextField jtf_sdt;
	private JTextField jtf_khoa;
	private JTextField jtf_nganh;

	public ThanhVienPanel() {
		setBackground(new Color(254, 255, 255));
		setBorder(new TitledBorder(null, "Th\u00E0nh vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		jtb_thanhvien = new JTable();
		jtb_thanhvien.setBounds(6, 265, 988, 448);
		jtb_thanhvien.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		jtb_thanhvien.setModel(new DefaultTableModel(
			new Object[][] {{null, null, null, null, null}},
			new String[] {"MÃ TV", "HỌ TÊN", "SDT", "KHOA", "NGÀNH"}
		));
		JScrollPane spn_thanhvien = new JScrollPane();
		spn_thanhvien.setBounds(6, 217, 988, 448);
		spn_thanhvien.setViewportView(jtb_thanhvien);
		add(spn_thanhvien);
		
		JLabel jlb_matv = new JLabel("Mã TV");
		jlb_matv.setBounds(35, 35, 61, 30);
		add(jlb_matv);
		
		jtf_matv = new JTextField();
		jtf_matv.setBounds(108, 35, 180, 30);
		add(jtf_matv);
		jtf_matv.setColumns(10);
		
		JLabel jlb_hoten = new JLabel("Họ tên");
		jlb_hoten.setBounds(368, 35, 61, 30);
		add(jlb_hoten);
		
		jtf_hoten = new JTextField();
		jtf_hoten.setColumns(10);
		jtf_hoten.setBounds(441, 35, 180, 30);
		add(jtf_hoten);
		
		JLabel jlb_sdt = new JLabel("SDT");
		jlb_sdt.setBounds(709, 35, 61, 30);
		add(jlb_sdt);
		
		jtf_sdt = new JTextField();
		jtf_sdt.setColumns(10);
		jtf_sdt.setBounds(782, 35, 180, 30);
		add(jtf_sdt);
		
		JLabel jlb_khoa = new JLabel("Khoa");
		jlb_khoa.setBounds(35, 90, 61, 30);
		add(jlb_khoa);
		
		jtf_khoa = new JTextField();
		jtf_khoa.setColumns(10);
		jtf_khoa.setBounds(108, 90, 180, 30);
		add(jtf_khoa);
		
		JLabel jlb_nganh = new JLabel("Ngành");
		jlb_nganh.setBounds(368, 90, 61, 30);
		add(jlb_nganh);
		
		jtf_nganh = new JTextField();
		jtf_nganh.setColumns(10);
		jtf_nganh.setBounds(441, 90, 180, 30);
		add(jtf_nganh);
		
		JButton btn_add = new JButton("Thêm");
		btn_add.setBounds(140, 150, 120, 36);
		add(btn_add);
		
		JButton btn_edit = new JButton("Sửa");
		btn_edit.setBounds(340, 150, 120, 36);
		add(btn_edit);
		
		JButton btn_delete = new JButton("Xoá");
		btn_delete.setBounds(540, 150, 120, 36);
		add(btn_delete);
		
		JButton btn_excel = new JButton("Excel");
		btn_excel.setBounds(740, 150, 120, 36);
		add(btn_excel);

	}
}
