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
//cinema�е�Ӱ����˼

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
		System.out.println("��listIndexController�еĵ�ǰҳΪ��"+page.getPage());
		
		List<ListIndex> listIndexList = service.getListByPage(creator, page, num);
		req.setAttribute("mvPage", page);
		System.out.println("�б�����е������б�");
		System.out.println(allListIndex);
		
		
		System.out.println("������ҳ�õ�������Ϊ��\n"+listIndexList);
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
			System.out.println("����listId�õ������еĲ����б����£�");
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
				System.out.println("ɾ��"+id+"�ɹ�������");
			}
		}
		return "success";
	}
	public String add() {
		//�ǵ��������ѯ���صĵ�id����Ȼ�޷�����б�
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
		System.out.println("׼��������ӵ�Ӱ��ҳ��");
		HttpServletRequest req = ServletActionContext.getRequest();
		String numString = req.getParameter("page");
		
		String listId = (String) req.getAttribute("listId");
		System.out.println("��������Ƶ֮���õ�listIdΪ��"+listId);
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
		System.out.println("׼����ӵ�Ӱ");
		HttpServletRequest req = ServletActionContext.getRequest();
		String listId = req.getParameter("listId");
		System.out.println("��������Ƶ֮���õ�listIdΪ��"+listId);
		@SuppressWarnings("unused")
		ListIndex listIndex = service.getListIndexById(listId);
		User user = (User) req.getSession(false).getAttribute("user");
		String userId = user.getId();
		System.out.println("��������Ƶ֮���õ�userIdΪ��"+userId);
		// TODO
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		String[] movieIds = req.getParameterValues("movieId");
		List<String> movieIdList = Arrays.asList(movieIds);
		for(String temp: movieIdList){
			System.out.println("temp:"+temp);
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		if(playService.addPlayList(listId, userId, movieIdList)){
			System.out.println("��ӵ�Ӱ���б��Ѿ��ɹ�");
		}
		return "success";
	}
public String deleteMovieList() {
	HttpServletRequest req = ServletActionContext.getRequest();
	System.out.println("׼��ɾ���б��еĵ�Ӱ");
	String listId = req.getParameter("listId");
	System.out.println("��������Ƶ֮���õ�listIdΪ��"+listId);
	User user = (User) req.getSession(false).getAttribute("user");
	String userId = user.getId();
	System.out.println("��������Ƶ֮���õ�userIdΪ��"+userId);
	// TODO
	System.out.println("+++++++++++++++++++++++++++++++++++++++++");
	String[] movieIds = req.getParameterValues("movieId");
	List<String> movieIdList = Arrays.asList(movieIds);
	for(String temp: movieIdList){
		System.out.println("temp:"+temp);
	}
	System.out.println("+++++++++++++++++++++++++++++++++++++++++");
	if(playService.deleteList(listId, userId, movieIdList)){
		System.out.println("ɾ����Ӱ���б��Ѿ��ɹ�");
	}
	return "success";
	}
public String search() {
	HttpServletRequest req = ServletActionContext.getRequest();
	User user = (User) req.getSession(false).getAttribute("user");
	String userId = user.getId();
	String keyWord = page.getKeyWord();
	System.out.println("keyWord��"+keyWord);
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
