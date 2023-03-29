package project.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.springframework.http.ResponseEntity;

import project.dto.ProductInsertDto;
import project.dto.ProductResponseDto;
import project.dto.ProductUpdateDto;

public interface ProductService 
{
	public List<ProductResponseDto> getAllProducts();
	
	public ResponseEntity<?> saveProductDetails(ProductInsertDto product);
	
	public ResponseEntity<?> deleteProduct(Long productId);
	
	public ResponseEntity<?> getProductById(Long productId);
	
	public ResponseEntity<?> updateProduct(ProductUpdateDto productUpdateDto);
	
	public InputStream getResource(String fileName) throws FileNotFoundException;
	
}
