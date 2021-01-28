package net.codejava;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ListingServiceImpl implements ListingService {
	
	
	private ListingRepo theListingRepo;
	@Override
	public List<Listing> findAllByUserID(long theUserId) {
		// TODO Auto-generated method stub
		return theListingRepo.findAllByUserId(theUserId);
		
	}

	@Autowired
	public ListingServiceImpl(ListingRepo theListingRepo) {
		this.theListingRepo = theListingRepo;
	}

	@Override
	public List<Listing> findAll() {
		// TODO Auto-generated method stub
		return theListingRepo.findAll();
	}
	
	@Override
	public Listing findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Listing> result = theListingRepo.findById(theId);
		Listing theListing= null;
		if (result.isPresent()) {
			theListing = result.get();
			}
			else {
			// we didn't find the employee					
			throw new RuntimeException("Did not find Lisitng id - " + theId);					
			}									
			return theListing;				
	}

	@Override
	@Transactional
	public void save(Listing theListing) {
		// TODO Auto-generated method stub
		theListingRepo.save(theListing);
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		theListingRepo.deleteById(theId);
	}

	@Override
	public List<Listing> findAllSortedByTime() {
		// TODO Auto-generated method stub
		return theListingRepo.findAll(Sort.by(Direction.DESC, "dateUploaded"));
	}

	@Override
	public List<Listing> findAllSortedByPrice() {
		// TODO Auto-generated method stub
		return theListingRepo.findAll(Sort.by(Direction.DESC, "price"));
	}

	@Override
	public Page<Listing> findAllPaged(int pageNumber) {
		// TODO Auto-generated method stub
		int pageSize=9;
		Pageable pageable= PageRequest.of(pageNumber-1, pageSize);
		return theListingRepo.findAll(pageable);
	}

	@Override
	public Page<Listing> findAllPagedSortedByPrice(int pageNumber) {
		// TODO Auto-generated method stub
		int pageSize=9;
		Sort sort= Sort.by("price").ascending();
		Pageable pageable= PageRequest.of(pageNumber-1, pageSize,sort);
		return theListingRepo.findAll(pageable);
	}

	@Override
	public Page<Listing> findAllPagedSortedByTime(int pageNumber) {
		// TODO Auto-generated method stub
		int pageSize=9;
		Sort sort= Sort.by("dateUploaded").descending();
		Pageable pageable= PageRequest.of(pageNumber-1, pageSize,sort);
		return theListingRepo.findAll(pageable);
	}
}
