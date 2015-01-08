import grails.util.Environment
import ru.repo001.Book
import ru.repo001.Picture

class BootStrap {

    def init = { servletContext ->
        Environment.executeForCurrentEnvironment {
            development {
                Picture picture = new Picture(height: 1, width: 1, size: 1)
                picture.contentType = "image/jpeg"
                picture.dir = "00/00/00/"
                picture.fileName = "01.jpg"
                picture.save()

                30.times {
                    Book book = new Book(name: "Повесть о ${it}", author: "Петров-${it} А.В.", active: true, picture: picture)
                    book.save(flush: true, failOnError: true)
                }
            }
        }
    }
    def destroy = {
    }
}
