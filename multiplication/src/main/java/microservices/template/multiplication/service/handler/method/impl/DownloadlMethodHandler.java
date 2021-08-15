package microservices.template.multiplication.service.handler.method.impl;

import microservices.template.multiplication.service.handler.method.ABaseMethodHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadlMethodHandler extends ABaseMethodHandler {
    public DownloadlMethodHandler(ApplicationContext context) {
        super(context);
    }

    @Override
    public Object handle(String objectId, Object[] arguments) throws Exception {
        HttpServletRequest request = (HttpServletRequest) arguments[0];
        // Load file as Resource
        Path path = Paths.get("C:/DEV/download/trans_payroll_detail_report.pdf");
        try {
            Resource resource = new UrlResource(path.toUri());
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return null;
        }
    }
}
