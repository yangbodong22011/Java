package src.xupt.se.ttms.view.seat;

import javax.swing.JDialog;




import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


//import view.studioUI.ImageJPanel;
import src.xupt.se.ttms.model.Seat;
import src.xupt.se.ttms.service.SeatSrv;
import src.xupt.se.ttms.view.tmpl.*;

public class SeatAddUI extends PopUITmpl implements ActionListener {

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblStudioId, lblRow, lblColumn;
	protected JTextField txtStudioId, txtRow, txtColumn;

	public SeatAddUI() {
	}

	@Override
	protected void initContent(){
		this.setTitle("添加数据字典");

		lblStudioId = new JLabel("排列顺序：");
		lblStudioId.setBounds(60, 30, 80, 30);
		contPan.add(lblStudioId);
		txtStudioId = new JTextField();
		txtStudioId.setBounds(150, 30, 120, 30);
		contPan.add(txtStudioId);

		lblRow = new JLabel("字典名称：");
		lblRow.setBounds(60, 80, 50, 30);
		contPan.add(lblRow);
		txtRow = new JTextField();
		txtRow.setBounds(150, 80, 120, 30);
		contPan.add(txtRow);

		lblColumn = new JLabel("字典值：");
		lblColumn.setBounds(60, 130, 90, 30);
		contPan.add(lblColumn);
		txtColumn = new JTextField();
		txtColumn.setBounds(150, 130, 120, 30);
		contPan.add(txtColumn);

		btnSave = new JButton("保存");

		btnSave.addActionListener(this);
		btnSave.setBounds(60, 220, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(180, 220, 60, 30);
		contPan.add(btnCancel);

		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
				"files/imgs/pencil.jpg").getImage());
		imageJP.setBounds(360, 160, 100, 100);
		imageJP.setLayout(null);
		this.add(imageJP);
	}
	
	
	public boolean getReturnStatus(){
		   return rst;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.dispose();
			getParent().setVisible(true);

		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}

	}
	
	protected void btnSaveClicked(){
		if (txtStudioId.getText() != null && txtRow.getText() != null
				&& txtColumn.getText() != null) {
			SeatSrv seatSrv = new SeatSrv();
			Seat seat=new Seat();
			seat.setStudioId(Integer.parseInt(txtStudioId.getText()));
			seat.setRow(Integer.parseInt(txtRow.getText()));
			seat.setColumn(Integer.parseInt(txtColumn.getText()));

			seatSrv.add(seat);
			this.setVisible(false);
			rst=true;
			getParent().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
}
