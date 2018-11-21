package br.com.workmade.cursomc.serviceImpl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.workmade.cursomc.service.ImageService;
import br.com.workmade.cursomc.service.exceptions.FileException;


@Service
public class ImageServiceImpl implements ImageService {

	@Override
	public BufferedImage getJpgImageFromFile(MultipartFile multipartFile) {
		String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		if( !"png".equals(ext) && !"jpg".equals(ext) ) {
			throw new FileException("Somente imagens PNG e JPG são permitidas!");
		}
		
		try {
			BufferedImage img = ImageIO.read(multipartFile.getInputStream());
			if("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro o arquivo de imagem.");
		}
	
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImg = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		jpgImg.createGraphics().drawImage(img, 0, 0,Color.WHITE , null);
		return jpgImg;
	}

	@Override
	public InputStream getInputStream(BufferedImage image, String extension) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, extension, os);
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}
		return new ByteArrayInputStream(os.toByteArray());
	}

	@Override
	public BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		BufferedImage crop = Scalr.crop(
				sourceImg,
				(sourceImg.getWidth() / 2) - (min /2),
				(sourceImg.getHeight() / 2) - (min / 2),
				 min, min);
		return crop;
	}
	
	@Override
	public BufferedImage resize(BufferedImage sourceImg, int size) {
		return  Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
		
	}
}






