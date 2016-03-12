package tweedledee

class ErrorController {

	static allowedMethods = [get: "GET"]

	def server(){
		response.status=500
		render(contentType:'application/json')
		{ 
			error=500
			message='Server Error' 
		}
	}
	def unauthorized(){
		response.status=401
		render(contentType:'application/json')
		{ 
			error=401
			message='Unauthorized Request' 
		}
	}
	def forbidden(){
		response.status=403
		render(contentType:'application/json')
		{ 
			error=403
			message='Forbidden Request' 
		}
	}
	def notfound(){
		response.status=404
		render(contentType:'application/json')
		{ 
			error=404
			message='Not Found' 
		}
	}
	def notallowed(){
		response.status=405
		render(contentType:'application/json')
		{ 
			error=405
			message='Request Not Allowed' 
		}
	}
	def unprocessable(){
		response.status=422
		render(contentType:'application/json')
		{ 
			error=422
			message='Request was well-formed but was unable to be followed due to semantic errors. Most likely due to passing an incorrect or invalid value.' 
		}
	}

}