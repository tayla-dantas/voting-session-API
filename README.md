# How to run this project 



## Build
Ive choosed docker to deploy the database, so in order to get the application build, installed, and deploy the mysql container I made a buildscript to automate. its in the rootfolder and called build-api.sh
In order to run it, you need to make sure you have vagrant properly installed and configurated. 
After running the script the application is fully deployed and you can run it as usual on intellij. 
 
## Postman collections 

In the folder collections theres all the requests to access the api funcionalities.
To use them, just import them in postman. 

## Design choices 

### Java

I choose java and springboot framework because its very good to build fast and with modular components. 

### Docker 

Docker makes it easier to deploy the external dependencies of the api without relying ou external servers or cloud providers. 

###Lombok 

I used lombok to make the code more clean. Also, I tried to implement builder using its anotation for it. 

## JPA repositories extending CrudRepository 

I chossed this interface because since Im not using pagination this will make the code less heavy without useless built-in classes. 

## How to use the API

Use /api/v1/schedules to create a schdule. 
You can verify if it was created by calling the /api/v1/schedules/{id} using the id provided in the last endpoint response. Notice that at first, the object votingSession is null because its not created yet. 
Then, create an voting session using /api/v1/votingSession.
After that, you can verify the voting session object as well by calling /api/v1/votingSession/{id} with the id provided in the last step. 
Then, you use /api/v1/votingSession/vote/{id} using the id of the voting session you create in previous steps to create votes. You can create as many votes as you want.
Finally, you can call /api/v1/votingSession to verify the votes or /api/v1/schedules/{id} to verify the whole schedule. 



## Future improvements 

One thing I that was very important but unfortunally the time not allowed was the unit test. I prioritized to deliver all the funcionalities first, but in real scenarios the full delivery of it its mandatory to contain the unit tests.
I also would make the design more simple in a future refactor.
