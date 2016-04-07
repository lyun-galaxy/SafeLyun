package com.paly.domain;

import java.util.List;

/**
 * 分页信息
 * @author root
 *
 * @param <T>
 */
public class Page<T> {

	private int totalpage;  
	//每页包含10条数据
	private int pagesize = 10;  
	
	private long totalrecord; 
	private int pagenum; 
	//起始索引
	private int startindex; 
	
	//数据集
	private List<T> list; 
	
	private int startPage;
	private int endPage;
	
	
	public Page(int pagenum,long totalrecord2){
		this.pagenum = pagenum;
		this.totalrecord = totalrecord2;
		
		
		this.totalpage = ((int)this.totalrecord+this.pagesize-1)/this.pagesize;
		this.startindex = (this.pagenum-1)*this.pagesize;
		
		
		if(this.totalpage<=10){
			this.startPage = 1;
			this.endPage = this.totalpage;
		}else{
			this.startPage = pagenum-4;
			this.endPage = pagenum+5;
			
			if(this.startPage<1){
				this.startPage = 1;
				this.endPage = 10;
			}
			if(this.endPage>this.totalpage){
				this.endPage = this.totalpage;
				this.startPage = this.totalpage-9;
			}
		}
	}


	public int getTotalpage() {
		return totalpage;
	}


	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}


	public int getPagesize() {
		return pagesize;
	}


	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public long getTotalrecord() {
		return totalrecord;
	}


	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}


	public int getPagenum() {
		return pagenum;
	}


	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}


	public int getStartindex() {
		return startindex;
	}


	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}


	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
}
