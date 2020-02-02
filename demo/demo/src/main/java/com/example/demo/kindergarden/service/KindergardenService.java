package com.example.demo.kindergarden.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.kindergarden.KindergardenUtils;
import com.example.demo.kindergarden.model.*;
import com.example.demo.kindergarden.repos.*;

@Service
public class KindergardenService {

	private final ChildrenModelRepository childrenModelRepository;
	private final KindergardenRepository kindergardenRepository;

	@Autowired
	public KindergardenService(final ChildrenModelRepository childrenModelRepository, final KindergardenRepository kindergardenRepository) {
	   this.childrenModelRepository = childrenModelRepository;
	   this.kindergardenRepository = kindergardenRepository;
	}

	public Iterable<ChildrenModel> getAllChildren() {
		return childrenModelRepository.findAll();
	}

	public Iterable<KindergardenModel> getAllKindergardens() {
		return kindergardenRepository.findAll();
	}

	public Optional<ChildrenModel> findAllChildrenById(String id) {
		return childrenModelRepository.findById(id);
		//messes up with tests
		/*Optional<ChildrenModel> optionalChildrenModel = childrenModelRepository.findById(id);
		if (optionalChildrenModel.isPresent()) {
			return optionalChildrenModel;
		} else {
			return Optional.empty();
		}*/
	}

	public Optional<KindergardenModel> findAllKindergardenById(Long id) {
		return kindergardenRepository.findById(id);
		
		//messes up with tests
		/*Optional<KindergardenModel> optionalKindergardenModel = kindergardenRepository.findById(id);
		if (optionalKindergardenModel.isPresent()) {
			return optionalKindergardenModel;
		} else {
			return Optional.empty();
		}*/
	}

	public ChildrenModel saveChildren(ChildrenModel childrenModel) {
		return childrenModelRepository.save(childrenModel);
	}

	public KindergardenModel saveKindergarden(KindergardenModel kindergardenModel) {
		return kindergardenRepository.save(kindergardenModel);
	}

	public Map<Integer, ChildrenModel> addToQueueByIdCode(Long kindergardenId, String childrenIdentityCode) {
		//messes up with tests
	//	try {
			Optional<KindergardenModel> kindergarden = findAllKindergardenById(kindergardenId);
			Optional<ChildrenModel> candidate = findAllChildrenById(childrenIdentityCode);
			//if (!Objects.equals(Optional.empty(), kindergarden) && !Objects.equals(Optional.empty(), candidate)) {
				KindergardenModel kindergardenModel = kindergarden.get();
				List<ChildrenModel> childrenInQueue = kindergardenModel.getChildrenQueue();
				childrenInQueue.add(candidate.get());
				kindergardenModel.setChildrenQueue(childrenInQueue);
				KindergardenModel updatedKindergarden = kindergardenRepository.save(kindergardenModel);
				return KindergardenUtils.getPrioritizedListforKindergarden(updatedKindergarden);
			//}
/*
			return Collections.EMPTY_MAP;
		} catch (Exception e) {
			return Collections.EMPTY_MAP;
		}*/
	}

	public Map<Integer, ChildrenModel> addToQueueByModel(Long kindergardenId, ChildrenModel candidate) {
		//messes up with tests
		//try {
			Optional<KindergardenModel> kindergarden = findAllKindergardenById(kindergardenId);
		//	if (!Objects.equals(Optional.empty(), kindergarden)) {
				KindergardenModel kindergardenModel = kindergarden.get();
				List<ChildrenModel> childrenInQueue = kindergardenModel.getChildrenQueue();
				childrenInQueue.add(candidate);
				kindergardenModel.setChildrenQueue(childrenInQueue);
				KindergardenModel updatedKindergarden = kindergardenRepository.save(kindergardenModel);
				return KindergardenUtils.getPrioritizedListforKindergarden(updatedKindergarden);
		/*	}

			return Collections.EMPTY_MAP;
		} catch (Exception e) {
			return Collections.EMPTY_MAP;
		}*/
	}

	public Map<Integer, ChildrenModel> removeFromQueue(Long kindergardenId, String childrenIdentityCode) {
		//messes up with tests
		//try {
			Optional<KindergardenModel> kindergarden = findAllKindergardenById(kindergardenId);
			// Optional<ChildrenModel> candidate =
			// findAllChildrenById(childrenIdentityCode);
	//		if (!Objects.equals(Optional.empty(), kindergarden)) {
				KindergardenModel kindergardenModel = kindergarden.get();
				List<ChildrenModel> childrenInQueue = kindergardenModel.getChildrenQueue();
				List<ChildrenModel> newChildrenQueue = new ArrayList<>();
				childrenInQueue.forEach(children -> {
					if (!Objects.equals(children.getIdentityCode(), childrenIdentityCode)) {
						newChildrenQueue.add(children);
					}
				});
				kindergardenModel.setChildrenQueue(newChildrenQueue);
				KindergardenModel updatedKindergarden = kindergardenRepository.save(kindergardenModel);
				return KindergardenUtils.getPrioritizedListforKindergarden(updatedKindergarden);
		/*	}

			return Collections.EMPTY_MAP;
		} catch (Exception e) {
			return Collections.EMPTY_MAP;
		}*/
	}

	public Map<Integer, ChildrenModel> getPrioritizedListforKindergarden(Long kindergardenId) {
		//messes up with tests
		//try {
			Optional<KindergardenModel> kindergarden = findAllKindergardenById(kindergardenId);
			//if (!Objects.equals(Optional.empty(), kindergarden)) {
				KindergardenModel kindergardenModel = kindergarden.get();
				return KindergardenUtils.getPrioritizedListforKindergarden(kindergardenModel);
		//	}

		//	return Collections.EMPTY_MAP;
	//	} catch (Exception e) {
	//		return Collections.EMPTY_MAP;
	//	}
	}

}