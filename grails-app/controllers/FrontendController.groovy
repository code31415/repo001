/**
 * Created by Ivan on 07.01.2015.
 */
class FrontendController {

    def bookService

    def index() {
        def books = bookService.list()

        [books: books.list]
    }

    def book() {
        def book = Book.get(params.long('id'))

        [book: book]
    }

}
