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
import src.xupt.se.ttms.view.seat.SeatAddUI;

public class SeatEditUI extends SeatAddUI{

	public SeatEditUI(Seat seat) {
		initData(seat);
	}

	private void initData(Seat seat) {
		txtStudioId.setText(Integer.toString(seat.getStudioId()));
		txtRow.setText(Integer.toString(seat.getRow()));
		txtColumn.setText(Integer.toString(seat.getColumn()));
	}

	@Override
	protected void btnSaveClicked(){
		if (txtStudioId.getText() != null && txtRow.getText() != null
				&& txtColumn.getText() != null) {
			SeatSrv seatSrv = new SeatSrv();
			Seat seat=new Seat();
			seat.setStudioId(1); // 暂时填成0 ，后面要进行修改
			seat.setRow(Integer.parseInt(txtRow.getText()));// 待修改
			seat.setColumn(Integer.parseInt(txtColumn.getText()));  // 待修改

			seatSrv.modify(seat);
			this.setVisible(false);
			rst=true;
			getParent().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}
