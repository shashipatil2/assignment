package com.assignment.expensify.service;

import org.springframework.stereotype.Service;

import com.assignment.expensify.utility.FileUtility;

@Service
public class FileReadImpl {
	
	public String getReqJobDes() {
		return FileUtility.getReqJobDesc();
	}

	public String getExcelTemplate() {
		return FileUtility.getExcelTemplate();
	}
}
