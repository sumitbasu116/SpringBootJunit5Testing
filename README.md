# Testing class Guidelines
 - Test class and method name should be the same as the actual class or method name appended with `Test` at last.
 - Use `@Mock` annotation to mock a stand alone class when it does not have any dependant mock object.
 - Use `@InjectMocks` annotation to mock a stand alone class when it has a dependant mock object.
- The test data should not go into the actual DB. To mock the repository call, we can use `Mockito` when function.
Mockito **`when`** is defined or read as when addProduct method of ProductService called with a product then return then the same product.

**We can casually divides a test class method into four sections:**
1. Data Preparation
2. Mocking call logic if any
3. Actual test method call
4. Assertions logic

# Test class life cycle

![image](https://github.com/user-attachments/assets/109a3206-acd7-4789-846d-c83075db4da8)


# Testing of delete and void method
- we can use **`doNothing`** method of `Mockito` to handle void return type for a test method.
- Suppose, we have a delete method, we have use **`verify`** method of `Mockito` to check a internal mock dependent object method called n times.

# Testing of private method
- private method testing can be done by Java reflection. First, get the declared method and then invoke it.
