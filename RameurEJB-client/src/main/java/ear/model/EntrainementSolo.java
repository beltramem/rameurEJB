package ear.model;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface EntrainementSolo {

    public void lancerEntrainementSolo() throws IOException, TimeoutException, InterruptedException;
}
