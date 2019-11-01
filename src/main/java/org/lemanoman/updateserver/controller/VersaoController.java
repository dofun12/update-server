package org.lemanoman.updateserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lemanoman.updateserver.exception.BookIdMismatchException;
import org.lemanoman.updateserver.exception.BookNotFoundException;
import org.lemanoman.updateserver.model.Book;
import org.lemanoman.updateserver.model.Versao;
import org.lemanoman.updateserver.repository.BookRepository;
import org.lemanoman.updateserver.repository.VersaoRepository;
import org.lemanoman.updateserver.struct.DefaultController;
import org.lemanoman.updateserver.struct.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/versao")
public class VersaoController extends DefaultController {

    @Autowired
    private VersaoRepository versaoRepository;

    @GetMapping
    public String findAll() {
        Resposta resposta = new Resposta();
        try {
            resposta.setResposta(versaoRepository.findAll());
            resposta.setSucesso(true);
        }catch (Exception ex){
            resposta.setMensagem("Erro: "+ex.getMessage());
        }
        return  sendResponse(resposta);
    }

    @GetMapping("/versao/{versao}")
    public String findByTitle(@PathVariable String versao) {
        Resposta resposta = new Resposta();
        try {
            resposta.setResposta( versaoRepository.findByNome(versao));
            resposta.setSucesso(true);
        }catch (Exception ex){
            resposta.setMensagem("Erro: "+ex.getMessage());
        }
        return  sendResponse(resposta);
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable Long id) {
        Resposta resposta = new Resposta();
        try {
            resposta.setResposta( versaoRepository.findById(id).orElseThrow(BookNotFoundException::new));
            resposta.setSucesso(true);
        }catch (Exception ex){
            resposta.setMensagem("Erro: "+ex.getMessage());
        }
        return  sendResponse(resposta);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Versao versao) {
        Resposta resposta = new Resposta();
        try {
            Versao saved = versaoRepository.save(versao);
            resposta.setResposta(saved);
            resposta.setMensagem("Salvo com sucesso");
            resposta.setSucesso(true);
        } catch (Exception ex) {
            resposta.setMensagem("Erro ao salvar");
            ex.printStackTrace();
        }
        return sendResponse(resposta);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Resposta resposta = new Resposta();
        try {
            versaoRepository.findById(id)
                    .orElseThrow(BookNotFoundException::new);
            versaoRepository.deleteById(id);
            resposta.setMensagem("Salvo com sucesso");
            resposta.setSucesso(true);
        } catch (Exception ex) {
            resposta.setMensagem("Erro ao salvar: " + ex.getMessage());
            ex.printStackTrace();
        }
        return sendResponse(resposta);

    }

    @PutMapping("/{id}")
    public String updateBook(@RequestBody Versao edicao, @PathVariable Long id) {
        Resposta resposta = new Resposta();
        try {
            if (edicao.getId() != id) {
                throw new BookIdMismatchException();
            }
            Versao versao = versaoRepository.findById(id)
                    .orElseThrow(BookNotFoundException::new);

            versao.setDataRelease(edicao.getDataRelease());
            versao.setDiretorio(edicao.getDiretorio());
            versao.setStatus(edicao.getStatus());
            versao.setNome(edicao.getNome());
            Versao saved = versaoRepository.save(versao);

            resposta.setResposta(saved);
            resposta.setSucesso(true);
            resposta.setMensagem("Salvo com sucesso");
        } catch (Exception ex) {
            resposta.setMensagem("Erro ao salvar: " + ex.getMessage());
            ex.printStackTrace();
        }
        return sendResponse(resposta);
    }
}
