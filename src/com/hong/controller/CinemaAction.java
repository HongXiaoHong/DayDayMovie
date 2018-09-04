package com.hong.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hong.bean.Category;
import com.hong.bean.Movie;
import com.hong.bean.Page;
import com.hong.bean.User;
import com.hong.service.CategoryService;
import com.hong.service.MovieService;
import com.hong.utils.GetPropertiesUtil;

//cinema有电影的意思

public class CinemaAction {

	
	private File[] uploader;
	private String[] uploaderContentType;
	private String[] uploaderFileName;
	private Movie movie;
	private Page<Movie> page;
	MovieService service = new MovieService();
	CategoryService catService = new CategoryService();
	static Map<String, String> config = GetPropertiesUtil.getConInfo("config.property");
	private HttpServletRequest req = ServletActionContext.getRequest();
	
	public Page<Movie> getPage() {return page;}
	public void setPage(Page<Movie> page) {this.page = page;}
	//拦截器通过该方法设置movie中的值
	public Movie getMovie() {return movie;}
	public void setMovie(Movie movie) {this.movie = movie;}
	//上传多文件
	public File[] getUploader() {return uploader;}
	public void setUploader(File[] uploader) {this.uploader = uploader;}
	public String[] getUploaderContentType() {return uploaderContentType;}
	public void setUploaderContentType(String[] uploaderContentType) {this.uploaderContentType = uploaderContentType;}
	public String[] getUploaderFileName() {return uploaderFileName;}
	public void setUploaderFileName(String[] uploaderFileName) {this.uploaderFileName = uploaderFileName;}
	
	public String upload() {
		System.out.println("进入上传的方法");
		for(String temp: uploaderFileName){
			System.out.println("文件名为:"+temp);
		}
		System.out.println("进入upload方法时候的movie为;"+movie);
		String result = "fail";
		//通过service层的方法实现上传
		if (service.upload(req, movie, uploader, uploaderContentType, uploaderFileName)) {
			result = "success";
		}
		return result;
	}

	public String toUploadPage() {
		System.out.println("进入上传的页面");
		// 我们需要在跳转以前将电影类型的数据
		// 这里我使用一个list集合将数据库中的数据取出
		// 放置到request域中
		List<Category> categories = catService.getAllCategory();
		req.setAttribute("categories", categories);
		return "success";
	}
	

	public String list() {
		User user = (User) req.getSession(false).getAttribute("user");
		
		String numString = req.getParameter("page");
		int num = 1;
		if(numString!=null){
			num = Integer.parseInt(numString);
		}
		String userId = user.getId();
		List<Movie> allMovieList = service.getMovieByUploader(userId);
		Page<Movie> page = new Page<Movie>();
		page.setParameters(allMovieList.size(), num);
		page.calcurate();
		
		
		List<Movie> movieList = service.getMovieByUploader(userId, page);
		
		
		req.setAttribute("movieList", movieList);
		req.setAttribute("mvPage", page);
		// 待定---判断改变路径或者其他逻辑
		return "success";
	}
	
	public String search() {
		//String path = "/";
		User user = (User) req.getSession(false).getAttribute("user");
		String userId = user.getId();
		String keyWord = page.getKeyWord();
		System.out.println("keyWord："+keyWord);
		List<Movie> movieList = service.search(userId, keyWord);
/*		User user = (User) req.getSession(false).getAttribute("user");
		
		String numString = req.getParameter("page");
		int num = 1;
		if(numString!=null){
			num = Integer.parseInt(numString);
		}
		String userId = user.getId();
		List<Movie> allMovieList = service.getMovieByUploader(userId);
		Page<Movie> page = new Page<Movie>();
		page.setParameters(allMovieList.size(), num);
		page.calcurate();
		
		
		List<Movie> movieList = service.getMovieByUploader(userId, page);
		
		
		req.setAttribute("movieList", movieList);
		req.setAttribute("mvPage", page);*/
		// 待定---判断改变路径或者其他逻辑
		page.setParameters(1, 1);
		page.calcurate();
		req.setAttribute("movieList", movieList);
		req.setAttribute("mvPage", page);
		return "success";
	}

	public String edit() {
		String movieId = req.getParameter("movieId");
		System.out.println("电影控制器中的movieId："+movieId);
		Movie movie = service.getMovieById(movieId);
		System.out.println("电影控制器中的movie对象："+movie);
		req.getSession(false).setAttribute("movie", movie);
		List<Category> categories = catService.getAllCategory();
		req.setAttribute("categories", categories);
		return "success";
	}

	public String editMovie() {
		// 
		String result ="fail";
		System.out.println("---------------------------------------------------------------");
		System.out.println("cinameController中的movie："+movie);
		System.out.println("---------------------------------------------------------------");
		String id = req.getParameter("movieId");
		if(service.updateMovie(movie,id))
			result = "success";
		return result;
	}
	public String delete() {
		String id = req.getParameter("movieId");
		String videoExtName = req.getParameter("videoExtName");;
		String imgExtName = req.getParameter("imgExtName");;
		String user = ((User)req.getSession(false).getAttribute("user")).getName();
		if(service.deleteById(id)){
			//如果删除成功，删除文件
			//videoExtName=${movie.extName}&imgExtName=${movie.imgExtName}
			String basePath = config.get("path");
			String imgPath = basePath+user+"\\"+"image\\"+
					id+"."+imgExtName;;
			String videoPath = basePath+user+"\\"+"video\\"+
					id+"."+videoExtName;
			File file = new File(videoPath);
			if(file.exists()){
				file.delete();
			}
			file = new File(imgPath);
			if(file.exists()){
				file.delete();
			}
			
		}
		return "success" ;
	}
	

	public String stopAndOpen() {
		String id = req.getParameter("movieId");
		String isEnabled = req.getParameter("isEnabled");
		service.updateIsEnabledd(id, isEnabled);
		// 待定---判断改变路径或者其他逻辑
		return "success";
	}
	
	public String play() {
		String id = req.getParameter("movieId");
		
		//获取movie
		Movie movie = service .getMovieById(id);
		System.out.println("PlayList中的movie对象："+movie);
		//将movie对象放置到session域中
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("user");
		String username = user.getName();
		String nickName = user.getNickName();
		movie.setUploaderName(username);
		movie.setUploaderNickName(nickName);
		session.setAttribute("movie", movie);
		
		return "success";
	}
}
