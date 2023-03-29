package project.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.dto.ProductInsertDto;
import project.dto.ProductResponseDto;
import project.dto.ProductUpdateDto;
import project.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController 
{
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<ProductResponseDto> getAllProducts()
	{
		List<ProductResponseDto> products = productService.getAllProducts();
		System.out.println(products);
		return products;
	}
	
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestParam("customerId") Long customerId, @RequestParam("serialNumber") String serialNumber, 
			@RequestParam("manufacturerId") Long manufacturerId,@RequestParam("warrentyOrGuaranteeExpiry") String warrentyOrGuaranteeExpiry, 
			@RequestParam("purchaseDate") String purchaseDate, @RequestParam("receipt") MultipartFile receipt, 
			@RequestParam("productName") String productName, @RequestParam("productType") String productType)
	{
		System.out.println("in add product");
		ProductInsertDto productInsertDto = new ProductInsertDto();
		productInsertDto.setCustomerId(customerId);
		productInsertDto.setWarrentyOrGuaranteeExpiry(LocalDate.parse(warrentyOrGuaranteeExpiry));
		productInsertDto.setManufacturerId(manufacturerId);
		productInsertDto.setPurchaseDate(LocalDate.parse(purchaseDate));
		productInsertDto.setReceipt(receipt);
		productInsertDto.setSerialNumber(serialNumber);
		productInsertDto.setProductName(productName);
		productInsertDto.setProductType(productType);
		return productService.saveProductDetails(productInsertDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id)
	{
		return productService.deleteProduct(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id)
	{
		return productService.getProductById(id);
	}
	
	@PutMapping
	public ResponseEntity<?> updateProduct(@RequestParam("id") Long id ,@RequestParam("customerId") Long customerId, @RequestParam("serialNumber") String serialNumber, 
			@RequestParam("manufacturerId") Long manufacturerId,@RequestParam("warrentyOrGuaranteeExpiry") String warrentyOrGuaranteeExpiry, 
			@RequestParam("purchaseDate") String purchaseDate, @RequestParam("receipt") MultipartFile receipt,
			@RequestParam("productType") String productType, @RequestParam("productName") String productName)
	{
		ProductUpdateDto productUpdateDto = new ProductUpdateDto();
		productUpdateDto.setId(id);
		productUpdateDto.setCustomerId(customerId);
		productUpdateDto.setWarrentyOrGuaranteeExpiry(LocalDate.parse(warrentyOrGuaranteeExpiry));
		productUpdateDto.setManufacturerId(manufacturerId);
		productUpdateDto.setPurchaseDate(LocalDate.parse(purchaseDate));
		productUpdateDto.setReceipt(receipt);
		productUpdateDto.setSerialNumber(serialNumber);
		productUpdateDto.setProductName(productName);
		productUpdateDto.setProductType(productType);
		return productService.updateProduct(productUpdateDto);
	}
	
	@GetMapping(value="images/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException
	{
		InputStream resource = productService.getResource(imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	//@PostMapping("/newRequest")
	//TODO: REQUEST OBJECT AS INPUT
}
