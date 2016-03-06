package tweedledee

class ErrorController {

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

}