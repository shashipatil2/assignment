package com.assignment.expensify.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignment.expensify.dto.VendorData;
import com.assignment.expensify.repository.VendorDataRepo;
import com.assignment.expensify.repository.VendorEntity;

@Repository
public class VendorDaoImpl {

	@Autowired
	private VendorDataRepo vendorDataRepo;
	
	public boolean saveData(List<VendorData> vendorDataFromCSV) {
		if(vendorDataFromCSV!=null && vendorDataFromCSV.size()>1) {
			vendorDataFromCSV.forEach(vendor-> {
				VendorEntity entity = new VendorEntity(vendor.getReportId(),vendor.getMerchant(),vendor.getAmount(),vendor.getCategory(),
						vendor.getExpenseDate());
				vendorDataRepo.save(entity);
			});
			return true;
		}
		return false;
	}
}
