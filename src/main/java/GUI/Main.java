package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.SwitchScreenController;
import DTO.MenuBean;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;

public class Main extends JFrame {

	private JPanel contentPane;
	 private XuLyPanel xuLyPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel jpn_sidebar = new JPanel();
		jpn_sidebar.setBackground(new Color(128, 128, 128));
		jpn_sidebar.setBounds(0, 0, 200, 672);
		contentPane.add(jpn_sidebar);
		jpn_sidebar.setLayout(null);
		
		JPanel jpn_thanhvien = new JPanel();
		jpn_thanhvien.setBounds(6, 50, 188, 45);
		jpn_sidebar.add(jpn_thanhvien);
		jpn_thanhvien.setLayout(null);
		
		JLabel jlb_thanhvien = new JLabel("THÀNH VIÊN");
		jlb_thanhvien.setHorizontalAlignment(SwingConstants.CENTER);
		jlb_thanhvien.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		jlb_thanhvien.setBounds(6, 6, 176, 33);
		jpn_thanhvien.add(jlb_thanhvien);
		
		JPanel jpn_thietbi = new JPanel();
		jpn_thietbi.setLayout(null);
		jpn_thietbi.setBounds(6, 120, 188, 45);
		jpn_sidebar.add(jpn_thietbi);
		
		JLabel jlb_thietbi = new JLabel("THIẾT BỊ");
		jlb_thietbi.setHorizontalAlignment(SwingConstants.CENTER);
		jlb_thietbi.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		jlb_thietbi.setBounds(6, 6, 176, 33);
		jpn_thietbi.add(jlb_thietbi);
		
		JPanel jpn_xuly = new JPanel();
		jpn_xuly.setLayout(null);
		jpn_xuly.setBounds(6, 190, 188, 45);
		jpn_sidebar.add(jpn_xuly);
		
		JLabel jlb_xuly = new JLabel("XỬ LÝ");
		jlb_xuly.setHorizontalAlignment(SwingConstants.CENTER);
		jlb_xuly.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		jlb_xuly.setBounds(6, 6, 176, 33);
		jpn_xuly.add(jlb_xuly);
		
		JPanel jpn_thongke = new JPanel();
		jpn_thongke.setLayout(null);
		jpn_thongke.setBounds(6, 260, 188, 45);
		jpn_sidebar.add(jpn_thongke);
		
		JLabel jlb_thongke = new JLabel("THỐNG KÊ");
		jlb_thongke.setHorizontalAlignment(SwingConstants.CENTER);
		jlb_thongke.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		jlb_thongke.setBounds(6, 6, 176, 33);
		jpn_thongke.add(jlb_thongke);
		
		JPanel jpn_thongtinsudung = new JPanel();
        jpn_thongtinsudung.setLayout(null);
        jpn_thongtinsudung.setBounds(6, 330, 188, 45);
        jpn_sidebar.add(jpn_thongtinsudung);
        
        JLabel jlb_thongtinsudung = new JLabel("THÔNG TIN SD");
        jlb_thongtinsudung.setHorizontalAlignment(SwingConstants.CENTER);
        jlb_thongtinsudung.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        jlb_thongtinsudung.setBounds(6, 6, 176, 33);
        jpn_thongtinsudung.add(jlb_thongtinsudung);
		
		JPanel jpn_main = new JPanel();
		jpn_main.setBounds(199, 0, 1001, 672);
		contentPane.add(jpn_main);
		
		SwitchScreenController controller = new SwitchScreenController(jpn_main);
		controller.setView(jpn_thongtinsudung, jlb_thongtinsudung);
		
		List<MenuBean> listItem = new ArrayList<>();
		listItem.add(new MenuBean("ThanhVien", jpn_thanhvien, jlb_thanhvien));
		listItem.add(new MenuBean("ThietBi", jpn_thietbi, jlb_thietbi));
		listItem.add(new MenuBean("XuLy", jpn_xuly, jlb_xuly));
		listItem.add(new MenuBean("ThongKe", jpn_thongke, jlb_thongke));
		listItem.add(new MenuBean("ThongTinSD", jpn_thongtinsudung, jlb_thongtinsudung));

		controller.setEvent(listItem);
	}
}
