package ra.kienpc.session13.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    String uploadFile(MultipartFile file, String folder);
}
