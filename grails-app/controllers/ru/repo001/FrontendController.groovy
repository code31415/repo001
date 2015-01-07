package ru.repo001

/**
 * Created by Ivan on 07.01.2015.
 */
class FrontendController {

    def bookService

    def simpleCaptchaService

    def index() {
        def books = bookService.list()

        [books: books.list]
    }

    def book() {
        def book = Book.get(params.long('id'))

        [book: book]
    }

    def create() {
        [book: new Book()]
    }

    def save() {
        Book book = new Book()
        bindData(book, [name: params.name, author: params.author])
        book.validate()
        boolean captchaValid = simpleCaptchaService.validateCaptcha(params.captcha)
        if (book.hasErrors() || !captchaValid  || !book.save(flush: true)) {
            render(view: 'create', model: [book: book, captchaValid: captchaValid])
            return
        }

        flash.success = 'Книга сохранена. Появится в рейтинге после успешной модерации. Спасибо за участие!'
        redirect(action: 'index')
    }

    def about() {

    }
}
