package tweedledee

class ErrorController {

	static allowedMethods = [get: "GET"]

	def server(){
		render error:500,message:'Server Error'
	}
	def unauthorized(){
		render error:401,message:'Unauthorized Request'
	}
	def forbidden(){
		render error:403,message:'Forbidden Request'
	}
	def notfound(){
		render error:404,message:'Not Found'
	}
	def notallowed(){
		render error:405,message:'Request Not Allowed'
	}
	def unprocessable(){
		render error:422,message:'Request was well-formed but was unable to be followed due to semantic errors. Most likely due to passing an incorrect or invalid value.'
	}

}