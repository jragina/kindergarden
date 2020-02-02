package com.example.demo.kindergarden.controller;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.demo.kindergarden.model.ChildrenModel;
import com.example.demo.kindergarden.model.KindergardenModel;
import com.example.demo.kindergarden.service.KindergardenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TO-DO: Remove error messages and display static message- security
@RestController
public class KindergardenController {

	@Autowired
	private final KindergardenService kindergardenService;


    public KindergardenController( final KindergardenService kindergardenService) {
         this.kindergardenService = kindergardenService;
	}
	
	@GetMapping("/children/all")
	public ResponseEntity<Iterable<ChildrenModel>> getAllChildren() {
		try{
		return new ResponseEntity(kindergardenService.getAllChildren(),HttpStatus.OK);
		}catch(Error e){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/kindergarden/all")
	public ResponseEntity<Iterable<KindergardenModel>> getAllKindergardens() {
		try{
			return new ResponseEntity(kindergardenService.getAllKindergardens(),HttpStatus.OK);
		}catch(Error e){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/children/{id}")
	public ResponseEntity<ChildrenModel> getChildrenModelById(@PathVariable("id") String id)
			throws NoSuchElementException {
		Optional<ChildrenModel> childrenomdel = kindergardenService.findAllChildrenById(id);
		try{
		//if (childrenomdel.isPresent()) {
			return new ResponseEntity(childrenomdel.get(), HttpStatus.OK);
	//	} else {
		//	return new ResponseEntity(HttpStatus.NOT_FOUND);
		//}
	}catch(Error e){
			return new ResponseEntity(HttpStatus.NOT_FOUND);

	}
	}

	@GetMapping("/kindergarden/{id}")
	public ResponseEntity<KindergardenModel> findAllKindergardenById(@PathVariable("id") String id)
			throws NoSuchElementException {
		try {
			Optional<KindergardenModel> kindergardenOptionalModel = kindergardenService
					.findAllKindergardenById(Long.parseLong(id));
			//if (kindergardenOptionalModel.isPresent()) {
				return new ResponseEntity(kindergardenOptionalModel.get(), HttpStatus.OK);
			//} else {
			//	return new ResponseEntity(HttpStatus.NOT_FOUND);
			//}
		} catch (Exception e) {

			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/children/create")
	public ResponseEntity<ChildrenModel> createChildren(@RequestBody ChildrenModel childrenModel) {
		try {
			return new ResponseEntity(kindergardenService.saveChildren(childrenModel), HttpStatus.CREATED);
		} catch (Exception e) {
			
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/kindergarden/create")
	public ResponseEntity<KindergardenModel> createKindergarden(@RequestBody KindergardenModel kindergardenModel) {
		try {
			return new ResponseEntity(kindergardenService.saveKindergarden(kindergardenModel), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/kindergarden/{id}/queue/add")
	public ResponseEntity<Map<Integer, ChildrenModel>> addToQueueByIdCode(@PathVariable("id") String kindergardenId,
			@RequestBody String childrenIdentityCode) {
		try {
			return new ResponseEntity(kindergardenService.addToQueueByIdCode(Long.parseLong(kindergardenId), childrenIdentityCode), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_MODIFIED);
		}
	}

	@GetMapping("/kindergarden/{id}/queue")
	public ResponseEntity<Map<Integer, ChildrenModel>> fetchPrioritizedQueue(@PathVariable("id") String kindergardenId){
		 try {
		return new ResponseEntity(kindergardenService.getPrioritizedListforKindergarden(Long.parseLong(kindergardenId)),HttpStatus.OK);
		 } catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_MODIFIED);
		 }
	}

	@PostMapping("/kindergarden/{id}/queue/create-add-by-model")
	public ResponseEntity<Map<Integer, ChildrenModel>> addToQueueByModel(@PathVariable("id") String kindergardenId,
			@RequestBody ChildrenModel childrenModel) {
		try {
			return new ResponseEntity(kindergardenService.addToQueueByModel(Long.parseLong(kindergardenId), childrenModel),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_MODIFIED);
		}
	}

	@PostMapping("/kindergarden/{id}/queue/remove-by-id")
	public ResponseEntity<Map<Integer, ChildrenModel>> removeFromQueueById(@PathVariable("id") String kindergardenId,
			@RequestBody String childrenIdentityCode) {
		try {
			return new ResponseEntity( kindergardenService.removeFromQueue(Long.parseLong(kindergardenId), childrenIdentityCode), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_MODIFIED);
		}
	}
}