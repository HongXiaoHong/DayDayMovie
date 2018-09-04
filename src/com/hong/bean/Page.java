package com.hong.bean;

import java.util.List;

public class Page<T> {

	//�����ֶ�
	public final int recOfPage = 3;//ÿҳ�ļ�¼��Ϊ3
	public final int btnOfPage = 5;//ÿҳ�İ�ť��Ϊ5
	//��ͨ�ֶ�
	private int totalRecs = 0;//�ܼ�¼��
	private int page = 1;//��ǰҳ
	private String keyWord;//�ؼ���
	private int curPageSeg = 1;//��ǰ��
	private int start = 0;//��ʼҳ
	private int end = 0;//����ҳ
	private int totalPage = 0;//��ҳ��
	private List<T> list;
	//�趨�ܼ�¼�������е�ǰҳ
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
		//������ҳ��
		if(totalRecs%recOfPage == 0)
			{totalPage = totalRecs/recOfPage;}
		else
			{totalPage = totalRecs/recOfPage+1;}
		//���㵱ǰҳ
		page = (page>=totalPage) ? totalPage : page;
		page = page==0 ? 1: page;
		//���㵱ǰ��
		if(page%btnOfPage==0){
			curPageSeg = page/btnOfPage;
		} else{
			curPageSeg = page/btnOfPage+1;
		}
		//���㿪ʼҳ
		start = (curPageSeg-1)*5+1;
		//�������ҳ
		end = start + btnOfPage -1;
		end = (end>totalPage)?totalPage:end; 
		System.out.println("===========================================");
		System.out.println("�ܼ�¼��Ϊ��"+totalRecs);
		System.out.println("��ҳ��Ϊ��"+totalPage);
		System.out.println("��ǰҳΪ��"+page);
		System.out.println("��ʼҳΪ��"+start);
		System.out.println("����ҳΪ��"+end);
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

	//���㵱ǰҳ���
	public int getCurPageSeg() {return curPageSeg;}
	public void setCurPageSeg(int curPageSeg) {this.curPageSeg = curPageSeg;}
	//�ؼ�������ҳ�������
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
