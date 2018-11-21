package br.com.workmade.cursomc.service;

import java.io.InputStream;
import java.net.URI;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
	
	public URI uploadFile(MultipartFile multipartFile);

	public URI uploadFile(InputStream inputStream, String fileName, String contentType);

}
