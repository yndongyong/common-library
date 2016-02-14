package org.yndongyong.common.net;

import java.util.List;

/**
 * 返回数据 基类
 * @param <T>
 */
public class ApiResponse<T> {

	private boolean succeed;// true 为成功
    private int code; // 返回码
	private String msg;// 返回信息
    private T data; //返回对象
	private List<T> datas; //返回对象列表
	
	public ApiResponse(){}
	/**
	 * 构造函数，初始化code和msg
	 * @param succeed
	 * @param msg
	 */
	public ApiResponse(boolean succeed, String msg) {
		this.succeed = succeed;
		this.msg = msg;
	}

	// 判断结果是否成功
	public boolean isSuccess() {
		return succeed == true;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
     public String toString() {
        return "ApiResponse{" +
                "succeed=" + succeed +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", datas=" + datas +
                '}';
    }
}
