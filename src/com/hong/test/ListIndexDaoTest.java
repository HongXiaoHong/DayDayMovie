package com.hong.test;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;

import com.hong.bean.ListIndex;
import com.hong.bean.Movie;
import com.hong.bean.PlayList;
import com.hong.dao.ListIndexDao;
import com.hong.utils.UUIDUtil;

public class ListIndexDaoTest {

	ListIndexDao dao = new ListIndexDao();
	@Test
	public void testAdd() {
		/*
		 * private String id;
	private String listName;
	private String creator;
		 */
		String id1 = UUIDUtil.getUUID();
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		ListIndex listIndex = new ListIndex(
				id1, "热门综艺", "wr59mgu5838xuifbttnp5iu041n148mr",
				stamp);
		String id2 = UUIDUtil.getUUID();
		List<PlayList> list = listIndex.getList();
		list.add(new PlayList(
				id2,
				id1,
				1,
				"4a57aec7a1ef43faa9b1a5b96bd677dd",
				"wr59mgu5838xuifbttnp5iu041n148mr",
				stamp
				));
		
		dao.add(listIndex);
	}

	@Test
	public void testGetListIndexById() {
		ListIndex listIndexById = dao.getListIndexById("720fe33ccfbe4a0eb4f0fb3f58dba71f");
		System.out.println("listIndex:"+listIndexById);
		
	}
	@Test
	public void testGetListIndex() {
		System.out.println();
		List<ListIndex> listIndex = dao.getListIndex();
		int i = 0;
		for(ListIndex list:listIndex){
			System.out.println("list"+(i++)+":"+list);
		}
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
}
