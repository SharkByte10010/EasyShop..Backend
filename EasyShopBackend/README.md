# EasyShop Backend
## Overview
This is a Java e-commerce application using RESTFul API allowing users to register, login, and browse categories. Future additions to come.
![img.png](img.png)

 ## Search Functions
### By category
1. Electronics
2. Fashion
3. Home & Kitchen
### By Minimum or Maximum price
1. Custom Sliders
### Custom Search
1. Color
2. Price range
3. Category
## Bugs: First Bug(Search Functionality)
### The search method accepts four parameters: categoryId, minPrice, maxPrice, and color
![1st Bug Pic1.png](..%2F..%2F..%2FUsers%2FStudent%2FPictures%2F1st%20Bug%20Pic1.png)
As Seen Above


But it does not pass the needed maxPrice parameter inside the PreparedStatement
![1st Bug Pic3.png](..%2F..%2F..%2FUsers%2FStudent%2FPictures%2F1st%20Bug%20Pic3.png)
As Seen 


So to fix, we pass the parameter inside the PreparedStatement.
![1st Bug Pic2.png](..%2F..%2F..%2FUsers%2FStudent%2FPictures%2F1st%20Bug%20Pic2.png)

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