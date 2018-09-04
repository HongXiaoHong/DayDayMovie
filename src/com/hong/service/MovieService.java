package com.hong.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.hong.bean.Movie;
import com.hong.bean.Page;
import com.hong.bean.User;
import com.hong.dao.MovieDao;
import com.hong.utils.GetPropertiesUtil;
import com.hong.utils.UUIDUtil;

public class MovieService {

	private MovieDao dao = new MovieDao();
	public boolean upload(HttpServletRequest req, Movie movie){
		boolean isMul = ServletFileUpload.isMultipartContent(req);
		System.out.println("isMul:"+isMul);
		String name = null;
		String uploader = null;
		if(isMul){
			
			//获取上下文信息
			RequestContext ctx = new ServletRequestContext(req);
			//获取工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置总文件最大为100M
			//设置临时文件
			factory.setRepository(new File("F:\\temp"));
			factory.setSizeThreshold(100*1024*1024);
			//得到核心的上传类；
			ServletFileUpload upload = 
					new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			upload.setFileSizeMax(20*1024*1024);
			System.out.println("upload:"+upload);
			List<FileItem> fileItem= null;
			
			
				try {
					fileItem = upload.parseRequest(ctx);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
				System.out.println("fileItem:"+fileItem);
			//获取文件项
				if(fileItem==null){
					System.out.println("fileItem为空");
				} else{
					name = ((User)(req.getSession(false).getAttribute("user"))).getName();
					uploader = ((User)(req.getSession(false).getAttribute("user"))).getId();
					movie.setUploader(uploader);
					//判断如果文件不存在，将创建文件
					String UUID = UUIDUtil.getUUID();
					movie.setId(UUID);
					Map<String, String> map = GetPropertiesUtil.getConInfo("config.property");
					String basePath = map.get("path");
					String fileName = null;
					File file = null;
					File fatherFile = null;
					String realPath = null;
					for(FileItem item: fileItem){
						if(!item.isFormField()){
							System.out.println(
									"文件项:"+
											"文件名："+item.getName()+
											"表单中的名字为"+item.getFieldName()
											
											
									);
							//先获取文件名
							fileName = item.getName();
							//通过判断对字符串进行拼接
							//拼接处数据
							String extName = null;
						if( "videoFile".equals(item.getFieldName())){
							
							extName = fileName.substring(fileName.indexOf(".")+1);
							movie.setExtName(extName);
							realPath = basePath+name+"\\video";
						}
						if( "imgFile".equals(item.getFieldName())){
							
							extName = fileName.substring(fileName.indexOf(".")+1);
							movie.setImgExtName(extName);
							realPath = basePath+name+"\\image";
						}
						//创建文件
						fatherFile = new File(realPath);
						//如果目录不存在就创建目录
						if(!fatherFile.exists()){
							fatherFile.mkdirs();
						}
						
						
						file = new File(fatherFile,UUID+"."+extName);
						if(!file.exists()){
							try {
								file.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						//将文件写到本地`
						try {
							item.write(file);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						} else{
							//由于表单数据中使用了enctype='multipart/form-data'
							//导致无法使用普通的方式也就是通过request的getParameter进行获取数
							//此时我们只能通过fileItem获取表单中的数据
							try {
								System.out.println(
										"非文件：\n"+
												"表单中的名字为"+item.getFieldName()+
												"表单的值为："+item.getString("UTF-8")
										);
							} catch (UnsupportedEncodingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Class<? extends Movie> clazz = movie.getClass();
							try {
								Field field = clazz.getDeclaredField(item.getFieldName());
								field.setAccessible(true);
								//在这里设置编码，如果不设置，这里获取数据的时候回默认以gbk方式
								//也就是文档目前的编码进行解码
								//但是我们编码的时候使用的是utf-8
								//这里我们需要在这里设置编码进行设置
								field.set(movie, item.getString("UTF-8"));
							} catch (NoSuchFieldException | SecurityException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							
							
						}
					}
				}
		}
		//设置多余需要设置的参数
		
		//设置播放权限
		//设置第一个参数为管理员允许
		//第二个参数为允许用户播放
		movie.setIsEnabled("11");
		//设置点数
		//用于收费系统的使用
		//这里没有用到，直接设置为0即可
		movie.setCostPoint(0);
		
		return dao.add(movie);
	}
	//通过用户名获取电影列表
	public List<Movie> getMovieByUploader(String userId){
		return dao.getMovieByUploader(userId);
	}
	//通过id获取电影
	public Movie getMovieById(String id){
		return dao.getMovieById(id);
	}
	//改变movie引用的数据进而更新
	public boolean updateMovie(Movie movie, String id){
		return dao.update(movie,id);
	}
	//通过ID删除数据库中的记录
	public boolean deleteById(String id){
		return dao.delete(id);
	}
	//通过ID删除数据库中的记录
	public boolean updateIsEnabledd(String id, String isEnabled){
		return dao.updateIsEnabled(id, isEnabled);
	}
	public List<Movie> getMovieByUploader(String userId, Page<Movie> page) {
		return dao.getMovieByUploader(userId, page);
	}
	public List<Movie> search(String userId, String keyWord) {
		
		return dao.search(userId, keyWord);
	}
	public boolean upload(HttpServletRequest req, Movie movie, File[] uploadFile,
			String[] uploaderContentType, String[] uploaderFileName) {
		
					String name = ((User)(req.getSession(false).getAttribute("user"))).getName();
					String uploader = ((User)(req.getSession(false).getAttribute("user"))).getId();
					movie.setUploader(uploader);
					//判断如果文件不存在，将创建文件
					String UUID = UUIDUtil.getUUID();
					movie.setId(UUID);
					Map<String, String> map = GetPropertiesUtil.getConInfo("config.property");
					String basePath = map.get("path");
					String fileName = null;
					File file = null;
					File fatherFile = null;
					String realPath = null;
					for(int i=0; i<uploadFile.length; i++){
							//先获取文件名
							fileName = uploaderFileName[i];
							//通过判断对字符串进行拼接
							//拼接处数据
							String extName = null;
						if( uploaderContentType[i].contains("video")){
							
							extName = fileName.substring(fileName.indexOf(".")+1);
							movie.setExtName(extName);
							realPath = basePath+name+"\\video";
						}
						if( uploaderContentType[i].contains("image")){
							
							extName = fileName.substring(fileName.indexOf(".")+1);
							movie.setImgExtName(extName);
							realPath = basePath+name+"\\image";
						}
						//创建文件
						fatherFile = new File(realPath);
						//如果目录不存在就创建目录
						if(!fatherFile.exists()){
							fatherFile.mkdirs();
						}
						
						
						file = new File(fatherFile,UUID+"."+extName);
						if(!file.exists()){
							try {
								file.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						//将文件写到本地
						int count = 0;
						byte[] buffer = new byte[1024];
						FileInputStream in = null;
						FileOutputStream out = null;
						try {
							in = new FileInputStream(uploadFile[i]);
							out = new FileOutputStream(file);
							while((count=in.read(buffer))>0){
								out.write(buffer, 0, count);
							}
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								if(in!=null){
									in.close();
									in = null;
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
							try {
								if(out!=null){
									out.close();
									out = null;
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						
							
						}
					
		//设置多余需要设置的参数
		
		//设置播放权限
		//设置第一个参数为管理员允许
		//第二个参数为允许用户播放
		movie.setIsEnabled("11");
		//设置点数
		//用于收费系统的使用
		//这里没有用到，直接设置为0即可
		movie.setCostPoint(0);
		
		return dao.add(movie);
	}
	
}
