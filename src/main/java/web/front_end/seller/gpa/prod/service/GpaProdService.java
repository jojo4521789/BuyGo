package web.front_end.seller.gpa.prod.service;

import java.util.List;

import web.front_end.seller.gpa.prod.entity.GpaProd;

public interface GpaProdService {
	GpaProd loadByGpaProdNo(Integer gpaProdNo);
	
	List<GpaProd> randomLoadByGpaCatsNoAndFilterGpaProdNo(Integer gpaCatsNo, Integer gpaProdNo); // 輸入欲搜尋的gpaCatsNo和欲忽視單筆的gpaProdNo
}
