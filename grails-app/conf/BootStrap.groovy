import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        Environment.executeForCurrentEnvironment {
            development {
                30.times {
                    Book book = new Book(name: "Повесть о ${it}", author: "Петров-${it} А.В.")
                    book.save(flush: true, failOnError: true)
                }
            }
        }
    }
    def destroy = {
    }
}
