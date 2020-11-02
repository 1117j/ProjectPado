package member.model;

import java.sql.Date;

public class Member {

	private int midx;
	private String mid;
	private String mpw;
	private String mname;
	private String mphonenumber;
	private String mphoto;
	private Date mregdate;
	
	
	public Member() {
	}


	public Member(int midx, String mid, String mpw, String mname, String mphonenumber, String mphoto, Date mregdate) {
		this.midx = midx;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mphonenumber = mphonenumber;
		this.mphoto = mphoto;
		this.mregdate = mregdate;
	}


	public int getMidx() {
		return midx;
	}


	public void setMidx(int midx) {
		this.midx = midx;
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	public String getMpw() {
		return mpw;
	}


	public void setMpw(String mpw) {
		this.mpw = mpw;
	}


	public String getMname() {
		return mname;
	}


	public void setMname(String mname) {
		this.mname = mname;
	}


	public String getMphonenumber() {
		return mphonenumber;
	}


	public void setMphonenumber(String mphonenumber) {
		this.mphonenumber = mphonenumber;
	}


	public String getMphoto() {
		return mphoto;
	}


	public void setMphoto(String mphoto) {
		this.mphoto = mphoto;
	}


	public Date getMregdate() {
		return mregdate;
	}


	public void setMregdate(Date mregdate) {
		this.mregdate = mregdate;
	}


	@Override
	public String toString() {
		return "Member [midx=" + midx + ", mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mphonenumber="
				+ mphonenumber + ", mphoto=" + mphoto + ", mregdate=" + mregdate + "]";
	}
	
	
}
