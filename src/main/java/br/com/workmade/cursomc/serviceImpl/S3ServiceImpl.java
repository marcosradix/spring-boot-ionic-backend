package br.com.workmade.cursomc.serviceImpl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import br.com.workmade.cursomc.service.S3Service;

@Service
public class S3ServiceImpl implements S3Service{

	private final static Logger LOG = LoggerFactory.getLogger(S3ServiceImpl.class);
	
	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	@Override
	public void uploadFile(String localFilePath) {
		
		try {
			LOG.info("iniciando envio");
			File file = new File(localFilePath);
			s3Client.putObject(new PutObjectRequest(bucketName, "Teste.jpg", file));
			LOG.info("envio terminado");
		} catch (AmazonServiceException e) {
			LOG.info("erro no envio "+e.getErrorMessage());
			LOG.info("status erro "+e.getErrorCode());
		}
		
		catch (AmazonClientException e) {
			LOG.info("AmazonClientException "+e.getMessage());
		}
		
		
	}
	
	

}
