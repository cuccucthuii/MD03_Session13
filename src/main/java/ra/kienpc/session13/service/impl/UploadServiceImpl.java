package ra.kienpc.session13.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.kienpc.session13.service.IUploadService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Service
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        File tempFile = null;
        try {
            tempFile = Files.createTempFile("upload_", "_" + file.getOriginalFilename()).toFile();
            file.transferTo(tempFile);

            @SuppressWarnings("unchecked")
            Map<String, Object> params = ObjectUtils.asMap(
                    "folder", folder,
                    "resource_type", "image"
            );
            Map<?, ?> uploadResult = cloudinary.uploader().upload(tempFile, params);
            Object url = uploadResult.get("secure_url");
            return url != null ? url.toString() : null;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi upload file: " + e.getMessage());
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
}
