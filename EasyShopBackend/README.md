# EasyShop Backend
This is a Java e-commerce application using RESTFul API allowing users to register, login, and browse categories. Future additions to come.
![img.png](img.png)
## Bugs: First Bug(Search Functionality)
### The search method accepts four parameters: categoryId, minPrice, maxPrice, and color
![1st Bug Pic1.png](..%2F..%2F..%2FUsers%2FStudent%2FPictures%2F1st%20Bug%20Pic1.png)
But it does not pass the needed maxPrice parameter inside the PreparedStatement
![1st Bug Pic2.png](..%2F..%2F..%2FUsers%2FStudent%2FPictures%2F1st%20Bug%20Pic2.png)
So to fix, we pass the parameter inside the PreparedStatement.
![1st Bug Pic3.png](..%2F..%2F..%2FUsers%2FStudent%2FPictures%2F1st%20Bug%20Pic3.png)
## Bugs: Second Bug(Updating Products)
The updateProduct method body uses the productDao.create method implementation instead of the .update method implementation
![2nd Bug Pic1.png](..%2F..%2F..%2FUsers%2FStudent%2FPictures%2F2nd%20Bug%20Pic1.png)
To fix we use the .update method implementation instead
![2nd Bug Pic2.png](..%2F..%2F..%2FUsers%2FStudent%2FPictures%2F2nd%20Bug%20Pic2.png)

## Postman Testing
This API Application relies on Postman testing to ensure application endpoints and logic are fully functional.
![img_1.png](img_1.png)

## Acknowledgements 
I'd like to give a bunch of thanks to our instructor Paul as well as my Pluralsight classmates who me helped along the way. You guys are the best.