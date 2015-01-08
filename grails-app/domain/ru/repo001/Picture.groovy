package ru.repo001

/**
 * Created by Ivan on 08.01.2015.
 */
class Picture {

    /** File name  */
    String fileName

    /** File directory */
    String dir

    /** Image width/height */
    Integer width, height

    /** Content type */
    String contentType

    Long size

    static constraints = {
        fileName(size: 1..1024, nullable: true, blank: false)
        dir(size: 1..1024, nullable: true, blank: false)
        contentType(nullable: false, size: 1..64)
    }

    static mapping = {
        id generator: 'sequence', params: [sequence: 'picture_id_sequence']
    }
}
