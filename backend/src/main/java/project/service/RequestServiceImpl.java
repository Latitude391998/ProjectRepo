package project.service;

import project.entity.Type;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dto.RequestDto;
import project.entity.Customer;
import project.entity.Manufacturer;
import project.entity.Product;
import project.entity.Request;
import project.entity.Status;
import project.exception.ResourceNotFoundException;
import project.repository.CustomerRepository;
import project.repository.ManufacturerRepository;
import project.repository.ProductRepository;
import project.repository.RequestRepository;
import project.response.ManufacturerSpecificResp;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestRepository requestRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	CustomerService customerService;

	@Autowired
	private ManufacturerRepository manufacturerRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	ManufacturerService manufacturerService;

	@Override
	public RequestDto addNewRequest(RequestDto requestDto) {
		
		
		Manufacturer manufacturer = manufacturerService.getManufacturerById(requestDto.getManufacturerId());
		Product product = productRepo.findById(requestDto.getProductId()).get();
		Customer customer = customerRepo.findById(requestDto.getCustomerId()).get();
		Status status = Status.valueOf(requestDto.getStatus());
		Type type = Type.valueOf(requestDto.getType());
		Request request = new Request(customer, product,manufacturer, requestDto.getStartDate(),null, type, status);
		
		
		
//		emailService.sendSimpleMessage(manufacturer.getEmail(), "New Request for Product",
//				"New Request is Added for Product ID: " + product.getId() + ". Please Respond.");
		
		requestRepo.save(request);
		return requestDto;
	}

	@Override
	public List<Request> requestsForManufacturer(ManufacturerSpecificResp manufacturer) {

		Long id = manufacturer.getId();

		Manufacturer m = manufacturerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Manufacturer ID"));

		return requestRepo.findByManufacturer(m);
	}

	@Override
	public int changeRequestStatus(Status status, Long requestId) {
		Request request = requestRepo.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Reuqest ID !!!!!"));
		Customer customer = customerService.getCustomerById(request.getCustomer().getId());
		emailService.sendSimpleMessage(customer.getEmail(), "Request In Processing",
				"Process started for Request Id: " + requestId + ". Please check.");
		return requestRepo.updateRequestStatus(status, requestId);
	}

}
