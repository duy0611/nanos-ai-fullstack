package ai.nanos.fullstack.web.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ImageResouce {

	private static final transient Logger LOG = LoggerFactory.getLogger(ImageResouce.class);
	
	private static String IMAGE_PATH = "/static/images/";

	@RequestMapping(value = "/images/{imageFileName}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getLocalImage(@PathVariable(name = "imageFileName") String imageFileName) throws IOException {
		LOG.debug("GET image file name = {}", imageFileName);
		
		ClassPathResource imgFile = new ClassPathResource(IMAGE_PATH + imageFileName);
		if (imgFile.exists()) {
			byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cache-Control", "public, max-age=3600");
			
			return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.IMAGE_JPEG)
	                .body(bytes);
		}
		return ResponseEntity.notFound().build();
	}

}
