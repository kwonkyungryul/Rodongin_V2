package shop.mtcoding.rodongin.util;

import org.apache.tika.Tika;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.handler.ex.CustomException;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class PathUtil {

    // image 폴더는 외부에 잡고, 그 경로를 잡아주는 것이 좋음
    public static String getStaticFolder() {
        // build된 realpath로 변경 (인텔리J)
        // return System.getProperty("user.dir") + "\\build\\resources\\main\\static\\";
        // vscode 용
        return System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
    }

    public static String writeImageFile(String profile) {

        String extension;
        try {
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(profile);
            Tika tika = new Tika();
            String mimeType = tika.detect(new ByteArrayInputStream(imageBytes));
            extension = mimeType.split("/")[1];

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomApiException("확장자 변환 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        UUID uuid = UUID.randomUUID();
        String uuidImageRealName = "images\\" + uuid + "." + extension;

        String staticFolder = getStaticFolder();
        Path imageFilePath = Paths.get(staticFolder + "\\" + uuidImageRealName);
        try {
            Files.write(imageFilePath, Base64.getDecoder().decode(profile));
        } catch (Exception e) {
            throw new CustomException("사진을 웹서버에 저장하지 못하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "/images/" + uuid + "." + extension;
    }
}
