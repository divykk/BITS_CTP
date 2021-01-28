package net.codejava;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {
	@Autowired
	private CategoryService aCategoryService;
	
	@Autowired
	private ListingService aListingService;
	
	@Autowired
	private UserRepository theUserRepo;
	
	public AppController(CategoryService aCategoryService, ListingService aListingService, UserRepository theUserRepo) {
		this.aCategoryService = aCategoryService;
		this.aListingService = aListingService;
		this.theUserRepo = theUserRepo;
	}
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		return viewPage(model,1);
	}
	@RequestMapping("/page/{thePageNumber}")
	public String viewPage(Model model,@PathVariable(name="thePageNumber") int thePageNumber) {
		
		List<Category> categories = aCategoryService.findall();
		Page<Listing> listings= aListingService.findAllPagedSortedByTime(thePageNumber);
		List<Listing> listingstoShow= listings.getContent();
		//System.out.println(Arrays.toString(listingstoShow.toArray()));
		model.addAttribute("currentPage",thePageNumber);
		model.addAttribute("allCategories", categories);
		model.addAttribute("listings",listingstoShow);
		model.addAttribute("totalPages",listings.getTotalPages());
		model.addAttribute("totalItems",listings.getTotalElements());
		model.addAttribute("imgUtil",new ImageUtil());
		return "index";
	}
	
	@RequestMapping("/{listing_id}")
	public String showProduct(Model model,@PathVariable(name="listing_id")int theId ) {
		Listing aProduct= aListingService.findById(theId);
		model.addAttribute("product",aProduct);
		model.addAttribute("imgUtil",new ImageUtil());
		return "single-product-normal";
	}
	
	@RequestMapping({"loginORregister","/login"})
	public String loginPage(Model model) {
		model.addAttribute("user", new User());
		return "login-register";
	}
	@RequestMapping({"/process_register"})
	public String processRegister(@ModelAttribute @Valid User user,BindingResult bindingResult) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    if(bindingResult.hasErrors()) {
	    	return "login-register";
	    }
	    theUserRepo.save(user);
	    return "redirect:/login";
	    
	}
	@RequestMapping("/myaccount")
	public String myaccountPge(Principal principal,@AuthenticationPrincipal MyUserDetails userDetails,Model model) {
		long userId=userDetails.getUserID();
		List<Listing> userListings= aListingService.findAllByUserID(userId); 
		model.addAttribute("userListings",userListings);
		return "my-account";
	} 
	@RequestMapping("/newlisting")
	public String newListingForm(Model model) {
		List<Category> categories = aCategoryService.findall();
		model.addAttribute("listing",new Listing());
		model.addAttribute("categories",categories);
		model.addAttribute("submit","Add"); //for using same for upload and updating treehouse
		model.addAttribute("heading","Add New Listing"); //for using same for upload and updating
		model.addAttribute("action","/add");
		return "new_listing";
	}
	@InitBinder
	protected void initBinder(HttpServletRequest request,
		ServletRequestDataBinder binder) throws ServletException {
			binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addNewListing(Listing listing,@Valid @RequestParam("file1") MultipartFile file1,@Valid @RequestParam("file2") MultipartFile file2,@Valid @RequestParam("file3") MultipartFile file3,@Valid @RequestParam("file4") MultipartFile file4, RedirectAttributes redirectttributes,@AuthenticationPrincipal MyUserDetails userDetails) {

		try {
			listing.setImage_1(file1.getBytes());
			listing.setImage_2(file2.getBytes());
			listing.setImage_3(file3.getBytes());
			listing.setImage_4(file4.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username= userDetails.getUsername();
		User user= theUserRepo.getUserByUsername(username);
		listing.setUser(user);
		aListingService.save(listing);
		redirectttributes.addFlashAttribute("message",new FlashMessage("GIF successfully uploaded!", FlashMessage.Status.SUCCESS));
		return String.format("redirect:/%s",listing.getListing_id());
	}
	
	
	
	@RequestMapping(value="/myaccount/delete/{listingID}",method = RequestMethod.POST)
	public String deleteAListing(@PathVariable int listingID,Principal principal,@AuthenticationPrincipal MyUserDetails userDetails,Model model) {
		Listing listing= aListingService.findById(listingID);
		long userId=userDetails.getUserID();
		listing.setAvailability(false);
		aListingService.save(listing);
		return "redirect:/myaccount";
	}
}
