package test.innerclas;

public class Button {
	private String sTitle;
	private OnClickListener onClickListener;
		
	protected Button(String sTitle) {
		this.sTitle = sTitle;
	}
	
	protected String getsTitle() {
		return sTitle;
	}

	protected void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	public void onClick() {
		this.onClickListener.onClick(this.sTitle);
	}


	//inner interface
	public interface OnClickListener {
		void onClick(String sTitle);		
	}
}
