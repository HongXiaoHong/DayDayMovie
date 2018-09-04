package com.hong.bean;

import java.util.Map;

import com.hong.utils.GetPropertiesUtil;

public class Res {

	private String user;      //��ֵ��ʾ�û���: ��: andy, candy ..
	private String movieId;   //��ֵ��ʾӰƬID��
	private String type;      //��ֵ��ʾ����: video / img 
	private String extName;   //��ֵ��ʾ��չ��: mp4 / flv / jpg / png ..
	static Map<String, String> config = GetPropertiesUtil.getConInfo("config.property");
	
	public String getRealPath(){
		
		//��ӳ����ȡ�û���·��
		//Ҳ���Ǵ��ͼƬ�Լ���Ƶ��·��
		String basePath = config.get("path");
		String realPath = null;
		if("img".equals(type)){
			//�����ͼƬ���ͣ���ƴ������
			realPath = basePath+user+"\\"+"image\\"+
					movieId+"."+extName;	
		} else{
			//�������Ƶ���ͣ���ƴ������
			realPath = basePath+user+"\\"+"video\\"+
					movieId+"."+extName;	
		}
		return realPath;
	}
	public Res() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Res(String user, String movieId, String type, String extName) {
		super();
		this.user = user;
		this.movieId = movieId;
		this.type = type;
		this.extName = extName;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExtName() {
		return extName;
	}
	public void setExtName(String extName) {
		this.extName = extName;
	}
	@Override
	public String toString() {
		return "Res [user=" + user + ", movieId=" + movieId + ", type=" + type
				+ ", extName=" + extName + "]";
	}
	
}
