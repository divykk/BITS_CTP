/**
 * 
 */
package net.codejava;

import java.util.List;


/**
 * @author KAMAL'S PERSONAL
 *
 */
public interface CategoryService {
	public List<Category> findall();
	public Category findById(int theId);
	public void save(Category theCategory);
	public void deleteById(int theId);
	//public void deleByName(String theName);
	
}
