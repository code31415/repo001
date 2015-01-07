import grails.util.Environment
import ru.repo001.Book

class BootStrap {

    def init = { servletContext ->
        Environment.executeForCurrentEnvironment {
            development {
                30.times {
                    Book book = new Book(name: "Повесть о ${it}", author: "Петров-${it} А.В.", active: true)
                    book.save(flush: true, failOnError: true)
                }
            }
        }
    }
    def destroy = {
    }
}
