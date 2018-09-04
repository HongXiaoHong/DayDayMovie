package com.hong.bean;

import java.util.Map;

import com.hong.utils.GetPropertiesUtil;

public class Res {

	private String user;      //此值表示用户名: 如: andy, candy ..
	private String movieId;   //此值表示影片ID。
	private String type;      //此值表示类型: video / img 
	private String extName;   //此值表示扩展名: mp4 / flv / jpg / png ..
	static Map<String, String> config = GetPropertiesUtil.getConInfo("config.property");
	
	public String getRealPath(){
		
		//从映射中取得基础路径
		//也就是存放图片以及视频的路劲
		String basePath = config.get("path");
		String realPath = null;
		if("img".equals(type)){
			//如果是图片类型，就拼接数据
			realPath = basePath+user+"\\"+"image\\"+
					movieId+"."+extName;	
		} else{
			//如果是视频类型，就拼接数据
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
