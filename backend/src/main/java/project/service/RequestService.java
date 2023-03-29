package project.service;

import java.util.List;

import project.dto.RequestDto;
import project.entity.Request;
import project.entity.Status;
import project.response.ManufacturerSpecificResp;

public interface RequestService {
	
	RequestDto addNewRequest(RequestDto service);
	
	List<Request> requestsForManufacturer(ManufacturerSpecificResp manufacturer);
	
	int changeRequestStatus(Status status, Long requestId);
}
