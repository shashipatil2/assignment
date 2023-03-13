package com.assignment.expensify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.expensify.dto.ResponseDetail;
import com.assignment.expensify.service.ExpensifyServiceImpl;

@RestController
public class ExpensifyController {
	
	@Autowired
	private ExpensifyServiceImpl expensifyServiceImpl;
	
	/**
	 * This is end point for this sample app. Where 
	 * will two integration server APIs. One to get filename and then pass 
	 * file to download the content.
	 * Req job desc will passing as query param and defined in txt file
	 * Template will passing as body in url uncoded form and defined in txt file
	 * Handling parent exception of type Exception globally using controller advice.
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path= "/expenses")
	public ResponseEntity<ResponseDetail> getExpense() throws Exception {
		//calling integration server will return report name
		String csvFileName = expensifyServiceImpl.callExpenseService();
		
		//checking report name is valid or not by its ext name
		if(csvFileName!=null && csvFileName.endsWith(".csv")) {
			//passing file name to get data as file download. and loading its content into h2 in memory DB.
			boolean dataFromCSVFileFromExpensify = expensifyServiceImpl.getDataFromCSVFileFromExpensify(csvFileName);
			if(dataFromCSVFileFromExpensify)
				return new ResponseEntity<ResponseDetail>(new ResponseDetail("Data pulled successfully and stored into DB.",
					HttpStatus.ACCEPTED.toString()),HttpStatus.OK);
		}
		return new ResponseEntity<ResponseDetail>(new ResponseDetail("Something went wrong,please try again later.",
				HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.OK);
	}
}
