package br.com.kairos.parking.storage;

import br.com.kairos.parking.config.property.ParkingApiProperty;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Component
public class StorageS3Config {
    
    private static final Logger logger = LoggerFactory.getLogger(StorageS3Config.class);
    
    @Autowired
    private AmazonS3 amazonS3;
    
    @Autowired
    private ParkingApiProperty property;
    
    public String salvarTemporariamente(final MultipartFile arquivo) {
        final AccessControlList acl = new AccessControlList();
        acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
        
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(arquivo.getContentType());
        objectMetadata.setContentLength(arquivo.getSize());
        
        final String nomeUnico = this.gerarNomeUnico(arquivo.getOriginalFilename());
        
        try {
            final PutObjectRequest putObjectRequest = new PutObjectRequest(
                    this.property.getS3().getBucket(),
                    nomeUnico,
                    arquivo.getInputStream(),
                    objectMetadata)
                            .withAccessControlList(acl);
            
            putObjectRequest.setTagging(new ObjectTagging(
                    Arrays.asList(new Tag("expirar", "true"))));
            
            this.amazonS3.putObject(putObjectRequest);
            
            if (logger.isDebugEnabled()) {
                logger.debug("Arquivo {} enviado com sucesso para o S3.",
                        arquivo.getOriginalFilename());
            }
            
            return nomeUnico;
        } catch (final IOException e) {
            throw new RuntimeException("Problemas ao tentar enviar o arquivo para o S3.", e);
        }
    }
    
    public String configurarUrl(final String objeto) {
        return "\\\\" + this.property.getS3().getBucket() +
                ".s3.amazonaws.com/" + objeto;
    }
    
    public void salvar(final String objeto) {
        final SetObjectTaggingRequest setObjectTaggingRequest = new SetObjectTaggingRequest(
                this.property.getS3().getBucket(),
                objeto,
                new ObjectTagging(Collections.emptyList()));
        
        this.amazonS3.setObjectTagging(setObjectTaggingRequest);
    }
    
    public void remover(final String objeto) {
        final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(
                this.property.getS3().getBucket(), objeto);
        
        this.amazonS3.deleteObject(deleteObjectRequest);
    }
    
    public void substituir(final String objetoAntigo, final String objetoNovo) {
        if (StringUtils.hasText(objetoAntigo)) {
            this.remover(objetoAntigo);
        }
        
        this.salvar(objetoNovo);
    }
    
    private String gerarNomeUnico(final String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }
    
}
