package br.com.workmade.cursomc.service;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	BufferedImage getJpgImageFromFile(MultipartFile multipartFile);

	InputStream getInputStream(BufferedImage image, String extension);
	
}
