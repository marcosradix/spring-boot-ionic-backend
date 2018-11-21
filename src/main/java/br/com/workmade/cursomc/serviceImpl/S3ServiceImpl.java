package br.com.workmade.cursomc.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.workmade.cursomc.service.S3Service;
import br.com.workmade.cursomc.service.exceptions.FileException;

@Service
public class S3ServiceImpl implements S3Service {

	private final static Logger LOG = LoggerFactory.getLogger(S3ServiceImpl.class);

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	@Override
	public URI uploadFile(MultipartFile multipartFile) {

		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream inputStream = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(inputStream, fileName, contentType);
		} catch (IOException e) {
			LOG.info(e.getMessage());
			throw new FileException("Erro O/I " +e.getMessage() );
		}

	}

	@Override
	public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("iniciando envio");
			s3Client.putObject(bucketName, fileName, inputStream, meta);
			LOG.info("envio terminado");
			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	}

}
