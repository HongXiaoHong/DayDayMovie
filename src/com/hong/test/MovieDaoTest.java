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
				"����ϱ��������", "�㶫����", "20:00",
				"������", "0", "��ͥϲ��", "ϲ��",
				0, "��Եǧ��", "mp4"
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
				"����ϱ��������7", "�㶫����", "25:00",
				"������", "0", "��ͥϲ��", "ϲ��",
				0, "flv", "mp4"
				);
		movie.setId("f5bf7836c0c7417f9951c7feb1332549");
		System.out.println("movie:"+movie);
		/*if( dao.update(movie) )
			System.out.println("���³ɹ���");
		else {
			System.out.println("����ʧ��");
		}*/
	}
	@Test
	public void testDelete() {
		
		if( dao.delete("123456") )
			System.out.println("���³ɹ���");
		else {
			System.out.println("����ʧ��");
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
