Feature: Develop a Java based application that support hello world function
	
	Scenario: Should be able to invoke sayHello method
		
		Given there is an class by name App
		When I invoke sayHello method
		Then I expect the response to be "Hello DevOps!"
