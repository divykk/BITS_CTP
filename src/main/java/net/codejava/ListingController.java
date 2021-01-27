package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ListingController {

	@Autowired
	private ListingService aListingService;
	@Autowired
	private CategoryService aCategoryService;
	
}
