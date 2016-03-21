package tweedledee

class BootStrap {

    def init = { servletContext ->
        new Account(handle:'Ojuwaykai1',name:'ookai 1bnmm11',email:'at@sasdf1.org',password:'12345678Nm').save(failOnError:true)
        new Account(handle:'Ojuwaykai12',name:'ookai 1bnmm22',email:'at@sasdf2.org',password:'12345678Nm').save(failOnError:true)
        new Account(handle:'Ojuwaykai13',name:'ookai 1bnmm33',email:'at@sasdf3.org',password:'12345678Nm').save(failOnError:true)
        new Account(handle:'Ojuwaykai14',name:'ookai 1bnmm44',email:'at@sasdf4.org',password:'12345678Nm').save(failOnError:true)
        new Message(test:'asdf  adfadafd',account:1).save(failOnError:true)
        new Message(test:'23safda ',account:2).save(failOnError:true)
        new Message(test:'adfasd lk fkasdjsslkfajlj jfdafa ',account:3).save(failOnError:true)
        new Message(test:'ad aadfdf',account:2).save(failOnError:true)
        new Message(test:'oiewnnoidf ',account:3).save(failOnError:true)
        new Message(test:' asdf  asdd!!!',account:4).save(failOnError:true)
    }
    //def destroy = {
    //}
}