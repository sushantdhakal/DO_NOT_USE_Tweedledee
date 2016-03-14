# MSSE-2016-WebApplication
This repository contains development code for a messaging application, similar to twitter. The application is primarily based on the Grails Framework with Geb for testing. This project fulfills the graduate degree requirements for Master's in Computer Software Engineering at the University of Minnesota. 

The primary contributors to this project are: Paul Michalek and Sushant Dhakal

#Phase 2 update: Completed - March 13, 2016
## API Description & Requirements:
*Create a REST endpoint that receives JSON data to create an Account
** Controller: AccountController
**Tests: AccountResourceFunctionalSpec

*Return an error response from the create Account endpoint if the account values are invalid
** Controller: AccountController
**Tests: AccountResourceFunctionalSpec

*Create a REST endpoint that returns JSON data with Account values for a user based on an account id or handle address
** Controller: AccountController
**Tests: AccountResourceFunctionalSpec

*Create a REST endpoint will create a Message given a specified, Account id or handle and message text
** Controller: MessageController 
**Tests: MessageResourceFunctionalSpec

*Return an error response from the create Message endpoint if user is not found or message text is not valid 
** Controller: MessageController 
**Tests: MessageResourceFunctionalSpec

*Create a REST endpoint that will return the most recent messages for an Account. The endpoint must honor a limit parameter (max) that caps the number of responses. The default limit is 10.
** Controller: MessageController 
**Tests: MessageResourceFunctionalSpec

*Support an offset parameter (offset) into the recent Messages endpoint to provide paged responses.
** Controller: MessageController 
**Tests: MessageResourceFunctionalSpec

*Create a REST endpoint that will search for messages containing a specified search term. Each response value will be a JSON object containing the Message details (text, date) as well as the Account. The search parameter shall be provided as a JSON object in the body of the RESTful call. For eg. {"searchText":"abc"}
** Controller: MessageController 
**Tests: MessageResourceFunctionalSpec

*Create a REST endpoint that will allow one account to follow another.
** Controller: AccountController	
** Tests: FollowerFunctionalSpec

*For the endpoint created for requirement A3, add properties for total counts of followers and following for the account.
** Controller: AccountController	
** Tests: FollowerFunctionalSpec

*Add an endpoint to get the followers for an account. This will return the details about the followers (handle, name, email, id) and add the limit and offset logic implemented for messages to this endpoint.
** Controller: AccountController	
** Tests: FollowerFunctionalSpec

*Create a ‘feed’ endpoint which will return the most recent messages by followers, include a response limit parameter, include a parameter to only look for messages after a specified date.
** Controller: AccountController	
** Tests: FollowerFunctionalSpec
