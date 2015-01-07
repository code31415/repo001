package ru.repo001

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

    def create() {

    }

    def save() {
        Book book = new Book()

        flash.success = 'Книга сохранена. Появится в рейтинге после успешной модерации. Спасибо за участие!'
        redirect(action: 'index')
    }

    def about() {

    }
}
