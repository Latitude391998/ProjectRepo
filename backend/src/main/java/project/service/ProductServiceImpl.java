package project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import project.autodelete.UnusedImages;
import project.autodelete.UnusedImagesRepository;
import project.dto.ProductInsertDto;
import project.dto.ProductResponseDto;
import project.dto.ProductUpdateDto;
import project.dto.ResponseDto;
import project.entity.Customer;
import project.entity.Manufacturer;
import project.entity.Product;
import project.repository.CustomerRepository;
import project.repository.ManufacturerRepository;
import project.repository.ProductRepository;
import project.repository.RequestRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private RequestRepository requestRepo; 
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ManufacturerRepository manufacturerRepo;
	
	@Autowired
	private UnusedImagesRepository unusedImagesRepo;
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private Environment env;
	
	@Override
	public List<ProductResponseDto> getAllProducts()
	{
		List<Product> products = productRepo.findAll();
		List<ProductResponseDto> responses = new ArrayList<ProductResponseDto>();
		
		for (Product p : products)
		{
			ProductResponseDto response = new ProductResponseDto();	
			
			response.setId(p.getId());
			response.setCustomerId(p.getCustomer().getId());
			response.setSerialNumber(p.getSerialNumber());
			response.setProductName(p.getProductName());
			response.setProductType(p.getProductType());
			response.setManufacturerId(p.getManufacturer().getId());
			response.setWarrentyOrGuaranteeExpiry(p.getWarrentyOrGuaranteeExpiry());
			response.setPurchaseDate(p.getPurchaseDate());
			response.setReceipt(p.getReceipt());
			
			responses.add(response);
		}
		
		return responses;
	}
	
	@Override
	public ResponseEntity<?> saveProductDetails(ProductInsertDto productInsertDto)
	{
		Product product = new Product();
		
		List<Product> productsBySerialNo = productRepo.selectProductsBySerialNo(productInsertDto.getSerialNumber());
		System.out.println(productsBySerialNo);
		if (productsBySerialNo.size() == 1)
		{
			String message = "serial number already exist";
			ResponseDto responseDto = new ResponseDto(message);
			return new ResponseEntity<>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Optional<Customer> customerOptional = customerRepo.findById(productInsertDto.getCustomerId());
		Optional<Manufacturer> manufacturerOptional = manufacturerRepo.findById(productInsertDto.getManufacturerId());
		
		if (customerOptional.isPresent() && manufacturerOptional.isPresent())
		{
			try
			{
				String filePath=uploadImage(productInsertDto.getReceipt());
				String portNumber=env.getProperty("server.port");
				String fullPath="http://localhost:"+portNumber+"/product/"+filePath;
				
				product.setCustomer(customerOptional.get());
				product.setManufacturer(manufacturerOptional.get());
				product.setSerialNumber(productInsertDto.getSerialNumber());
				product.setWarrentyOrGuaranteeExpiry(productInsertDto.getWarrentyOrGuaranteeExpiry());
				product.setPurchaseDate(productInsertDto.getPurchaseDate());
				product.setProductName(productInsertDto.getProductName());
				product.setProductType(productInsertDto.getProductType());
				product.setReceipt(fullPath);
				
				
				product = productRepo.save(product);
				String message = "product added with id: "+product.getId();
				ResponseDto responseDto = new ResponseDto(message);
				return new ResponseEntity<>(responseDto,HttpStatus.OK);
			}
			catch(IOException e)
			{
				String message = "receipt upload failed";
				ResponseDto responseDto = new ResponseDto(message);
				return new ResponseEntity<>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		else
		{
			String message = "Manufacturer Id is invalid";
			ResponseDto responseDto = new ResponseDto(message);
			return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public ResponseEntity<?> getProductById(Long productId)
	{
		if (productRepo.existsById(productId))
		{
			Product product=productRepo.findById(productId).get();
			ProductResponseDto response = new ProductResponseDto();
			
			response.setId(product.getId());
			response.setCustomerId(product.getCustomer().getId());
			response.setSerialNumber(product.getSerialNumber());
			response.setProductName(product.getProductName());
			response.setProductType(product.getProductType());
			response.setManufacturerId(product.getManufacturer().getId());
			response.setWarrentyOrGuaranteeExpiry(product.getWarrentyOrGuaranteeExpiry());
			response.setPurchaseDate(product.getPurchaseDate());
			response.setReceipt(product.getReceipt());
			
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else
		{
			String message = "Product with Id: "+productId+" not found";
			ResponseDto responseDto = new ResponseDto(message);
			return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public ResponseEntity<?> updateProduct(ProductUpdateDto productUpdateDto)
	{
		if (productRepo.existsById(productUpdateDto.getId()))
		{
			Product product = new Product();
			Optional<Customer> customerOptional = customerRepo.findById(productUpdateDto.getCustomerId());
			Optional<Manufacturer> manufacturerOptional = manufacturerRepo.findById(productUpdateDto.getManufacturerId());
			
			if (customerOptional.isPresent() && manufacturerOptional.isPresent())
			{
				try
				{
					deleteImage(productUpdateDto.getId());
					
					String filePath=uploadImage(productUpdateDto.getReceipt());
					String portNumber=env.getProperty("server.port");
					String fullPath="http://localhost:"+portNumber+"/product/"+filePath;
					product.setReceipt(fullPath);
					
					product.setCustomer(customerOptional.get());
					product.setManufacturer(manufacturerOptional.get());
					product.setId(productUpdateDto.getId());
					product.setSerialNumber(productUpdateDto.getSerialNumber());
					product.setWarrentyOrGuaranteeExpiry(productUpdateDto.getWarrentyOrGuaranteeExpiry());
					product.setPurchaseDate(productUpdateDto.getPurchaseDate());
					product.setProductName(productUpdateDto.getProductName());
					product.setProductType(productUpdateDto.getProductType());
					
					product = productRepo.save(product);
					String message = "product with id: "+product.getId()+" updated";
					ResponseDto responseDto = new ResponseDto(message);
					return new ResponseEntity<>(responseDto,HttpStatus.OK);
				}
				catch(IOException e)
				{
					String message = "receipt upload failed";
					ResponseDto responseDto = new ResponseDto(message);
					return new ResponseEntity<>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else
			{
				String message = "Manufacturer Id is invalid";
				ResponseDto responseDto = new ResponseDto(message);
				return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			String message = "product with given id doesn't exist";
			ResponseDto responseDto = new ResponseDto(message);
			return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> deleteProduct(Long productId)
	{
		if (productRepo.existsById(productId))
		{
			deleteImage(productId);
			
			requestRepo.deleteByProduct_Id(productId);
			productRepo.deleteByProduct_Id(productId);
			
			String message = "Product with Id: "+productId+" deleted";
			ResponseDto responseDto = new ResponseDto(message);
			return new ResponseEntity<>(responseDto,HttpStatus.OK);
		}
		else
		{
			String message = "Product with Id: "+productId+" not found";
			ResponseDto responseDto = new ResponseDto(message);
			return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);
		}
	}
	
	private String uploadImage(MultipartFile file) throws IOException
	{
		String actualName = file.getOriginalFilename();
		int indexOfDot= actualName.lastIndexOf(".");
		String fileExtension = actualName.substring(indexOfDot);
		
		double num= Math.random();
		int randomNum =(int)Math.floor(num*1000000000);
		
		String name = randomNum+fileExtension;
		
		String filePath = path +"/"+ name; 
		
		File folder = new File(path);
		
		if (!folder.exists())
		{
			folder.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return filePath;
	}
	
	private void deleteImage(Long productId)
	{
		Product existingProduct= productRepo.findById(productId).get();
		String receiptUrl = existingProduct.getReceipt();
		System.out.println(receiptUrl);
		int indexOfLastSlash = receiptUrl.lastIndexOf("/");
		String fileName = receiptUrl.substring(indexOfLastSlash);
		
		String path = "images"+fileName;
		System.out.println(path);
		File fileToDelete = new File(path);
		
		boolean isDeleted=fileToDelete.delete();
		if (isDeleted == false)
		{
			UnusedImages unusedImages = new UnusedImages();
			unusedImages.setPath(path);
			unusedImagesRepo.save(unusedImages);
		}
	}
	
	@Override
	public InputStream getResource(String fileName) throws FileNotFoundException
	{
		String fullPath = path + "/" + fileName;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}
}
