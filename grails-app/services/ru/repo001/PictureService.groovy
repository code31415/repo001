package ru.repo001

/**
 * Created by Ivan on 08.01.2015.
 */
class PictureService {

    def grailsApplication

    /**
     * Picture dir calculation algorithm. Id is converted to 8 digits hex string
     * and first three two-digits groups are used as folded dirs.
     * Ex.:
     * id 10 will be 0000000a string will be 00\00\00\ dir.
     * id 256 will be 00000100 string will be 00\00\01\ dir.
     * id 257 will be 00000101 string will be 00\00\01\ dir.
     *
     * @param picture picture
     * @return picture dir
     */
    def getDir(picture) {
        def idStr = Integer.toHexString(picture.id as int)
        for (int i = idStr.length(); i < 8; i++) {
            idStr = '0' + idStr
        }
        def dir = ''
        for (int i = 0; i < 6; i++) {
            dir = dir + idStr[i]
            if (i % 2 == 1) {
                dir = dir + '/'
            }
        }
        dir
    }

    /** Path to converted image */
    def getPathToImage(Picture picture) {
        def dir = getDir(picture)
        def path = grailsApplication.config.picture.upload.dir + dir
        //make dirs
        new File(path).mkdirs()
        return path + getPictureFileName(picture.id) + getExtension(picture.contentType)
    }

    def getFullPathToImage(Picture picture) {
        def dir = getDir(picture)
        def path = grailsApplication.config.picture.url.prefix + dir
        return path + picture.fileName
    }

    /** Filename of saved picture */
    def getPictureFileName (pictureId) {
        return Integer.toHexString(pictureId as int).padLeft(8, '0')[-2,-1]
    }


    def savePicture(org.springframework.web.multipart.commons.CommonsMultipartFile file) {
        Picture picture = new Picture(size: file.getFileItem().getSize(), contentType: file.getFileItem().getContentType())
        def dimensions = file.getFileItem().getInputStream().withStream { is -> ImageScaler.getImageInfo(is) }
        picture.width = dimensions.width
        picture.height = dimensions.height
        picture.save(flush: true, failOnError: true) //to get id

        BufferedInputStream stream = new BufferedInputStream(file.getFileItem().getInputStream())
        stream.mark(Integer.MAX_VALUE as int)
        stream.reset()
        File fileX = new File(getPathToImage(picture))
        fileX.delete()
        fileX << stream
        stream.close()


        picture.dir = getDir(picture)
        picture.fileName = getPictureFileName(picture.id as int) + getExtension(picture.contentType)
        picture.save(flush: true, failOnError: true)

        picture
    }

    private static String getExtension(String contentType) {
        if (contentType == "image/bmp") {
            return ".bmp"
        }
        if (contentType == "image/jpeg") {
            return ".jpg"
        }
        if (contentType == "image/png") {
            return ".png"
        }
        if (contentType == "image/gif") {
            return ".gif"
        }
        if (contentType == "application/x-shockwave-flash") {
            return ".swf"
        }

        return ""
    }
}
