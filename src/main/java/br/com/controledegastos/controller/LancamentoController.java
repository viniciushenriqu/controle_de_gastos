package br.com.controledegastos.controller;

import br.com.controledegastos.model.Lancamento;
import br.com.controledegastos.model.TipoLancamento;
import br.com.controledegastos.repository.LancamentoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LancamentoController {

    private final LancamentoRepository repository;

    public LancamentoController(LancamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
public String listar(Model model) {
    var todosLancamentos = repository.findAll();
    System.out.println("Chamou listar()! Quantidade de lançamentos: " + todosLancamentos.size());
    
    model.addAttribute("lancamentos", todosLancamentos);
    return "index"; // templates/index.html
}


    // Formulário de cadastro
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("lancamento", new Lancamento());
        model.addAttribute("tipos", TipoLancamento.values());
        return "form"; // templates/form.html
    }

    // Salvar um novo lançamento
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Lancamento lancamento) {
        repository.save(lancamento);
        return "redirect:/";
    }
}
