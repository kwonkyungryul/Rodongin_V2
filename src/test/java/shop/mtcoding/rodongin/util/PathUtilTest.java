package shop.mtcoding.rodongin.util;

import org.apache.tika.Tika;
import org.junit.jupiter.api.Test;

import javax.activation.MimetypesFileTypeMap;
import javax.xml.bind.DatatypeConverter;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class PathUtilTest {

    @Test
    public void pathUtil_test() throws Exception{
        String encoded = "iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAYAAACOEfKtAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA+JJREFUeNrsm01uE0EQhSc+gW/A3IBhg9gFEAdgwT7jPVLwCSKfII7E3u09UsweoWGHWA03GG6QI/DaqkjBcez+qappo3rSCCHFM68/V4/771WVyWQymUymcXTGebP2m3v98P/uXduV0MhXH9b/+Pr55aIrAiCA1fjnEpc32DzxZz0ub/gGQAclYN7LBa73uOoDvr767xlAB1WABG5F4GLkcC2kQBK460Rfc4C8EwcIeJ/IZKq8yRkgbpjhsfgCxI0YQMDzVdcytdlDdEzwWH0BYrCvSQS8a0aTXivcsy0M3tYX7tmyViAa6l/Gt0Lv/BeoxD4RnqgvVGKfXYGAN6UfDCndJsKT9rXi6sL+5TwVNFondmVpX01IVw4BeKkwdLtU+gz7MyYB776pgtEGz2oi330qvvCsOqcCm0pPMYPf81J8TQoy+iymMhR9ZVWgppqqTJ2fCkCp7i6qUwXYG8DHGiIn/kV8WZOCvunfhVbgnxyAPxSNdhF/W4yvgwBpzU6juwyRCwqdlq9jCwoh78AbBaNRz6CV442CrwXHj8hS+Nv21bdMbNydcPW5bIBonDc5FzQ6S/kQbQQtxvYVNIyhpXcn0UVytj4BcSnkax669TnmnogDvBnHjZiX9f02Z7CvqIE0NZij28y54FElzuhdnaP7XbkoX6n7wn4uepUwJ+0InshAmE4gXCcsTHQEb4h9Zu7JBG/4/gTA9MAUzRtcax31IJChvm5CNo9EAO7ArKvHa2eD1nGOAzCbPRCHnOMcJpPJZDKZTCaTyTSu2KZyL99+nu5O4n99/9iN3UDn3CNfbcs3Jz/LhBYaJ9hO2gF0UIIW5QtAB1WAAOdNJcccpEASuNSYwyIF5FkCPI44wRwQHTM8lpgDIMrFHACPNU7ABRHwWH0BYrCvSQQ89pgD7tkWBm/rC/dsWSsQDZWME7xJ/bVGQ0VjDqjE/JgDDU9GjxM8MTyxmIMfZiR2ZfGYQ0hXLiXmcJXwmfJjDvTu04gT1DQoj3n3qcQc8Kw6pwIt5nBCMYfnMZWh6CurAqtSjI4oizlIymIO/xHAmJUQiznskcUcMhUzH9b0tUkGiEm+WswBzyox5tAfW2QtJeYQdeoVjdKKORxtexExh8SF1bm0r5CF1aMA0bgiYw5UhacRc6AKcRJdN2frExDFYg6hW59j7ok4wGM5qc+8rL8EvOAeFzWQpgbndpvt7hcXPKpENl8x8KIr8EElpu6/dgRvEOh2vhK9n9T4xUxlX3gHpDcaHCeIHOvlggzx5YdC65DNIxGAOzDrak/MQes4xwGY+3z19CtuMplMJpPpZPVXgAEAfeiegS0N8+0AAAAASUVORK5CYII=";
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(encoded);
        Tika tika = new Tika();
        String mimeType = tika.detect(new ByteArrayInputStream(imageBytes));
        String extension = mimeType.split("/")[1];
        System.out.println(extension);
    }
}
