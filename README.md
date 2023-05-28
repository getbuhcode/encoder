# Encoder

This application takes in plain text and encode it based on an encoding reference table.

OOP Concept in this application:
1. Inheritance: The Encoder Interface is inherited by the MessageEncoder class through the "implements" keyword.

2. Encapsulation: The offset variable and some methods in the MessageEncoder class has a private access modifier to prevent access from other class.

3. Polymorphism: The encode method have two types based on different signatures, one of which will generate the offset randomly while the other requires the offset to be defined explicitly.

4. Abstraction: Abstraction is achieved by hiding the details of how the methods are implemented. 
