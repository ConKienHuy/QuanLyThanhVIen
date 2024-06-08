package BLL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.security.Provider;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import DTO.MenuBean;
import GUI.ThanhVienPanel;
import GUI.ThietBiPanel;
import GUI.ThongKePanel;
import GUI.ThongTinSDPanel;
import GUI.XuLyPanel;

public class SwitchScreenController {
	private JPanel root;
	private String kindSelected = "";
	private int id;
	private int permission = 0;
	private List<MenuBean> listItem = null;

	private List<String> list_module = null;
	
	public SwitchScreenController(JPanel jpnRoot) {
		this.root = jpnRoot;
	}
	
	public void setView(JPanel jpnItem, JLabel jlbItem) {
		kindSelected = "ThongKe";
		root.removeAll();
		root.setLayout(new BorderLayout());
		root.add(new ThongKePanel());
		root.validate();
		root.repaint();
	}
	
	public void setEvent(List<MenuBean> listItem) {
		this.listItem = listItem;
		
		for (MenuBean item : listItem) {
			item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
		}
	}
	
	class LabelEvent implements MouseListener {
		private JPanel node;
		private String kind;
		
		private JPanel jpnItem;
		private JLabel jlbItem;
	
		public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
			this.kind = kind;
			this.jpnItem = jpnItem;
			this.jlbItem = jlbItem;
		}

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			switch (kind) {
				case "ThanhVien": {
					node = new ThanhVienPanel();
					break;
				}
				case "ThietBi": {
					node = new ThietBiPanel();
					break;
				}
				case "XuLy": {
					node = new XuLyPanel();
					break;
				}
				case "ThongKe": {
					node = new ThongKePanel();
					break;
				}
				case "ThongTinSD":{
					node = new ThongTinSDPanel();
					break;
				}
				default:
					node = new ThongKePanel();
					break;
			}
			root.removeAll();
			root.setLayout(new BorderLayout());
			root.add(node);
			root.repaint();
			root.validate();
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			kindSelected = kind;
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {}
		
	}
}
