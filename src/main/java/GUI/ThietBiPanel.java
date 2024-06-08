package GUI;

import BLL.ThanhVienBLL;
import BLL.ThietBiBLL;
import BLL.ThongTinsdBLL;
import DAL.ThietBiDAL;
import DTO.ThanhVien;
import DTO.ThietBi;
import DTO.ThongTinsd;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

public class ThietBiPanel extends JPanel {
	private JTable jtb_thietbi;
	private JTextField jtf_matb;
	private JTextField jtf_tentb;
	private JTextField jtf_motatb;
	DefaultTableModel model;
	
	public ThietBiPanel() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Thi\u1EBFt b\u1ECB", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setBackground(new Color(254, 255, 255));
		setLayout(null);
		
		jtb_thietbi = new JTable();
		jtb_thietbi.setBounds(6, 265, 988, 491);
		jtb_thietbi.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		jtb_thietbi.setModel(new DefaultTableModel(
			new Object[][] {{null, null, null}},
			new String[] {"MÃ TB", "TÊN TB", "MÔ TẢ"}
		));
        model = (DefaultTableModel) jtb_thietbi.getModel();
        updateTableData(1);
		JScrollPane spn_thietbi = new JScrollPane();
		spn_thietbi.setBounds(6, 191, 988, 491);
		spn_thietbi.setViewportView(jtb_thietbi);
		add(spn_thietbi);
		
		JLabel jlb_matb = new JLabel("Mã TB");
		jlb_matb.setBounds(35, 35, 61, 30);
		add(jlb_matb);
		
		jtf_matb = new JTextField();
		jtf_matb.setBounds(108, 35, 180, 30);
		add(jtf_matb);
		jtf_matb.setColumns(10);
		
		JLabel jlb_tentb = new JLabel("Tên TB");
		jlb_tentb.setBounds(368, 35, 61, 30);
		add(jlb_tentb);
		
		jtf_tentb = new JTextField();
		jtf_tentb.setColumns(10);
		jtf_tentb.setBounds(441, 35, 180, 30);
		add(jtf_tentb);
		
		JLabel jlb_motatb = new JLabel("Mô tả");
		jlb_motatb.setBounds(709, 35, 61, 30);
		add(jlb_motatb);
		
		jtf_motatb = new JTextField();
		jtf_motatb.setColumns(10);
		jtf_motatb.setBounds(782, 35, 180, 30);
		add(jtf_motatb);
		
		
		jtb_thietbi.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRowIndex = jtb_thietbi.getSelectedRow();
				
				Object maTB = jtb_thietbi.getValueAt(selectedRowIndex, 0);
				Object tenTB = jtb_thietbi.getValueAt(selectedRowIndex, 1);
				Object motaTB = jtb_thietbi.getValueAt(selectedRowIndex, 2);
				
