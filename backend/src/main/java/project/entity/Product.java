package project.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "serial_number", unique = true, nullable = false)
	private String serialNumber;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_type")
	private String productType;

	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	private LocalDate warrentyOrGuaranteeExpiry;

	private LocalDate purchaseDate;

	private String receipt;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Request request;

	public Product() {
		super();
	}

	public Product(Customer customer, String serialNumber, String productName, String productType,
			Manufacturer manufacturer, LocalDate dateOfManufacture, LocalDate purchaseDate, String receipt,
			Request request) {
		super();
		this.customer = customer;
		this.serialNumber = serialNumber;
		this.productName = productName;
		this.productType = productType;
		this.manufacturer = manufacturer;
		this.warrentyOrGuaranteeExpiry = dateOfManufacture;
		this.purchaseDate = purchaseDate;
		this.receipt = receipt;
		this.request = request;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public LocalDate getWarrentyOrGuaranteeExpiry() {
		return warrentyOrGuaranteeExpiry;
	}

	public void setWarrentyOrGuaranteeExpiry(LocalDate warrentyOrGuaranteeExpiry) {
		this.warrentyOrGuaranteeExpiry = warrentyOrGuaranteeExpiry;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
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

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

}
