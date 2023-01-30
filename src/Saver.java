import java.io.IOException;

public interface Saver {
    void saveFile(Presentation p, String fn) throws IOException;
}
