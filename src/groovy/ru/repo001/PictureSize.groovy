package ru.repo001
/**
 * Created by Ivan on 08.01.2015.
 */
public enum PictureSize {

    SMALL(90, 60, 's'), MEDIUM(270, 180, 'm')

    int height
    int width
    String postfix

    PictureSize(int height, int width, String postfix) {
        this.height = height
        this.width = width
        this.postfix = postfix
    }

    int getId() {
        return id
    }
}
