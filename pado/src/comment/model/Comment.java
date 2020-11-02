package comment.model;

import java.util.Date;

public class Comment {
	private int cidx;
	private String bid;
	private int bidx;
	private String cmessage;
	private Date regdate;
	public int getCidx() {
		return cidx;
	}
	public void setCidx(int cidx) {
		this.cidx = cidx;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public int getBidx() {
		return bidx;
	}
	public void setBidx(int bidx) {
		this.bidx = bidx;
	}
	public String getCmessage() {
		return cmessage;
	}
	public void setCmessage(String cmessage) {
		this.cmessage = cmessage;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "Comment [cidx=" + cidx + ", bid=" + bid + ", bidx=" + bidx + ", cmessage=" + cmessage + ", regdate="
				+ regdate + "]";
	}
	
	public Comment(int cidx, String bid, int bidx, String cmessage, Date regdate) {
		this.cidx = cidx;
		this.bid = bid;
		this.bidx = bidx;
		this.cmessage = cmessage;
		this.regdate = regdate;
	}
	public Comment() {
	}
	
	

	
	
	
	
}
