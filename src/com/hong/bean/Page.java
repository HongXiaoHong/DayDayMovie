package com.hong.bean;

import java.util.List;

public class Page<T> {

	//常量字段
	public final int recOfPage = 3;//每页的记录数为3
	public final int btnOfPage = 5;//每页的按钮数为5
	//普通字段
	private int totalRecs = 0;//总记录数
	private int page = 1;//当前页
	private String keyWord;//关键字
	private int curPageSeg = 1;//当前段
	private int start = 0;//开始页
	private int end = 0;//结束页
	private int totalPage = 0;//总页数
	private List<T> list;
	//设定总记录数，还有当前页
	public void setParameters(int _totalRecs, int _page){
		this.totalRecs = _totalRecs;
		this.page = _page;
	}
	
	public void setList(List<T> list){this.list = list;}
	public List<T> getList() {return list;}

	public int getNextPage(){
		return (page>=totalPage)?totalPage:page+1;}
	public int getPrevPage(){
		
		return (page<=1)?1:page-1;}
	
	public void calcurate(){
		//计算总页数
		if(totalRecs%recOfPage == 0)
			{totalPage = totalRecs/recOfPage;}
		else
			{totalPage = totalRecs/recOfPage+1;}
		//计算当前页
		page = (page>=totalPage) ? totalPage : page;
		page = page==0 ? 1: page;
		//计算当前段
		if(page%btnOfPage==0){
			curPageSeg = page/btnOfPage;
		} else{
			curPageSeg = page/btnOfPage+1;
		}
		//计算开始页
		start = (curPageSeg-1)*5+1;
		//计算结束页
		end = start + btnOfPage -1;
		end = (end>totalPage)?totalPage:end; 
		System.out.println("===========================================");
		System.out.println("总记录数为："+totalRecs);
		System.out.println("总页数为："+totalPage);
		System.out.println("当前页为："+page);
		System.out.println("开始页为："+start);
		System.out.println("结束页为："+end);
		System.out.println("===========================================");
	}
	
	public Page() {super();}
	public Page(int totalRecs, int page, int start, int end, int totalPage) {
		super();
		this.totalRecs = totalRecs;
		this.page = page;
		this.start = start;
		this.end = end;
		this.totalPage = totalPage;
	}

	public int getTotalRecs() {return totalRecs;}
	public void setTotalRecs(int totalRecs) {this.totalRecs = totalRecs;}

	public int getPage() {return page;}
	public void setPage(int page) {this.page = page;}

	public int getStart() {return start;}
	public void setStart(int start) {this.start = start;}

	public int getEnd() {return end;}
	public void setEnd(int end) {this.end = end;}

	public int getTotalPage() {return totalPage;}
	public void setTotalPage(int tatalPage) {this.totalPage = tatalPage;}

	public int getRecOfPage() {return recOfPage;}
	public int getBtnOfPage() {return btnOfPage;}

	//计算当前页面段
	public int getCurPageSeg() {return curPageSeg;}
	public void setCurPageSeg(int curPageSeg) {this.curPageSeg = curPageSeg;}
	//关键字用于页面的搜索
	public String getKeyWord() {return keyWord;}
	public void setKeyWord(String keyWord) {this.keyWord = keyWord;}

	@Override
	public String toString() {
		return "Page [recOfPage=" + recOfPage + ", btnOfPage=" + btnOfPage
				+ ", totalRecs=" + totalRecs + ", page=" + page + ", keyWord="
				+ keyWord + ", curPageSeg=" + curPageSeg + ", start=" + start
				+ ", end=" + end + ", totalPage=" + totalPage + ", list="
				+ list + "]";
	}
	
}
