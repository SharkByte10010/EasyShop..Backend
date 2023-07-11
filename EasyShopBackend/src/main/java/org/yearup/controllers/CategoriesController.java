package org.yearup.controllers;
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

// add the annotations to make this a REST controller
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
// add annotation to allow cross site origin requests

@RestController // Added @RestController annotation to make this a REST controller and handle HTTPRequests
@RequestMapping("/categories") // Added @RequestMapping annotation to define the URL path and mapped it to "categories"
@CrossOrigin //Added CrossOrigin annotation to allow cross site origin requests
public class CategoriesController
{
    // Created Constructor to initialize CategoryDao and ProductDao dependencies.
        @Autowired // Added Autowired Annotation to inject the categoryDao and ProductDao
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }
    // Created Constructor to initialize CategoryDao and ProductDao dependencies.

    private CategoryDao categoryDao;


    private ProductDao productDao;




    // add the appropriate annotation for a get action
    @GetMapping("") // Added GetMapping annotation that will handle Get actions and mapped to the root URL path
    public List<Category> getAll()
    {
        // find and return all categories
        return categoryDao.getAllCategories();
    }

    // add the appropriate annotation for a get action


    @GetMapping("{id}") //Added GetMapping annotation and mapped it to the PathVariable "id"  of the categories URL
    public Category getById(@PathVariable int id) { // Method signature so that it takes the integer parameter "id' that was annotated PathVariable
        Category category = categoryDao.getById(id); // Gets category with specific Id from categoryDao by calling getById method passing "id" parameter

        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    //If the category with the specified id is not found we throw a ResponseStatusException.
        return category;
    }


    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    @PreAuthorize("permitALL")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // get a list of products by categoryId
        return productDao.listByCategoryId(categoryId);
    }

    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category addCategory(@RequestBody Category category)
    {
        // insert the category
        return categoryDao.create(category);
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @PutMapping("{categoryID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updatepCategory(@PathVariable int id, @RequestBody Category category)

    {
        categoryDao.update(id, category);
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function

    @DeleteMapping("{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(@PathVariable int categoryId) {
        Category category = categoryDao.getById(categoryId);
        if (category != null) {
            categoryDao.delete(categoryId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }



}
