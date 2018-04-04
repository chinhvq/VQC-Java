package arrraylist.mobiphone;

import java.util.ArrayList;
import java.util.Arrays;

public class MobilePhone {
	ArrayList<Contact> listContact = new ArrayList<Contact>();

	protected void ThemTaiKhoan(Contact contact) {
		if (contact.getsPhoneName().equals("")) {
			System.out.println("\nTen tai khoan khong hop le\n" + "VUI LONG NHAP LAI");
		} else if (contact.getsPhoneNumber().equals("")) {
			System.out.println("\nSo dien thoai khong hop le\n" + "VUI LONG NHAP LAI");
		} else if (this.KiemTraTenBoolean(contact.getsPhoneName())) {
			System.out.println("\nTen Tai Khoan Da Ton Tai Trong Danh Ba - Khong The Them Moi");
		} else if (this.KiemTraSoDienThoai(contact.getsPhoneNumber())) {
			System.out.println("\nSo Dien thoai Da Ton Tai Trong Danh Ba - Khong The Them Moi");
		} else {
			listContact.add(contact);
			System.out.println("\nTai Khoan\t\"" + contact.getsPhoneName() + "\"\t da duoc them vao danh ba thanh cong" );
		}
	}

	protected void TimTenTaiKhoan(String sTenTaiKhoan) {
		if (sTenTaiKhoan.equals("")) {
			System.out.println("\nTen tai khoan khong hop le\n" + "VUI LONG NHAP LAI");
		} else if (KiemTraTenInt(sTenTaiKhoan) != -1) {
			System.out.println("\nTen Tai Khoan\t\"" + sTenTaiKhoan + "\"\tSo Dien Thoai\t\"" + LaySoDienThoai(sTenTaiKhoan) +"\"");
		} else {
			System.out.println("\nKhong tim thay ten\t\"" + sTenTaiKhoan + "\"\ttrong danh ba");
		}
	}

	protected void TimSoDienThoai(String sSoDienThoai) {
		if (sSoDienThoai.equals("")) {
			System.out.println("\nSo dien thoai khong hop le\n" + "VUI LONG NHAP LAI");
		} else if (KiemTraSoDienThoai(sSoDienThoai)) {
			String sTenTaiKhoan = LayTenTaiKhoan(sSoDienThoai);
			System.out.println("\nTen Tai Khoan\t\"" + sTenTaiKhoan + "\"\tSo Dien Thoai\t\"" + sSoDienThoai +"\"");
		} else {
			System.out.println("\nKhong tim thay So Dien Thoai\t\"" + sSoDienThoai + "\" trong danh ba");
		}
	}

	protected void CapNhat(String sTenTaiKhoan, String sNewTenTaiKhoan) {
		if (sTenTaiKhoan.equals("")) {
			System.out.println("\nTen tai khoan khong hop le\n" + "VUI LONG NHAP LAI");
		} else if (sNewTenTaiKhoan.equals("")) {
			System.out.println("\nTen tai khoan moi khong hop le\n" + "VUI LONG NHAP LAI");
		} else if (KiemTraTenBoolean(sNewTenTaiKhoan)) {
			System.out.println("\nTen tai khoan moi da ton tai trong danh ba\n" + "VUI LONG NHAP");
		} else if (!KiemTraTenBoolean(sTenTaiKhoan)) {
			System.out.println("\nKhong tim thay ten\t\"" + sTenTaiKhoan + "\" trong danh ba");
		} else {
			CapNhatTaiKhoan(sTenTaiKhoan, sNewTenTaiKhoan);
		}
	}

	private void CapNhatTaiKhoan(String sTenTaiKhoan, String sNewTenTaiKhoan) {
		for (int i = 0; i < listContact.size(); i++) {
			Contact contact = listContact.get(i);
			if (contact.getsPhoneName().equalsIgnoreCase(sTenTaiKhoan)) {
				contact = Contact.TaoTaiKhoan(sNewTenTaiKhoan, contact.getsPhoneNumber());
				listContact.set(KiemTraTenInt(sTenTaiKhoan), contact);
				System.out.println(
						"\nTen tai khoan cu\t\"" + sTenTaiKhoan + "\"\tda duoc cap nhat thanh\t\"" + sNewTenTaiKhoan + "\"");
				System.out.println("Thong tin tai khoan moi:\tTen tai khoan\t\"" + sNewTenTaiKhoan + "\"\tSo dien thoai\t\""
						+ contact.getsPhoneNumber() + "\"");
				break;
			}
		}

	}

