package io.xpipe.app.fxcomps.impl;

import javafx.scene.image.Image;

import java.util.Optional;

public interface SvgCache {

    Optional<Image> getCached(String image);
}
