package com.example.livraria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileStorageServer {

    private final Path root = Paths.get("Livraria2/src/main/images");

    public FileStorageServer() {
    }

    public void init(){
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("failed creating new directory for image ");

        }
    }

    public void salvar(MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
    }

    public Resource load(String filename){
        Path file = root.resolve(filename);

        try {
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()){
                return resource;
            }else{
                throw new RuntimeException("Arquivo ilegível");
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Nao carregou arquivos no LoadALL");
        }
    }



}
