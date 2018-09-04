package com.hong.test;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;

import com.hong.bean.Movie;
import com.hong.bean.PlayList;
import com.hong.dao.PlayListDao;
import com.hong.utils.UUIDUtil;

public class PlayListDaoTest {

	PlayListDao dao = new PlayListDao();
	

	@Test
	public void testGetById() {
		List<Movie> movieList = dao.getMovieList("1893m62j204j5wccsbfs4k1127y28248");
		System.out.println("movieList:"+movieList);
	}
	@Test
	public void testAdd() {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		PlayList playlist = new PlayList(
				UUIDUtil.getUUID(),
				"1893m62j204j5wccsbfs4k1127y28248",
				1,
				"b51d65977ebb4e5ea438450f376a4b0b",
				"wr59mgu5838xuifbttnp5iu041n148mr",
				stamp
				);
		if(dao.add(playlist))
		System.out.println("movieList:"+playlist);
		else{
			System.out.println("≤Â»Î ß∞‹");
		}
	}
	@Test
	public void testDelete(){
		if(dao.deleteByOtherWay("1893m62j204j5wccsbfs4k1127y28248", "040dbe82ee3a4bf689a444d11c81c655", "wr59mgu5838xuifbttnp5iu041n148mr"))
			System.out.println("…æ≥˝≥…π¶");;
	}
}
