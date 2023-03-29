package project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.dto.RequestDto;
import project.entity.Customer;
import project.response.ApiResponse;
import project.response.CustomerSpecificResp;
import project.response.EmailRequestDto;
import project.response.LoginRequestDto;
import project.service.CustomerService;
import project.service.RequestService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
@Validated
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RequestService requestService;

	@PostMapping(value = "/register",consumes="application/json")
	public Customer saveCustomer(@RequestBody @Valid Customer customer) {
		return customerService.addCustomer(customer);
	}

	@GetMapping(value = "/all",consumes="application/json")
	public List<Customer> fetchAllCustomers() {
		return customerService.getAllCustomers();
	}

	@PostMapping(value = "/authenticate",consumes="application/json")
	public ResponseEntity<?> validateEmployee(@RequestBody @Valid LoginRequestDto dto) {
		try {
			System.out.println("");
			return ResponseEntity.ok(customerService.authenticateCustomer(dto));
		} catch (RuntimeException e) {
			System.out.println("err in emp controller " + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PostMapping(value = "/getbymail",consumes="application/json")
	public ResponseEntity<?> getCustomerByMail(@RequestBody @Valid EmailRequestDto dto) {
		try {
			return ResponseEntity.ok(customerService.getCustomerByMail(dto));
		} catch (RuntimeException e) {
			System.out.println("err in emp controller " + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PutMapping(value = "/resetpassword",consumes="application/json")
	public ResponseEntity<?> resetPassword(@RequestBody @Valid LoginRequestDto dto) {
		try {
			return ResponseEntity.ok(customerService.updatePassword(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PutMapping(value = "/update",consumes="application/json")
	public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerSpecificResp customer) {
		try {
			return ResponseEntity.ok(customerService.updateCustomer(customer));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PostMapping(value = "/addRequest",consumes="application/json")
	public RequestDto addNewRequest(@RequestBody @Valid RequestDto requestDto) {
		return requestService.addNewRequest(requestDto);
	}
	
	@PostMapping("/check")
	public ResponseEntity<?> sendHash(@RequestBody @Valid EmailRequestDto emailDto){
		try {
			return ResponseEntity.ok(customerService.sendHash(emailDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

}
