package com.assignment.expensify.repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VendorEntity {

	@Id
	String reportId;
	String merchant;	
	Integer amount;	
	String category;
	String expenseDate;
	
	public VendorEntity(String reportId,String merchant,Integer amount,String category,String expenseDate) {
		this.reportId=reportId;
		this.merchant=merchant;
		this.amount=amount;
		this.category=category;
		this.expenseDate=expenseDate;
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
		return "VendorEntity [reportId=" + reportId + ", merchant=" + merchant + ", Amount=" + amount + ", category="
				+ category + ", expenseDate=" + expenseDate + "]";
	}
	
	
}
