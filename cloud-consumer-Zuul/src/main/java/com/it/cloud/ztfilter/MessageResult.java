package com.it.cloud.ztfilter;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.Collection;

public class MessageResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String OK = "0";

	public static final String ERROR = "1";

	public static final MessageResult<Object> SUCCESS = new MessageResult<Object>("0", "成功");

	private Object object;

	private Long totalCount;

	private String returnMessage;

	private String returnCode;

	public MessageResult() {
		this((Page) null);
	}

	public MessageResult(String returnCode, String returnMessage) {
		this((Page) null);
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}

	public MessageResult(Collection<T> dataList) {
		this(dataList, dataList != null ? dataList.size() : 0L);
	}

	public MessageResult(Long totalCount) {
		this((Collection) null, totalCount);
	}

	public MessageResult(Collection<T> dataList, Long totalCount) {
		this.returnMessage = "成功";
		this.totalCount = totalCount;
		this.returnCode = "0";
		this.object = "";
	}

	public MessageResult(Page<T> pageResult) {
		this((Collection) (pageResult != null ? pageResult.getResult() : null));
		this.setTotalCount(pageResult != null ? (Long) pageResult.getTotal() : 0);
	}

	public Long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public String getReturnMessage() {
		return this.returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getReturnCode() {
		return this.returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public Object getObject() {
		return this.object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
