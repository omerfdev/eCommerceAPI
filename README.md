Project Description
This project is an e-commerce application developed with Spring Boot. It provides a platform where customers can view products, create a cart, place orders, and view order history. Additionally, it includes functionalities such as updating product prices and inventory tracking.

Features
Customer Management:
Add new customers
Product Management:
Add new products
Update products
Delete products
View all products
Cart Management:
Create a cart
Add products to the cart
Remove products from the cart
Update the cart
Empty the cart
Order Management:
Create an order
View a specific order
View all orders for a customer
Services and Usage
Customer Management:
/addCustomer: Adds a new customer.
Product Management:
/getProduct: Retrieves a specific product.
/createProduct: Adds a new product.
/updateProduct: Updates an existing product.
/deleteProduct: Deletes a product.
/getAllProducts: Lists all products.
Cart Management:
/getCart: Retrieves the customer's cart.
/updateCart: Updates the customer's cart.
/emptyCart: Empties the customer's cart.
Order Management:
/placeOrder: Places an order for the customer.
/getOrderForCode: Retrieves a specific order.
/getAllOrdersForCustomer: Lists all orders for a customer.
Cart and Order Item Operations:
/addProductToCart: Adds a product to the cart.
/removeProductFromCart: Removes a product from the cart.
Inventory Tracking and Price Update
Tracks inventory status of products, preventing orders for out-of-stock items.
Allows updating product prices, with customers able to view the price they purchased at in their order history.
Getting Started
The project is created using Spring Initializr. Follow the steps below to run the project:

Install Dependencies: Open the project in a Java IDE and wait for the dependencies to be installed.
Run the Application: Start the application and verify that it runs on the specified port.
Using the Services: Perform operations using the provided services.
