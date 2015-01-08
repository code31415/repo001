package ru.repo001

/**
 * Created by Ivan on 08.01.2015.
 */
class AdminBookController {

    def pictureService

    def index(){
        redirect(action: 'list')
    }

    def list() {
        def books = Book.list(sort: 'id')
        [books: books]
    }

    def edit() {
        Book book = Book.get(params.long('id'))

        [book: book]
    }


    def update() {
        Book book = Book.get(params.long('id'))
        bindData(book, params)

        book.validate()
        if (book.hasErrors() || !book.save(flush: true)) {
            render(view: 'edit', model: [book: book])
        }

        if (params.pictureFile){
            Picture picture = pictureService.savePicture(params.pictureFile)
            book.picture = picture
        }

        flash.success = "книга успешно отредактирована"
        redirect(action: 'list')
    }
}
