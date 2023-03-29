package project.dto;

import java.time.LocalDate;

import project.entity.Product;
import project.entity.Status;

public class RequestDto {
	
	private long customerId;
	
	private long manufacturerId;
	
	private Long productId;
	
	private LocalDate startDate;
	
	private String type;
	
	private String status;



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RequestDto(long customerId, long manufacturerId, Long productId, LocalDate startDate, String type,
			String status) {
		super();
		this.customerId = customerId;
		this.manufacturerId = manufacturerId;
		this.productId = productId;
		this.startDate = startDate;
		this.type = type;
		this.status = status;
	}

	public RequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String status) {
		this.type = status;
	}

	@Override
	public String toString() {
		return "RequestDto [customerId=" + customerId + ", manufacturerId=" + manufacturerId + ", productId="
				+ productId + ", startDate=" + startDate + ", type=" + type + ", status=" + status + "]";
	}

	
	
	
}
