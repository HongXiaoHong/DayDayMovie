package com.hong.test;

import java.util.List;

import org.junit.Test;

import com.hong.bean.Movie;
import com.hong.bean.Page;
import com.hong.dao.MovieDao;

public class MovieDaoTest {

	MovieDao dao = new MovieDao();
	@Test
	public void testAdd() {
		Movie movie = new Movie(
				"外来媳妇本地郎", "广东卫视", "20:00",
				"洪晓鸿", "0", "家庭喜剧", "喜剧",
				0, "有缘千里", "mp4"
				);
		dao.add(movie);
	}

	@Test
	public void testGetById() {
		Movie movie = dao.getMovieById("040dbe82ee3a4bf689a444d11c81c655");
		System.out.println("movie:"+movie);
	}
	@Test
	public void testUpdate() {
		Movie movie = new Movie(
				"外来媳妇本地郎7", "广东卫视", "25:00",
				"洪晓鸿", "0", "家庭喜剧", "喜剧",
				0, "flv", "mp4"
				);
		movie.setId("f5bf7836c0c7417f9951c7feb1332549");
		System.out.println("movie:"+movie);
		/*if( dao.update(movie) )
			System.out.println("更新成功！");
		else {
			System.out.println("更新失败");
		}*/
	}
	@Test
	public void testDelete() {
		
		if( dao.delete("123456") )
			System.out.println("更新成功！");
		else {
			System.out.println("更新失败");
		}
	}
	@Test
	public void testGetByPage() {
		
		Page<Movie> page = new Page<Movie>();
		page.setParameters(5, 1);
		page.calcurate();
		List<Movie> movieByUploader = dao.getMovieByUploader("wr59mgu5838xuifbttnp5iu041n148mr", page);
		System.out.println(movieByUploader);
	}
	
}
