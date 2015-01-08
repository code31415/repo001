package ru.repo001

/**
 * Created by Ivan on 07.01.2015.
 */
class Book {

    String name

    String author

    boolean active = false

    Picture picture

    /* 0..10 */
    BigDecimal rating = new BigDecimal(1)

    static constraints = {
        name(nullable: false, blank: false, maxSize: 1024)
        author(nullable: false, blank: false, maxSize: 1024)
        rating(nullable: false, min: 0.0, max: 10.0)
        picture(nullable: true)
    }

    /** Custom mapping */
    static mapping = {
        id generator: 'sequence', params: [sequence: 'book_id_sequence']
    }

}
