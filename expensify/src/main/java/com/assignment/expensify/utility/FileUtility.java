package com.assignment.expensify.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FileUtility {

	public static String getReqJobDesc() {
		return getDataFromFile("/request/requestJobDescription.txt");
	}

	public static String getExcelTemplate() {
		return getDataFromFile("/templates/excelTemplate.txt");
	}
	
	public static String getDownJobDesc() {
		return getDataFromFile("/request/downloadFile.txt");
	}

	@SuppressWarnings("resource")
	public static String getDataFromFile(String fileName) {
		Resource pathResource = new ClassPathResource(fileName);
		String data = null;
		try {
			File file = pathResource.getFile();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			data = bufferedReader.lines().collect(Collectors.joining());
			System.out.println(data);
		} catch (IOException e) {
			return null;
		}
		return data;
	}

	/*public static void main(String[] a) {
		getDataFromFile("/request/requestJobDescription.txt");
		getDataFromFile("/templates/excelTemplate.txt");
	}*/
}
