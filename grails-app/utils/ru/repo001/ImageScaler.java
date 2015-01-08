/*
* ImageScaler
*/
package ru.repo001;



import javax.imageio.*;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Retrieve image metadata and scale it when necessary.
 */
public class ImageScaler {

    /**
     * Input image will be proportionally scaled to fit specified sizes. All blank space (if any) will be
     * transparent.
     *
     * @param source input stream with image to process
     * @param width  target width
     * @param height target height
     * @param os output stream to writer result to
     * @throws IOException if error occures
     */
    public static void scaleImage(InputStream source, int width, int height, OutputStream os) throws IOException {
        BufferedImage bim = ImageIO.read(source);
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = out.createGraphics();
        g2d.setComposite(AlphaComposite.Src);
        int sourceWidth = bim.getWidth();
        int sourceHeight = bim.getHeight();
        if (sourceWidth > width || sourceHeight > height) { //need to scale image
            double fx = 1.0 * width / sourceWidth;
            double fy = 1.0 * height / sourceHeight;
            double factor = Math.min(fx, fy);
            int atX = (int) ((width - sourceWidth * factor) / 2);
            int atY = (int) ((height - sourceHeight * factor) / 2);
            RenderingHints rHints = new RenderingHints(null);
            rHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            rHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            AffineTransform scaler = AffineTransform.getScaleInstance(factor, factor);
            g2d.drawImage(bim, new AffineTransformOp(scaler, rHints), atX, atY);
        } else {
            int atX = (width - sourceWidth) / 2;
            int atY = (height - sourceHeight) / 2;
            g2d.drawImage(bim, atX, atY, null);
        }
        g2d.dispose();
        ImageIO.write(out, "png", os);
    }


    /**
     * Returns image dimensions
     * @param source image input source
     * @return map with width and height
     * @throws IOException if error occures
     */
    public static Map<String, Object> getImageInfo(InputStream source) throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
        if (!readers.hasNext()) {
            throw new RuntimeException("Unknown image format.");
        }
        ImageReader reader = readers.next();
        reader.setInput(iis, true);