				jtf_matb.setText(maTB.toString());
				jtf_tentb.setText(tenTB.toString());
				jtf_motatb.setText(motaTB.toString());
				
			}
		});
		
		JButton btn_add = new JButton("Thêm");
		btn_add.setBounds(140, 100, 120, 36);
		add(btn_add);
		btn_add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	int maTB=0;
                    // Thu thập dữ liệu từ các thành phần nhập
                	try {
                		String maTBString = jtf_matb.getText().toString();
                		if (maTBString.isEmpty()) throw new RuntimeException();  
                		
                		maTB = Integer.parseInt(maTBString);               
                	}catch (NumberFormatException NFex) { 
                		JOptionPane.showMessageDialog(null, "Mã thiết bị phải là 1 chữ số");
                		NFex.printStackTrace();
                		return;
					}catch (RuntimeException RTex) {
						JOptionPane.showMessageDialog(null, "Mã thiết bị không được để trống");
						RTex.printStackTrace();
						return;
					}
                		
                    String TenTB = jtf_tentb.getText().toString();
                    String MoTa = jtf_motatb.getText().toString();
                    
                    // Kiểm tra các giá trị không được trống
                    if (TenTB.isEmpty() || MoTa.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                        return; // Thoát khỏi phương thức nếu có bất kỳ trường nào trống
                    }
                        
                    // kiem tra ma co bi trung khong        
                    if(checkmatrung(maTB)){
                       JOptionPane.showMessageDialog(null,"Mã bạn vừa nhập đã bị trùng");
                       return;
                    }

                    // Tạo đối tượng mới với dữ liệu đã thu thập
                    ThietBi thietBiMoi = new ThietBi();
                    thietBiMoi.setMaTB((int) maTB);
                    thietBiMoi.setTenTB(TenTB);
                    thietBiMoi.setMotaTB(MoTa);
                    // Gọi BLL để xử lý thêm vào cơ sở dữ liệu
                    try {
                        ThietBiBLL thietBiBLL = new ThietBiBLL();
                        thietBiBLL.addThietBi(thietBiMoi);
                        // Cập nhật lại bảng sau khi thêm
                        updateTableData(1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm xử lý: " + ex.getMessage());
                    }
                }
            });
                
		JButton btn_edit = new JButton("Sửa");
		btn_edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Lấy chỉ số hàng được chọn từ jtb_thietbi
				int selectedRow = jtb_thietbi.getSelectedRow();
				
				//Kiểm tra xem có hàng nào được chọn hay không
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa!","Lưu ý", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn sửa?", "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					//Chỉ lấy mã thiết bị từ bảng
					int maTB = (int) jtb_thietbi.getValueAt(selectedRow, 0);
					String tenTB = jtf_tentb.getText().toString() ;
					String motaTB = jtf_motatb.getText().toString();
                                        if (tenTB.isEmpty() || motaTB.isEmpty()) {
                                            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin thiết bị.");
                                            return; 
                                        }
					
					// Xử lý khi người dùng muốn sửa mã thiết bị
					try {
						if(maTB != Integer.parseInt(jtf_matb.getText().toString())) {
							jtf_matb.setText(maTB+"");
							JOptionPane.showMessageDialog(null, "Mã thiết bị là duy nhất và không thể sửa!","Lưu ý",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,"Không thể chỉnh sửa mã thiết bị!","Lưu ý",JOptionPane.ERROR_MESSAGE);
						jtf_matb.setText(maTB+"");
						ex.printStackTrace();
						return;
					}
					
					// Cập nhật thông tin
					try {
						ThietBiBLL tBiBLL = new ThietBiBLL();
						tBiBLL.updateThietBi(new ThietBi(maTB, tenTB, motaTB));
						
						updateTableData(1);
						JOptionPane.showMessageDialog(null, "Sửa thành công!");
					}catch (Exception exc) {
						exc.printStackTrace();
						JOptionPane.showMessageDialog(null, "Lỗi xảy ra: "+exc);
					}
				}
			}
		});
		btn_edit.setBounds(340, 100, 120, 36);
		add(btn_edit);
		
		JButton btn_delete = new JButton("Xoá");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			 // Lấy chỉ số của hàng được chọn
                int[] selectedRows = jtb_thietbi.getSelectedRows();

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
                        int maTB = (int) jtb_thietbi.getValueAt(selectedRows[i], 0);
                        
                        if(checkKhoaNgoai(maTB)) {
                        	JOptionPane.showMessageDialog(null, "Mã thiết bị đang được sử dụng ở bảng 'Thông tin sử dụng'");
                        	return;
                        }
                        
                        // Gọi phương thức BLL để xóa dữ liệu
                        try {
                            ThietBiBLL tbBLL = new ThietBiBLL();
                            tbBLL.deleteThietBi(maTB);
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
		btn_delete.setBounds(540, 100, 120, 36);
		add(btn_delete);
		
		JButton btn_excel = new JButton("Xuất Excel");
		btn_excel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//xuất file excel
				xuatExcel();
			}
		});
		btn_excel.setBounds(740, 100, 120, 36);
		add(btn_excel);
		
		JButton btn_adv_delete = new JButton("Xóa nâng cao");
		btn_adv_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Xóa nhiều thiết bị theo mã qui định
				XoaThietBiFrame();			}
		});
		btn_adv_delete.setBounds(540, 144, 120, 36);
		add(btn_adv_delete);
		
		JButton btn_NhapExcel = new JButton("Nhập Excel");
		btn_NhapExcel.setBounds(740, 146, 120, 35);
		btn_NhapExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Nhập file excel
				nhapExcel();
				
			}
		});
		add(btn_NhapExcel);
	}
        
	
	// ****
	//  Các hàm chuyên biệt dành cho các xử lý
	// ***
        public boolean isDeviceBorrowed(int maTB) {
        try {
            ThongTinsdBLL thongTinsdBLL = new ThongTinsdBLL();
            List<ThongTinsd> thongTinList = thongTinsdBLL.LoadThongTinsd(1); // Thay thế tham số với trang thích hợp nếu cần

            // Duyệt qua danh sách thống tin sử dụng
            for (ThongTinsd ttsd : thongTinList) {
                // Kiểm tra xem mã thiết bị có tồn tại trong bảng thống tin sử dụng và chưa được trả hay không
                if (ttsd.getMaTB().getMaTB() == maTB && ttsd.getTGTra() == null) {
                    JOptionPane.showMessageDialog(null, "Thiết bị đang được mượn");
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
	
	// Check mã đã tồn tại trong databse
	private boolean checkmatrung(int matb){     
		ThietBiDAL dal = new ThietBiDAL();
        List<ThietBi> dstb = dal.loadThietBi();
        for (ThietBi tb: dstb){ 
            if (matb == tb.getMaTB())
                return true;
        }
        return false;                           
        }
	
	// Cập nhật danh sách cho jTable_thietbi
	private void updateTableData(int page) {
        try {
            ThietBiBLL tbBLL = new ThietBiBLL();
            List<ThietBi> listThietbi = tbBLL.LoadThietBi(page); // Sửa tên phương thức cho đúng

            // Xóa dữ liệu hiện có trong bảng
            model.setRowCount(0);
            

            // Chuyển đổi dữ liệu thành mảng Object và thêm vào tableModel
            for (ThietBi tb : listThietbi) {
                Object[] row = new Object[]{
                    tb.getMaTB(),
                    tb.getTenTB(),
                    tb.getMotaTB()                   
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        
    }
	
	// Xuất file Excel
	private void xuatExcel() {
    	   ThietBiBLL tBll = new ThietBiBLL();
    	   try (XSSFWorkbook wordkbook = new XSSFWorkbook()) {
		        XSSFSheet sheet=wordkbook.createSheet("danhsachthietbi");
		        XSSFRow row =null;
		        Cell cell=null;
		        
		         // Lay danh sach va hien thi len excel
		        List<ThietBi> arr = tBll.getlistTB();

		         // >> Hang 1 << //
		        row=sheet.createRow(0);
		        //cell=row.createCell(0,CellType.STRING); 
		        //cell.setCellValue("STT");
		      
		        cell=row.createCell(0,CellType.STRING); 
		        cell.setCellValue("Mã thiết bị");
		        
		        cell=row.createCell(1,CellType.STRING); 
		        cell.setCellValue("Tên thiết bị");
		        
		        cell=row.createCell(2,CellType.STRING); 
		        cell.setCellValue("Mô tả");
		         // >> Ket thuc hang 1 << //
		        
		        for(int i=0; i<arr.size(); i++){
		        	 // >> Hang (1 + i) << //
		            row=sheet.createRow(1 + i);
		           
		            //cell=row.createCell(0,CellType.NUMERIC);
		            //cell.setCellValue(i+1);
		            
		            cell=row.createCell(0,CellType.STRING);
		            cell.setCellValue(arr.get(i).getMaTB());
		               
		            cell=row.createCell(1,CellType.STRING);
		            cell.setCellValue(arr.get(i).getTenTB());
		            
		            cell=row.createCell(2,CellType.STRING);
		            cell.setCellValue(arr.get(i).getMotaTB());
		                                  
		        }
	        
		        File f = new File("C://Users//HUYKIEN//danhsachthietbi.xlsx");
	        		try {
	        			FileOutputStream fis = new FileOutputStream(f);
	        			wordkbook.write(fis);
	        			JOptionPane.showMessageDialog(null, "Xuất file thành công!");
	        			fis.close();
	        		} 
	        		catch (FileNotFoundException ex) {
	        			JOptionPane.showMessageDialog(null, "Error: "+ex, "Loi", JOptionPane.ERROR_MESSAGE);
	        			ex.printStackTrace();
	        		}
	        
	        		catch (IOException ex) {
	        			JOptionPane.showMessageDialog(null, "Error: "+ex, "Loi", JOptionPane.ERROR_MESSAGE);
	        			ex.printStackTrace();
	        		}
	        		
	        
    	   } catch(Exception ex){
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Lỗi mở File!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
       }
       
	// Nhập file Excel
       private void nhapExcel() {
    	   DefaultTableModel model= (DefaultTableModel) jtb_thietbi.getModel();
           File excelFile;
           FileInputStream excelFIS = null;
           BufferedInputStream excelBIS = null;
           XSSFWorkbook excelImportToJTable = null;
           String defaultCurrentDirectoryPath = "C:\\Users\\HUYKIEN";
           JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
           excelFileChooser.setDialogTitle("Chọn File Excel");
           FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
           excelFileChooser.setFileFilter(fnef);
           int excelChooser = excelFileChooser.showOpenDialog(null);
           if (excelChooser == JFileChooser.APPROVE_OPTION) {
               try {
                   excelFile = excelFileChooser.getSelectedFile();
                   excelFIS = new FileInputStream(excelFile);                  
                   excelBIS = new BufferedInputStream(excelFIS);                  
                   excelImportToJTable = new XSSFWorkbook(excelBIS);                 
                   XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);
                   
                   for (int row = 1; row < excelSheet.getLastRowNum(); row++) {
                       XSSFRow excelRow = excelSheet.getRow(row);                    
                       int maTB = (int) excelRow.getCell(0).getNumericCellValue();
                       String tenTB = (String) excelRow.getCell(1).getStringCellValue();
                       String motaTB = (String) excelRow.getCell(2).getStringCellValue();
                       
                       if(!checkmatrung(maTB)) {
                    	   model.addRow(new Object[]{maTB, tenTB, motaTB});
                    	   
                    	   try {
                    		   ThietBiBLL tbll = new ThietBiBLL();
                    		   tbll.addThietBi(new ThietBi(maTB, tenTB, motaTB));
                    	   } catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Exception: " +e);
						}
                    	   //JOptionPane.showMessageDialog(null, "Đã bỏ qua các thiết bị trùng mã số");
                       }
                   }
                   
                   JOptionPane.showMessageDialog(null, "Nhập fie thành công!");
               } catch (IOException iOException) {
                   JOptionPane.showMessageDialog(null, iOException.getMessage());
               } catch (NullPointerException nullException) {
            	   JOptionPane.showMessageDialog(null, "Đã bỏ qua các hàng dữ liệu rỗng");
               } finally {
                   try {
                       if (excelFIS != null) {
                           excelFIS.close();
                       }
                       if (excelBIS != null) {
                           excelBIS.close();
                       }
                       if (excelImportToJTable != null) {
                           excelImportToJTable.close();
                       }
                   } catch (IOException iOException) {
                       JOptionPane.showMessageDialog(null, iOException.getMessage());
                   }
               }
           }
       }
       
       // Xóa nâng cao: một màn hình mới
       private void XoaThietBiFrame()  {
    	   JFrame f = new JFrame("Xóa theo mã qui định thiết bị");
    	   f.setSize(296, 125);
    	   f.setLocationRelativeTo(null);
    	   JPanel panel = new JPanel(null);
    	   
    	   JLabel lblNhap = new JLabel("Chọn mã để xóa: ");
    	   lblNhap.setBounds(40, 10, 100, 26);
    	   panel.add(lblNhap);
    	   
    	   //Đổ lựa chọn vào model, sau đó tạo 1 combboox lựa chọn mã qui định cần xóa
    	   DefaultComboBoxModel<String> dModel = new DefaultComboBoxModel<String>();   	   
    	   dModel.addElement("1. Micro"); 	//Micro
    	   dModel.addElement("2. Máy chiếu"); 	// Máy chiếu
    	   dModel.addElement("3. Máy ảnh"); 	// Máy ảnh
    	   dModel.addElement("4. Cassette"); 	// Casste
    	   dModel.addElement("5. Tivi");		
    	   dModel.addElement("6. Quạt đứng");	    	   
    	   JComboBox<String> cbb = new JComboBox<String>(dModel);
    	   cbb.setBounds(40,40,110,26);
    	   panel.add(cbb);
    	      	   
    	   JButton btXoa = new JButton("Xóa");
    	   btXoa.setBounds(160, 40, 70, 26);
    	   btXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String luachon = (String) dModel.getSelectedItem();
				switch(luachon) {
				case "1. Micro":
					xoaThietBinangcao("Micro");
					break;
				case "2. Máy chiếu":
					xoaThietBinangcao("Máy chiếu");
					break;
				case "3. Máy ảnh":
					xoaThietBinangcao("Máy ảnh");
					break;
				case "4. Cassette":
					xoaThietBinangcao("Cassette");
					break;
				case "5. Tivi":
					xoaThietBinangcao("Tivi");
					break;
				case "6. Quạt đứng":
					xoaThietBinangcao("Quạt đứng");
					break;
				}
						
				// Cập nhật dữ liệu cho bảng sau khi xóa thiết bị
				updateTableData(1);
			}
		});
    	   panel.add(btXoa);
    	   
    	   
    	   f.getContentPane().add(panel);
    	   f.setVisible(true);
       }
       
       private boolean checkKhoaNgoai(int maTB) {
    	   ThongTinsdBLL tBll = new ThongTinsdBLL();
    	   List<ThongTinsd> tList = tBll.LoaddsThongTinsd(); // Kiểm tra có khóa ngoại trong thongtinsudung không
    	   
    	   for(ThongTinsd tsd: tList) {
    		   if(tsd.getMaTB() != null) {
    			   if(maTB == tsd.getMaTB().getMaTB()) {
    		   			return true;
    		   
    			   }
    		   }
    	    }
    	   return false;
       }
       
       // Hàm được sử dụng trong XoaThietBiFrame
       private void xoaThietBinangcao(String TenThietBi) {
    	   //Danh sách các khóa ngoại không thể xóa
    	   List <Integer> KhoaNgoaiKhongTheXoa= new ArrayList<Integer>();
    	   
    	   boolean isTabelNotNull = true;	// Kiểm tra xem nếu Table jtb_thietbi rỗng
    	   ThietBiBLL tBll = new ThietBiBLL();
    	   List <ThietBi> tbi_CanXoa = tBll.getThietBifromName(TenThietBi);	// Lấy tất cả thiết bị có tên bao gồm "TenThietBi" 
    	   
    	   try {
    		   for(ThietBi tb: tbi_CanXoa) {
    			   // Check khóa ngoại ở bảng Thông tin sử dụng
    			   if(!checkKhoaNgoai(tb.getMaTB())) {
    				    isTabelNotNull = tBll.deleteThietBi(tb.getMaTB()); 
    			   }else {
    				   KhoaNgoaiKhongTheXoa.add(tb.getMaTB()); // 
    			   }
    		   }
    		   
    		   // Nếu check thấy danh sách khóa ngoại khác rỗng, hiển thị messege cho người dùng
    		   if(!KhoaNgoaiKhongTheXoa.isEmpty()) {
    			   JOptionPane.showMessageDialog(null, "Một số thiết bị không thể xóa do mã thiết bị đang là khóa ngoại ở bảng nghi khác  " + KhoaNgoaiKhongTheXoa.toString(),"Lưu ý", JOptionPane.INFORMATION_MESSAGE);    			   
    		   }   		   
    		   // Nếu dữ liệu Table rỗng
    		   if (!isTabelNotNull) {
    			   JOptionPane.showMessageDialog(null, "Dữ liệu bảng rỗng");
    		   }
    		   JOptionPane.showMessageDialog(null, "Xóa các bản nghi thành công");
    		   
    	   } catch(Exception exc) {
    		   JOptionPane.showMessageDialog(null, "Lỗi xảy ra khi xóa nâng cao: " +exc);
    		   exc.printStackTrace();
    	   }
       }
}
