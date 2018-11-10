/**
 * 
 */
package com.javateam.member.util;

import com.javateam.member.domain.PagingVO;

/**
 * @author javateam
 *
 */
public class PagingUtil {
	
	public PagingVO getPageInfo(int memberNum, 
								PagingVO pagingVo) {
		
		// 페이징 관련 변수 설정
		pagingVo.setTotPage((int)((memberNum-1) 
			/ pagingVo.getRowPerPage()) + 1);
		
		pagingVo.setEndPage((pagingVo.getTotPage() == 1) ?
				1 : pagingVo.getStartPage() + 
				pagingVo.getTotPage() - 1); // 마지막 페이지
		
		pagingVo.setPrePage((pagingVo.getCurPage() <= 1) ? 
				1 : pagingVo.getCurPage() - 1); // 이전 페이지
		
		pagingVo.setNextPage((pagingVo.getTotPage() == 1) ? 1 :
					 (pagingVo.getCurPage() >= pagingVo.getEndPage()) ? 
					  pagingVo.getEndPage() : pagingVo.getCurPage() + 1); // 다음 페이지
		
		pagingVo.setCurPage((pagingVo.getCurPage() >= pagingVo.getEndPage()) ? 
				 pagingVo.getEndPage() : pagingVo.getCurPage()); // 현재 페이지
		
		return pagingVo;
	}

}