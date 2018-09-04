package com.hong.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hong.bean.ListIndex;
import com.hong.bean.Movie;
import com.hong.bean.Page;
import com.hong.bean.User;
import com.hong.service.ListIndexService;
import com.hong.service.MovieService;
import com.hong.service.PlayListService;
import com.hong.utils.GetPropertiesUtil;
//cinema有电影的意思

public class ListIndexAction {

	private ListIndex listIndex;
	private Page<Movie> page;
	ListIndexService service = new ListIndexService();
	PlayListService playService = new PlayListService();
	MovieService movieService = new MovieService();
	static Map<String, String> config = GetPropertiesUtil.getConInfo("config.property");
	public String view() {
		HttpServletRequest req = ServletActionContext.getRequest();
		User user = (User) req.getSession(false).getAttribute("user");
		String creator = user.getId();
		List<ListIndex> allListIndex = service.getListByCreator(creator);
		int size = allListIndex.size();
		Page<Movie> page = new Page<Movie>();
		int num = 1;
		String numString = req.getParameter("page");
		if(numString!=null){
			num = Integer.parseInt(numString);
		}
		page.setPage(num);
		System.out.println("在listIndexController中的当前页为："+page.getPage());
		
		List<ListIndex> listIndexList = service.getListByPage(creator, page, num);
		req.setAttribute("mvPage", page);
		System.out.println("列表控制中的所有列表");
		System.out.println(allListIndex);
		
		
		System.out.println("经过分页得到的数据为：\n"+listIndexList);
		req.setAttribute("allListIndex", listIndexList);
		req.setAttribute("size", size);
		return "success";
	}
	/*
	 * num
	 */
	public String edit() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String id = req.getParameter("id");
		String numString = req.getParameter("num");
		int num = 0;
		if(numString!=null){
			num = Integer.parseInt(numString);
			System.out.println("num"+num);
		}
		ListIndex listIndex = service.getListIndexById(id);
		req.setAttribute("listIndex", listIndex);
		if(num!=0){
			String listId = listIndex.getId();
			List<Movie> movieList = playService.getMovieListByListId(listId );
			System.out.println("根据listId得到了所有的播放列表如下：");
			System.out.println(movieList);
			req.setAttribute("movieList", movieList);
		}
		
		return "success";
	}
	public String delete() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String id = req.getParameter("id");
		if(id!=null){
			boolean result = service.deleteById(id);
			if(result){
				System.out.println("删除"+id+"成功！！！");
			}
		}
		return "success";
	}
	public String add() {
		//记得在这里查询返回的到id，不然无法添加列表
		HttpServletRequest req = ServletActionContext.getRequest();
		String listId = service.add(listIndex);
		if(listId!=null)
			req.setAttribute("listId", listId);
		return "success";
	}
	public String createList(){
		return "success";
	}
	public String toCreateListPage(HttpServletRequest req, HttpServletResponse resp) {
		return "success";
	}
	public String toPlayPage() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String listId = req.getParameter("listId");
		List<Movie> movies = null;
		if(listId!=null){
			movies = playService.getMovieListByListId(listId);
		}
		req.setAttribute("movies", movies);
		return "success";
	}
	public String toAddMoviePage() {
		System.out.println("准备进入添加电影的页面");
		HttpServletRequest req = ServletActionContext.getRequest();
		String numString = req.getParameter("page");
		
		String listId = (String) req.getAttribute("listId");
		System.out.println("点击添加视频之后获得的listId为："+listId);
		ListIndex listIndex = service.getListIndexById(listId);
		System.out.println("listIndex:"+listIndex);
		User user = (User) req.getSession(false).getAttribute("user");
		String uploader = user.getId();
		
		int num = 1;
		if(numString!=null){
			num = Integer.parseInt(numString);
		}
		Page<Movie> page = new Page<Movie>();
		List<Movie> allMovieList = movieService.getMovieByUploader(uploader);
		page.setParameters(allMovieList.size(), num);
		page.calcurate();
		List<Movie> movieList = movieService.getMovieByUploader(uploader, page);
		
		req.setAttribute("listIndex", listIndex);
		req.setAttribute("movieList", movieList);
		req.setAttribute("mvPage", page);
		return "success";
	}
	public String addMovies() {
		System.out.println("准备添加电影");
		HttpServletRequest req = ServletActionContext.getRequest();
		String listId = req.getParameter("listId");
		System.out.println("点击添加视频之后获得的listId为："+listId);
		@SuppressWarnings("unused")
		ListIndex listIndex = service.getListIndexById(listId);
		User user = (User) req.getSession(false).getAttribute("user");
		String userId = user.getId();
		System.out.println("点击添加视频之后获得的userId为："+userId);
		// TODO
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		String[] movieIds = req.getParameterValues("movieId");
		List<String> movieIdList = Arrays.asList(movieIds);
		for(String temp: movieIdList){
			System.out.println("temp:"+temp);
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		if(playService.addPlayList(listId, userId, movieIdList)){
			System.out.println("添加电影到列表已经成功");
		}
		return "success";
	}
public String deleteMovieList() {
	HttpServletRequest req = ServletActionContext.getRequest();
	System.out.println("准备删除列表中的电影");
	String listId = req.getParameter("listId");
	System.out.println("点击添加视频之后获得的listId为："+listId);
	User user = (User) req.getSession(false).getAttribute("user");
	String userId = user.getId();
	System.out.println("点击添加视频之后获得的userId为："+userId);
	// TODO
	System.out.println("+++++++++++++++++++++++++++++++++++++++++");
	String[] movieIds = req.getParameterValues("movieId");
	List<String> movieIdList = Arrays.asList(movieIds);
	for(String temp: movieIdList){
		System.out.println("temp:"+temp);
	}
	System.out.println("+++++++++++++++++++++++++++++++++++++++++");
	if(playService.deleteList(listId, userId, movieIdList)){
		System.out.println("删除电影到列表已经成功");
	}
	return "success";
	}
public String search() {
	HttpServletRequest req = ServletActionContext.getRequest();
	User user = (User) req.getSession(false).getAttribute("user");
	String userId = user.getId();
	String keyWord = page.getKeyWord();
	System.out.println("keyWord："+keyWord);
	List<ListIndex> allListIndex = service.search(userId, keyWord);

	int size = 1;
	page.setParameters(1, 1);
	page.calcurate();
	req.setAttribute("allListIndex", allListIndex);
	req.setAttribute("mvPage", page);
	req.setAttribute("size", size);
	return "success";
}
public ListIndex getListIndex() {
	return listIndex;
}
public void setListIndex(ListIndex listIndex) {
	this.listIndex = listIndex;
}

}
