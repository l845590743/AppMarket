package com.lzm.appmarket.comment;

/**
 * 
 * @author 
 * @version 
 */
public class ResHeadAndBody {
	@Override
	public String toString() {
		return "ResHeadAndBody{" +
				"header=" + header +
				", body=" + body +
				", page=" + page +
				", retMessage='" + retMessage + '\'' +
				'}';
	}

	/**
	 * 服务器返回的header
	 */
	private ResponseHeader header;

	/**
	 * 服务器返回的body
	 */
	private Object body;
	
	/**
	 * 服务器返回的记录分页信息
	 */
	private Object page;

	private String retMessage;

	public String getRetMessage() {
		return retMessage;
	}

	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}


	public ResponseHeader getHeader() {
		return header;
	}

	public void setHeader(ResponseHeader header)
	{
		this.header = header;
	}
	
	public Object getBody() {
		return body;
	}
	
	public void setBody(Object body) {
		this.body = body;
	}
	
	public Object getPage() {
		return page;
	}
	
	public void setPage(Object value) {
		this.page = value;
	}
}