        int width = reader.getWidth(0);
        int height = reader.getHeight(0);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("width", width);
        result.put("height", height);
        return result;
    }

    /**
     * Scale/crop image into a JPG file.
     * If no transformation required, simply return WxH.
     *
     * @param source source image to be transformed
     * @param size   target square width/height
     * @param scale  need to scale/convert or just return metadata?
     * @return resulting image info: width, height, resulting file path (JPEG)
     * @throws java.io.IOException on image processing errors
     */
    public static Map<String, Object> makeSquareImageOrReturnDimensions(InputStream source, int size, boolean scale) throws IOException {

        ImageInputStream iis = ImageIO.createImageInputStream(source);
        ImageReader reader = ImageIO.getImageReaders(iis).next();
        reader.setInput(iis, true);

        int width = reader.getWidth(0);
        int height = reader.getHeight(0);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("width", width);
        result.put("height", height);

        // temporary image for every scaled instance
        BufferedImage scaledImg;

        // Does not need scaling. Return width/height.
        if (!scale) {
            return result;
        }

        if (width == height && width < size) {
            // no scaling, just bring this to square size.
            scaledImg = reader.read(0);
        } else {
            // scale/crop
            scaledImg = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics2D gScaledImg = scaledImg.createGraphics();

            // Note the use of BILNEAR filtering to enable smooth scaling
            RenderingHints rHints = new RenderingHints(null);
            rHints.put(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            rHints.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            BufferedImage img = reader.read(0);

            // Scale the original image into the temporary image
            int scaleSize = Math.max(width, height);
            double factor = (double) size / (double) scaleSize;
            AffineTransform scaler = AffineTransform.getScaleInstance(factor, factor);
            gScaledImg.setColor(Color.WHITE);
            gScaledImg.fillRect(0, 0, size, size);
            gScaledImg.drawImage(img, new AffineTransformOp(scaler, rHints),
                    (int) (size - factor * width) / 2,
                    (int) (size - factor * height) / 2);
        }

        // Save JPEG
        File scaledImgFile = File.createTempFile("crop", ".jpg");
        Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter jpegWriter = (ImageWriter) iter.next();
        try {
            ImageWriteParam iwp = jpegWriter.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwp.setCompressionQuality(0.75f);   // an integer between 0 and 1 (1 specifies minimum compression and maximum quality)
            FileImageOutputStream output = new FileImageOutputStream(scaledImgFile);
            jpegWriter.setOutput(output);
            IIOImage image = new IIOImage(scaledImg, null, null);
            jpegWriter.write(null, image, iwp);
        } finally {
            jpegWriter.dispose();
        }

        result.put("file", scaledImgFile);
        return result;
    }

    /**
     * Input image will be proportionally scaled to fit specified sizes. All blank space (if any) will be
     * white.
     *
     * @param source   input stream with image to process
     * @param width    target width
     * @param height   target height
     * @param fileName output fileName to write result to
     * @return map with image params
     * @throws IOException if error occures
     */
    public static Map scaleImage2Jpeg(InputStream source, int width, int height, String fileName) throws IOException {

        BufferedImage bim = ImageIO.read(source);
        if (width == -1) {
            width = bim.getWidth();
        }
        if (height == -1) {
            height = bim.getHeight();
        }
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = out.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        int sWidth = bim.getWidth();
        int sHeight = bim.getHeight();

        if (sWidth > width || sHeight > height) { //need to scale image
            double fx = 1.0 * width / sWidth;
            double fy = 1.0 * height / sHeight;
            double factor = Math.min(fx, fy);
            double newWidth = sWidth * factor;
            double newHeight = sHeight * factor;
            int atX = (int) ((width - newWidth) / 2);
            int atY = (int) ((height - newHeight) / 2);
            BufferedImage tmp = resize (createCompatibleImage(bim), (int) (newWidth * 3), (int) (newHeight * 3));
            tmp = blurImage(tmp);
            tmp = resize(tmp, (int)newWidth, (int)newHeight);
            g2d.drawImage(tmp, atX, atY, null);
        } else {
            int atX = (width - sWidth) / 2;
            int atY = (height - sHeight) / 2;
            g2d.drawImage(bim, atX, atY, null);
        }
        g2d.dispose();

        // Save JPEG
        File scaledImgFile = new File(fileName);

        Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter jpegWriter = (ImageWriter) iter.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(scaledImgFile);
        jpegWriter.setOutput(ios);
        try {
            ImageWriteParam iwp = jpegWriter.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwp.setCompressionQuality(1f);   // an integer between 0 and 1 (1 specifies minimum compression and maximum quality)
            IIOImage image = new IIOImage(out, null, null);
            jpegWriter.write(null, image, iwp);
        } finally {
            ios.flush();
            jpegWriter.dispose();
            ios.close();
        }
        Map result = new HashMap();
        result.put("width", width);
        result.put("height", height);
        result.put("size", scaledImgFile.length());
        return result;
    }

    /**
     * Creates compatible image from the specified source picture
     *
     * @param image image to copy
     * @return compatible image
     */
    private static BufferedImage createCompatibleImage(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = result.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2.drawRenderedImage(image, null);
        g2.dispose();
        return result;
    }

    /**
     * Blurs specified image
     *
     * @param image image to blur
     * @return blurred image
     */
    private static BufferedImage blurImage(BufferedImage image) {
        float ninth = 1.0f / 9.0f;
        float[] blurKernel = {
                ninth, ninth, ninth,
                ninth, ninth, ninth,
                ninth, ninth, ninth
        };
        Map map = new HashMap();
        map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RenderingHints hints = new RenderingHints(map);
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
        return op.filter(image, null);
    }

    /**
     * Resizes image
     *
     * @param image  image to resize
     * @param width  new width
     * @param height new height
     * @return resized image
     */
    private static BufferedImage resize(BufferedImage image, int width, int height) {
        int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }
}