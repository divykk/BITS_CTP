package net.codejava;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ListingService {
	public List<Listing> findAll();
	public Listing findById(int theId);
	public void save(Listing theListing);
	public void deleteById(int theId);
	public List<Listing> findAllSortedByTime();
	public List<Listing> findAllSortedByPrice();
	public Page<Listing> findAllPaged(int pageNumber);
	public Page<Listing> findAllPagedSortedByPrice(int pageNumber);
	public Page<Listing> findAllPagedSortedByTime(int pageNumber);
	public List<Listing> findAllByUserID(long theUserId);
}
