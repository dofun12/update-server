package org.lemanoman.updateserver.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.lemanoman.updateserver.exception.BookIdMismatchException;
import org.lemanoman.updateserver.exception.BookNotFoundException;
import org.lemanoman.updateserver.model.Book;
import org.lemanoman.updateserver.repository.BookRepository;
import org.lemanoman.updateserver.struct.Arquivo;
import org.lemanoman.updateserver.struct.DefaultController;
import org.lemanoman.updateserver.struct.Diretorio;
import org.lemanoman.updateserver.struct.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController extends DefaultController {

    @GetMapping(value = {"/",""})
    public String listDir(
    ) {
        return listDir(toBase64(System.getProperty("user.home")));

    }


    @GetMapping(value = "/directory/{caminho}")
    public String listDir(
            @PathVariable("caminho") String caminho
    ) {
        Resposta resposta = new Resposta();
        try {
            File diretorio = new File(fromBase64(caminho));
            if (diretorio.exists() && diretorio.isDirectory()) {
                Diretorio dir = new Diretorio();
                dir.setNome(diretorio.getName());
                dir.setPath(toBase64(diretorio.getAbsolutePath()));
                if(diretorio.getParentFile()!=null){
                    dir.setParentPath(toBase64(diretorio.getParentFile().getAbsolutePath()));
                }else{
                    dir.setParentPath(toBase64(diretorio.getAbsolutePath()));
                }

                List<Arquivo> arquivos = new ArrayList<>();
                if(diretorio.canRead() && diretorio.listFiles()!=null){
                    for (File file : diretorio.listFiles()) {
                        Arquivo arquivo = new Arquivo();
                        arquivo.setDir(file.isDirectory());
                        arquivo.setNome(file.getName());
                        arquivo.setPath(toBase64(file.getAbsolutePath()));
                        arquivo.setTamanho(file.length());
                        arquivos.add(arquivo);
                    }
                }
                dir.setArquivos(arquivos);
                resposta.setResposta(dir);
                resposta.setMensagem("Sucesso!");
                resposta.setSucesso(true);
                return sendResponse(resposta);
            } else {
                throw new Exception("Invalid Dir");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            resposta.setMensagem("Diretorio invalido");
            resposta.setSucesso(true);
            return sendResponse(resposta);
        }

    }

    private String fromBase64(String text){
        try {
            return new String(Base64.getDecoder().decode(text.getBytes()),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String toBase64(String text){
       return Base64.getEncoder().encodeToString(text.getBytes());
    }
}
