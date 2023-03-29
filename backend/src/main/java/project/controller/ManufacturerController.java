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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.entity.Manufacturer;
import project.response.ApiResponse;
import project.response.EmailRequestDto;
import project.response.LoginRequestDto;
import project.response.ManufacturerSpecificResp;
import project.response.RequestDto;
import project.service.ManufacturerService;
import project.service.RequestService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/manufacturer")
@Validated
public class ManufacturerController {

	@Autowired
	private ManufacturerService manufacturerService;

	@Autowired
	private RequestService requestService;

	@PostMapping(value = "/register", consumes = "application/json")
	public Manufacturer addManufacturer(@RequestBody Manufacturer m) {
		System.out.println("added manufacturer: " + m.toString());
		return manufacturerService.addManufactrer(m);

	}

	@GetMapping(value = "/all", consumes = "application/json")
	public List<Manufacturer> getAllManufacturer() {
		return manufacturerService.getAllManufacturers();
	}

	@GetMapping(consumes = "application/json")
	@ResponseBody
	public ManufacturerSpecificResp getManufacturerDetails(@RequestParam String brandName) {
		return manufacturerService.getManufacturerDetails(brandName);
	}

	@PostMapping(value = "/login", consumes = "application/json")
	public ResponseEntity<?> authenticateManufacturer(@RequestBody LoginRequestDto dto) {
		try {
			return ResponseEntity.ok(manufacturerService.authenticateManufacturer(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PutMapping(value = "/reset", consumes = "application/json")
	public ResponseEntity<?> resetPassword(@RequestBody @Valid LoginRequestDto dto) {
		try {
			return ResponseEntity.ok(manufacturerService.updatePassword(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PostMapping(value = "/getbymail", consumes = "application/json")
	public ResponseEntity<?> getManufacturerByMail(@RequestBody @Valid EmailRequestDto dto) {
		try {
			return ResponseEntity.ok(manufacturerService.getManufacturerByMail(dto));
		} catch (RuntimeException e) {
			System.out.println("err in emp controller " + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PutMapping(value = "/update", consumes = "application/json")
	public ResponseEntity<?> updateManufacturer(@RequestBody @Valid ManufacturerSpecificResp manufacturer) {
		try {
			return ResponseEntity.ok(manufacturerService.updateManufacturer(manufacturer));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@GetMapping(value = "/requests", consumes = "application/json")
	public ResponseEntity<?> requestsForManufactrer(@RequestBody ManufacturerSpecificResp manufacturer) {
		try {
			return ResponseEntity.ok(requestService.requestsForManufacturer(manufacturer));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PutMapping(value = "/updaterequest", consumes = "application/json")
	public ResponseEntity<?> updateRequestStatus(@RequestBody @Valid RequestDto dto) {
		try {
			return ResponseEntity.ok(requestService.changeRequestStatus(dto.getStatus(), dto.getRequestId()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PostMapping("/check")
	public ResponseEntity<?> sendHash(@RequestBody @Valid EmailRequestDto emailDto) {
		try {
			return ResponseEntity.ok(manufacturerService.sendHash(emailDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
}
