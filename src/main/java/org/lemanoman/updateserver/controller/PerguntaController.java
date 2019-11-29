package org.lemanoman.updateserver.controller;

import org.lemanoman.updateserver.exception.BookIdMismatchException;
import org.lemanoman.updateserver.exception.BookNotFoundException;
import org.lemanoman.updateserver.model.Alternativa;
import org.lemanoman.updateserver.model.Book;
import org.lemanoman.updateserver.model.Pergunta;
import org.lemanoman.updateserver.repository.AlternativaRepository;
import org.lemanoman.updateserver.repository.BookRepository;
import org.lemanoman.updateserver.repository.PerguntaRepository;
import org.lemanoman.updateserver.struct.DefaultController;
import org.lemanoman.updateserver.struct.PerguntaJS;
import org.lemanoman.updateserver.struct.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pergunta")
public class PerguntaController extends DefaultController {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private AlternativaRepository alternativaRepository;

    @GetMapping
    public Iterable findAll() {
        return perguntaRepository.findAll();
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable Long id) {
        Resposta resposta = new Resposta();
        PerguntaJS perguntaJS = new PerguntaJS();
        Optional<Pergunta> optPergunta = perguntaRepository.findById(id);

        if (optPergunta.isPresent()) {

            Pergunta pergunta = optPergunta.get();
            List<Alternativa> list = alternativaRepository.findByIdPergunta(pergunta.getId());
            perguntaJS.setIdPergunta(pergunta.getId());
            perguntaJS.setAlternativas(list);
            perguntaJS.setResposta(pergunta.getResposta());
            perguntaJS.setTexto(pergunta.getTexto());
            resposta.setSucesso(true);
            resposta.setResposta(perguntaJS);
            resposta.setMensagem("Sucesso!");
            return sendResponse(resposta);
        }
        resposta.setMensagem("Pergunta nao encontrada");
        return sendResponse(resposta);

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Resposta resposta = new Resposta();
        Optional<Pergunta> optPergunta = perguntaRepository.findById(id);

        if (optPergunta.isPresent()) {

            Pergunta pergunta = optPergunta.get();
            List<Alternativa> list = alternativaRepository.findByIdPergunta(pergunta.getId());
            alternativaRepository.deleteAll(list);
            perguntaRepository.delete(pergunta);
            resposta.setSucesso(true);
            resposta.setResposta("Deletado");
            resposta.setMensagem("Deletado com sucesso");
            return sendResponse(resposta);
        }
        resposta.setMensagem("Pergunta nao encontrada");
        return sendResponse(resposta);

    }


    @PutMapping("/")
    public String delete(@RequestBody PerguntaJS perguntaJS) {
        Resposta resposta = new Resposta();
        if(perguntaJS.getIdPergunta()!=null){
            Optional<Pergunta> optPergunta = perguntaRepository.findById(perguntaJS.getIdPergunta());
            try {
                if (optPergunta.isPresent()) {

                    Pergunta pergunta = optPergunta.get();
                    pergunta.setResposta(perguntaJS.getResposta());
                    pergunta.setTexto(perguntaJS.getTexto());
                    pergunta.setIdCategoria(perguntaJS.getIdCategoria());
                    List<Alternativa> list = perguntaJS.getAlternativas();
                    for (Alternativa alt : list) {
                        if (alt.getId() != null) {
                            Optional<Alternativa> opt = alternativaRepository.findById(alt.getId());
                            Alternativa alternativa;
                            if (opt.isPresent()) {
                                alternativa = opt.get();
                            }else{
                                alternativa = new Alternativa();
                            }
                            alternativa.setCode(alt.getCode());
                            alternativa.setTexto(alt.getTexto());
                            alternativa.setIdPergunta(pergunta.getId());
                            alternativaRepository.save(alternativa);
                        }

                    }
                    perguntaRepository.save(pergunta);
                    resposta.setSucesso(true);
                    resposta.setResposta("Alterado com sucesso");
                    resposta.setMensagem("Alterado com sucesso");
                    return sendResponse(resposta);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        resposta.setMensagem("Pergunta nao encontrada");
        return sendResponse(resposta);
    }

    @PostMapping
    public String create(@RequestBody PerguntaJS perguntaJS) {
        Resposta resposta = new Resposta();
        try {
            Pergunta pergunta = new Pergunta();
            pergunta.setTexto(perguntaJS.getTexto());
            pergunta.setResposta(perguntaJS.getResposta());
            perguntaRepository.save(pergunta);
            for (Alternativa alternativa : perguntaJS.getAlternativas()) {
                alternativa.setIdPergunta(pergunta.getId());
                alternativaRepository.save(alternativa);
            }
            resposta.setMensagem("Sucesso!");
            resposta.setResposta(pergunta);
            resposta.setSucesso(true);
            return sendResponse(resposta);
        } catch (Exception ex) {
            ex.printStackTrace();
            resposta.setSucesso(false);
            resposta.setMensagem(ex.getMessage());
            return sendResponse(resposta);
        }
    }

}
