import ru.repo001.Picture;
/**
 * Created by Ivan on 08.01.2015.
 */
class MainTagLib {

    def pictureService

    def picture = { attrs ->
        Picture picture = attrs.picture
        out << pictureService.getFullPathToImage(picture)
    }
}
