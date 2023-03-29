package project.dto;

import java.time.LocalDate;

public class ProductResponseDto {
	private Long id;

	private Long customerId;

	private String serialNumber;

	private String productName;

	private String productType;

	private Long manufacturerId;

	private LocalDate warrentyOrGuaranteeExpiry;

	private LocalDate purchaseDate;

	private String receipt;

	public ProductResponseDto() {
		super();
	}

	public ProductResponseDto(Long id, Long customerId, String serialNumber, String productName, String productType,
			Long manufacturerId, LocalDate warrentyOrGuaranteeExpiry, LocalDate purchaseDate, String receipt) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.serialNumber = serialNumber;
		this.productName = productName;
		this.productType = productType;
		this.manufacturerId = manufacturerId;
		this.warrentyOrGuaranteeExpiry = warrentyOrGuaranteeExpiry;
		this.purchaseDate = purchaseDate;
		this.receipt = receipt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public LocalDate getWarrentyOrGuaranteeExpiry() {
		return warrentyOrGuaranteeExpiry;
	}

	public void setWarrentyOrGuaranteeExpiry(LocalDate warrentyOrGuaranteeExpiry) {
		this.warrentyOrGuaranteeExpiry = warrentyOrGuaranteeExpiry;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "ProductResponseDto [id=" + id + ", customerId=" + customerId + ", serialNumber=" + serialNumber
				+ ", productName=" + productName + ", productType=" + productType + ", manufacturerId=" + manufacturerId
				+ ", warrentyOrGuaranteeExpiry=" + warrentyOrGuaranteeExpiry + ", purchaseDate=" + purchaseDate
				+ ", receipt=" + receipt + "]";
	}

}
