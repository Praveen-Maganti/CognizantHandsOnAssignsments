**Conversion of Request Payload to Country Bean**

When a client sends a JSON request, Spring detects the Jackson library to parse the JSON. Jackson creates a
constructor and maps each JSON property to the corresponding setter method (for example, setName() ) using Java Reflection. 
Once the object is populated, Spring passes it to the controller method. When the method returns the object, 
Jackson converts it back into JSON and sends it as the HTTP response.

**Bean Naming Conventions**

A Java Bean should have:
1)Private fields
2)A public no-argument constructor
3)Public getter and setter methods

For example, the property name should have getName() and setName(), while code should have getCode() and setCode(). 
Following these conventions enables Spring and Jackson to map JSON data to Java objects automatically.