	protected void XoaTenTaiKhoan(String sTenTaiKhoan) {
		if (sTenTaiKhoan.equals("")) {
			System.out.println("\nTen tai khoan khong hop le\n" + "VUI LONG NHAP LAI");
		} else if (!KiemTraTenBoolean(sTenTaiKhoan)) {
			System.out.println("\nKhong tim thay ten\t\"" + sTenTaiKhoan + "\" trong danh ba");
		} else {
			listContact.remove(KiemTraTenInt(sTenTaiKhoan));
			System.out.println("\nTen tai khoan\t\"" + sTenTaiKhoan + "\"\tda bi xoa khoi danh ba");
		}
	}
	
	protected void XoaSoDienThoai(String sSoDienThoai) {
		if (sSoDienThoai.equals("")) {
			System.out.println("\nSo dien thoai khong hop le\n" + "VUI LONG NHAP LAI");
		} else if (!KiemTraSoDienThoai(sSoDienThoai)) {
			System.out.println("\nKhong tim thay So Dien Thoai\t\"" + sSoDienThoai + "\" trong danh ba");
		} else {
			listContact.remove(KiemTraTenInt(LayTenTaiKhoan(sSoDienThoai)));
			System.out.println("\nSo Dien Thoai\t\"" + sSoDienThoai + "\"\tda bi xoa khoi danh ba");
		}
	}
	
	protected void XemDanhBa() {
		if (listContact.isEmpty()) {
			System.out.println("\nDanh ba hien chua co tai khoan nao");
		} else {
			HienThiListMobiPhone();
		}
	}

	private boolean KiemTraTenBoolean(String sTenTaiKhoan) {
		for (Contact contact : listContact) {
			if (contact.getsPhoneName().equalsIgnoreCase(sTenTaiKhoan)) {
				return true;
			}
		}

		return false;
	}

	private int KiemTraTenInt(String sTenTaiKhoan) {
		for (int i = 0; i < listContact.size(); i++) {
			Contact contact = listContact.get(i);
			if (contact.getsPhoneName().equalsIgnoreCase(sTenTaiKhoan)) {
				return i;
			}
		}

		return -1;
	}

	private boolean KiemTraSoDienThoai(String sSoDienThoai) {
		for (Contact contact : listContact) {
			if (contact.getsPhoneNumber().equalsIgnoreCase(sSoDienThoai)) {
				return true;
			}
		}

		return false;
	}

	private String LayTenTaiKhoan(String sSoDienThoai) {
		for (Contact contact : listContact) {
			if(contact.getsPhoneNumber().equalsIgnoreCase(sSoDienThoai)) {
				return contact.getsPhoneName();
			}
		}

		return null;
	}

	private String LaySoDienThoai(String sTenTaiKhoan) {
//		for (int i = 0; i < listContact.size(); i++) {
//			Contact contact = listContact.get(i);
//			if (contact.getsPhoneName().equalsIgnoreCase(sTenTaiKhoan)) {
//				return contact.getsPhoneNumber();
//			}
//		}
		
		for (Contact contact : listContact) {
			if(contact.getsPhoneName().equalsIgnoreCase(sTenTaiKhoan)) {
				return contact.getsPhoneNumber();
			}
		}

		return null;
	}

	private void HienThiListMobiPhone() {
		System.out.println();
		listContact.forEach(contact -> System.out.println(
				"Ten Tai Khoan\t\"" + contact.getsPhoneName() + "\"\tSo Dien Thoai\t" + contact.getsPhoneNumber() + "\""));		
		System.out.println("\nBan dang co\t\"" + listContact.size() + "\" tai khoan trong danh ba.");		
	}
}
