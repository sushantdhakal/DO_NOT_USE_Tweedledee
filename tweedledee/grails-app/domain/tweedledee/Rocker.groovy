package tweedledee

class Rocker {
    String text
    String dateCreated
    static belongsTo = [ account : Account ]
    static mapping = {
        sort dateCreated:'asc'
    }
    static constraints = {
        text nullable:false,size: 1..40
    }
}
