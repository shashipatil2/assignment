package com.assignment.expensify.dto;

import com.opencsv.bean.CsvBindByPosition;

public class VendorData {
	
	@CsvBindByPosition(position = 0)
	String reportId;
	@CsvBindByPosition(position = 1)
	String merchant;	
	@CsvBindByPosition(position = 2)
	Integer amount;	
	@CsvBindByPosition(position = 3)
	String category;
	@CsvBindByPosition(position = 4)
	String expenseDate;
	
	public VendorData() {
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}

	@Override
	public String toString() {
		return "VendorData [reportId=" + reportId + ", merchant=" + merchant + ", Amount=" + amount + ", category="
				+ category + ", expenseDate=" + expenseDate + "]";
	}

	
	

}
