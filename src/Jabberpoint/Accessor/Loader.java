package Jabberpoint.Accessor;

import Jabberpoint.Presentation.Presentation;

import java.io.IOException;

public interface Loader {
    void loadFile(Presentation p, String fn) throws IOException;
}
