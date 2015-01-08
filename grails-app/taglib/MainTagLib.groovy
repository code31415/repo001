import ru.repo001.Picture
import ru.repo001.PictureSize;
/**
 * Created by Ivan on 08.01.2015.
 */
class MainTagLib {

    def pictureService

    def picture = { attrs ->
        Picture picture = attrs.picture
        PictureSize size = attrs.size
        out << pictureService.getUrlPathToImage(picture) + size.postfix + ".jpg"
    }
}
