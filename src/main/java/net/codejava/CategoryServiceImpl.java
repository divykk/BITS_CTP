package net.codejava;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepo categoryRepository;
	@Autowired
	public CategoryServiceImpl(CategoryRepo theCategoryRepo)
	{
		categoryRepository= theCategoryRepo;
	}
	@Override
	public List<Category> findall() {
		// TODO Auto-generated method stub
		return  categoryRepository.findAll();
		
	}

	@Override
	public Category findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Category> result = categoryRepository.findById(theId);
		Category theCategory= null;
		if (result.isPresent()) {
			theCategory = result.get();
			}
			else {
			// we didn't find the employee					
			throw new RuntimeException("Did not find category id - " + theId);					
			}									
			return theCategory;				
	}

	@Override
	public void save(Category theCategory) {
		// TODO Auto-generated method stub
		categoryRepository.save(theCategory);
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(theId);
	}

	/*@Override
	public void deleByName(String theName) {
		// TODO Auto-generated method stub

	}*/

}
