package project.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class ProductInsertDto {
	private Long customerId;

	private String serialNumber;

	private Long manufacturerId;

	private String productName;

	private String productType;

	private LocalDate warrentyOrGuaranteeExpiry;

	private LocalDate purchaseDate;

	private MultipartFile receipt;

	public ProductInsertDto() {
		super();
	}

	public ProductInsertDto(Long customerId, String serialNumber, Long manufacturerId, String productName,
			String productType, LocalDate warrentyOrGuaranteeExpiry, LocalDate purchaseDate, MultipartFile receipt) {
		super();
		this.customerId = customerId;
		this.serialNumber = serialNumber;
		this.manufacturerId = manufacturerId;
		this.productName = productName;
		this.productType = productType;
		this.warrentyOrGuaranteeExpiry = warrentyOrGuaranteeExpiry;
		this.purchaseDate = purchaseDate;
		this.receipt = receipt;
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

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
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

	public MultipartFile getReceipt() {
		return receipt;
	}

	public void setReceipt(MultipartFile receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "ProductInsertDto [customerId=" + customerId + ", serialNumber=" + serialNumber + ", manufacturerId="
				+ manufacturerId + ", productName=" + productName + ", productType=" + productType
				+ ", warrentyOrGuaranteeExpiry=" + warrentyOrGuaranteeExpiry + ", purchaseDate=" + purchaseDate
				+ ", receipt=" + receipt + "]";
	}
}
