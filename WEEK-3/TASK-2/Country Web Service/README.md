1)What happens in the controller method?

When you visit: GET /country

Spring performs these steps:
1)The request reaches the DispatcherServlet.
2)It finds the CountryController.
3)The getCountryIndia() method is invoked.
4)ClassPathXmlApplicationContext("country.xml") loads the Spring XML configuration.
5)The country bean is retrieved from the Spring container.
6)The Country object is returned.

2)How the bean is converted into JSON reponse?

Since the class is annotated with @RestController, Spring Boot automatically:

1)Uses @ResponseBody to write the return value into the HTTP response.
2)Uses the Jackson library (included with spring-boot-starter-web) to convert the Java object into JSON.

