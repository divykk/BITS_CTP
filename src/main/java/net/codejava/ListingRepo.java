package net.codejava;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ListingRepo extends JpaRepository<Listing, Integer> {
	
	@Query(value= "SELECT * FROM listings u WHERE u.username = :username AND u.availability=1",nativeQuery=true)
	public List<Listing> findAllByUserId(@Param("username")long theUserID);
	
}
