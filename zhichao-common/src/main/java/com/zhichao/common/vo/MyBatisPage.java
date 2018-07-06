package com.zhichao.common.vo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 自定义mybatis分页对象
 * @author Administrator
 */
public class MyBatisPage<T> implements Page<T>, Serializable {
	/**
	 * serialVersionUID:
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;
	private List<T> list;

	private int pageNum;
	private int pageSize;
	private int totalPages;
	private int rowCount;
	/**
	 * 初始化Page
	 * @param pageNum		页号
	 * @param pageSize		页长
	 * @param list			数据列表
	 * @param rowCount		数据总条数
	 */
	public MyBatisPage(int pageNum, int pageSize,List<T> list,int rowCount) {
		this.list = list;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.totalPages = pageSize > 0 ? (int)Math.ceil(((double) rowCount)/pageSize) : 0;
		
		
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public List<T> getContent() {
		// TODO Auto-generated method stub
		return list;
	}
	
	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return pageNum;
	}
	
	@Override
	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return pageSize;
	}


	@Override
	public boolean hasContent() {
		if(list != null){
			return true;
		}
		return false;
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isFirst() {
	    if(rowCount==0){
            return true;
        }
		if(this.pageNum==0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isLast() {
	    if(rowCount==0){
            return true;
        }
		if(this.pageNum==this.totalPages-1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pageable nextPageable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previousPageable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalPages() {
		// TODO Auto-generated method stub
		return totalPages;
	}

	@Override
	public long getTotalElements() {
		// TODO Auto-generated method stub
		return rowCount;
	}

    @Override
    public <S> Page<S> map(Converter<? super T, ? extends S> arg0) {
        
        // TODO Auto-generated method stub
        return null;
    }

}
