/**
 * Created by Ivan on 07.01.2015.
 */
class BookService {

    def list() {
        def result = Book.createCriteria().list() {

            order('rating', 'desc')
            order('id', 'desc')
        }

        return [list: result]
    }
}
