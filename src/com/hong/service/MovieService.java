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
			
			//��ȡ��������Ϣ
			RequestContext ctx = new ServletRequestContext(req);
			//��ȡ����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//�������ļ����Ϊ100M
			//������ʱ�ļ�
			factory.setRepository(new File("F:\\temp"));
			factory.setSizeThreshold(100*1024*1024);
			//�õ����ĵ��ϴ��ࣻ
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
			//��ȡ�ļ���
				if(fileItem==null){
					System.out.println("fileItemΪ��");
				} else{
					name = ((User)(req.getSession(false).getAttribute("user"))).getName();
					uploader = ((User)(req.getSession(false).getAttribute("user"))).getId();
					movie.setUploader(uploader);
					//�ж�����ļ������ڣ��������ļ�
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
									"�ļ���:"+
											"�ļ�����"+item.getName()+
											"���е�����Ϊ"+item.getFieldName()
											
											
									);
							//�Ȼ�ȡ�ļ���
							fileName = item.getName();
							//ͨ���ж϶��ַ�������ƴ��
							//ƴ�Ӵ�����
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
						//�����ļ�
						fatherFile = new File(realPath);
						//���Ŀ¼�����ھʹ���Ŀ¼
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
						//���ļ�д������`
						try {
							item.write(file);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						} else{
							//���ڱ�������ʹ����enctype='multipart/form-data'
							//�����޷�ʹ����ͨ�ķ�ʽҲ����ͨ��request��getParameter���л�ȡ��
							//��ʱ����ֻ��ͨ��fileItem��ȡ���е�����
							try {
								System.out.println(
										"���ļ���\n"+
												"���е�����Ϊ"+item.getFieldName()+
												"����ֵΪ��"+item.getString("UTF-8")
										);
							} catch (UnsupportedEncodingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Class<? extends Movie> clazz = movie.getClass();
							try {
								Field field = clazz.getDeclaredField(item.getFieldName());
								field.setAccessible(true);
								//���������ñ��룬��������ã������ȡ���ݵ�ʱ���Ĭ����gbk��ʽ
								//Ҳ�����ĵ�Ŀǰ�ı�����н���
								//�������Ǳ����ʱ��ʹ�õ���utf-8
								//����������Ҫ���������ñ����������
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
		//���ö�����Ҫ���õĲ���
		
		//���ò���Ȩ��
		//���õ�һ������Ϊ����Ա����
		//�ڶ�������Ϊ�����û�����
		movie.setIsEnabled("11");
		//���õ���
		//�����շ�ϵͳ��ʹ��
		//����û���õ���ֱ������Ϊ0����
		movie.setCostPoint(0);
		
		return dao.add(movie);
	}
	//ͨ���û�����ȡ��Ӱ�б�
	public List<Movie> getMovieByUploader(String userId){
		return dao.getMovieByUploader(userId);
	}
	//ͨ��id��ȡ��Ӱ
	public Movie getMovieById(String id){
		return dao.getMovieById(id);
	}
	//�ı�movie���õ����ݽ�������
	public boolean updateMovie(Movie movie, String id){
		return dao.update(movie,id);
	}
	//ͨ��IDɾ�����ݿ��еļ�¼
	public boolean deleteById(String id){
		return dao.delete(id);
	}
	//ͨ��IDɾ�����ݿ��еļ�¼
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
					//�ж�����ļ������ڣ��������ļ�
					String UUID = UUIDUtil.getUUID();
					movie.setId(UUID);
					Map<String, String> map = GetPropertiesUtil.getConInfo("config.property");
					String basePath = map.get("path");
					String fileName = null;
					File file = null;
					File fatherFile = null;
					String realPath = null;
					for(int i=0; i<uploadFile.length; i++){
							//�Ȼ�ȡ�ļ���
							fileName = uploaderFileName[i];
							//ͨ���ж϶��ַ�������ƴ��
							//ƴ�Ӵ�����
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
						//�����ļ�
						fatherFile = new File(realPath);
						//���Ŀ¼�����ھʹ���Ŀ¼
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
						//���ļ�д������
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
					
		//���ö�����Ҫ���õĲ���
		
		//���ò���Ȩ��
		//���õ�һ������Ϊ����Ա����
		//�ڶ�������Ϊ�����û�����
		movie.setIsEnabled("11");
		//���õ���
		//�����շ�ϵͳ��ʹ��
		//����û���õ���ֱ������Ϊ0����
		movie.setCostPoint(0);
		
		return dao.add(movie);
	}
	
}
