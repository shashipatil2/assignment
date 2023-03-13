package com.assignment.expensify.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.assignment.expensify.daoImpl.VendorDaoImpl;
import com.assignment.expensify.dto.VendorData;
import com.assignment.expensify.utility.FileUtility;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


@Service
public class ExpensifyServiceImpl {

	@Value("${expensify.endpoint}")
	public String endPoint;

	@Autowired
	private FileReadImpl fileReadImpl;
	
	@Autowired
	private VendorDaoImpl vendorDaoImpl;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Calling integration server with required req job desc request param and template as body in
	 * the form of www-encoded-url-format 
	 *  
	 * @return
	 * @throws Exception
	 */
	public String callExpenseService() throws Exception {
		/**
		 * Getting req job dec param and csv file template from file
		 * defined as txt file
		 */
		String reqJobDes = fileReadImpl.getReqJobDes();
		String excelTemplate = fileReadImpl.getExcelTemplate();

		HttpEntity<MultiValueMap<String, String>> httpEntity = getReqEntity(excelTemplate);

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endPoint).queryParam("requestJobDescription",
				reqJobDes);
		System.out.println(builder.build().toUri());
		
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(builder.build().toUri(), httpEntity,
				String.class);
		if (postForEntity != null && postForEntity.getBody() != null) {
			System.out.println(postForEntity.getBody());
			return postForEntity.getBody();
		}
		return null;
	}

	private HttpEntity<MultiValueMap<String, String>> getReqEntity(String excelTemplate) {
		MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
		valueMap.add("template", excelTemplate);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(valueMap, httpHeaders);
		return httpEntity;
	}
	
	/**
	 * Based file calling another API from integration server
	 * and storing records into in memory H2 DB.
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public boolean getDataFromCSVFileFromExpensify(String fileName)throws Exception {
		String queryParam = FileUtility.getDownJobDesc()+fileName+"}";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endPoint).queryParam("requestJobDescription",queryParam);
		System.out.println(builder.build().toUri());
		
		HttpEntity<MultiValueMap<String, String>> httpEntity = getReqEntity("");

		ResponseEntity<InputStream> postForEntity = restTemplate.postForEntity(builder.build().toUri(), httpEntity,
				InputStream.class);
		if (postForEntity != null && postForEntity.getBody() != null) {
			InputStreamReader reader = new InputStreamReader(postForEntity.getBody());
			CsvToBean<VendorData> csvToBean = new CsvToBeanBuilder<VendorData>(reader)
					.withType(VendorData.class).withSeparator(',').withIgnoreLeadingWhiteSpace(true).build();
			List<VendorData> vendorData = csvToBean.stream().collect(Collectors.toList());
			//Storing into DB.
			return vendorDaoImpl.saveData(vendorData);
		}
		return false;
		
	}
}
