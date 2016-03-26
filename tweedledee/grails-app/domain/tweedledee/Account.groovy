package tweedledee

class Account {

    transient springSecurityService

    String handle
    String name
    String email
    String password
    Date dateCreated

    static hasMany = [ followers : Account, following : Account, messages : Message ]

    static mapping = {
        messages sort:'dateCreated'
    }
    static constraints = {
        handle nullable:false, unique:true
        name nullable:false
        email nullable:false,email:true,unique:true
        password nullable:false,matches:"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}\$"
    }
}
