package product;

import java.io.File;

@FunctionalInterface
public interface Uploadable {
    boolean upload(File file);
}
