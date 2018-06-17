package test.projectmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AboutController {

	@FXML
	private Label aboutLabel;
	
	public void initialize() {
		String about = "Cám ơn bạn đã sử dụng phần mềm QUẢN LÝ DỰ ÁN của chúng tôi.\n"
				+ "Phần mềm này giúp các bạn quản lý được dự án, khách hàng và nhân viên của dự án\n"
				+ "Rất mong nhận được ý kiến đóng góp quý báu của các bạn nhằm giúp chúng tôi cải thiện chất lượng phần mềm.\n\n"
				+ "Mọi ý kiến đóng góp vui lòng gửi về hòm thư: \nchinhvq@gmail.com\n" + "CHÂN THÀNH CÁM ƠN!!!";

		aboutLabel.setText(about);
	}
	
}
