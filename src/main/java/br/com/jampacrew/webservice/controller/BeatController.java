package br.com.jampacrew.webservice.controller;
import br.com.jampacrew.webservice.model.Beat;
import br.com.jampacrew.webservice.repo.BeatsRepo;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/beats")
public class BeatController {
    @Autowired
    private BeatsRepo beatsRepository;

    @RequestMapping("/drop")
    public ModelAndView getFormBeat(ModelAndView model){
        model.setViewName("beats/cadastro-beat");
        model.addObject("menu", "cadastrar");
        model.addObject("beat", new Beat());
        return model;
    }

    @ModelAttribute("categorias")
    public Map<String, String> getCategorias() {
        Map<String, String> categorias = new LinkedHashMap<String, String>();
        categorias.put("1", "Trap");
        categorias.put("2", "Boombap");
        categorias.put("3", "Drill");
        categorias.put("4", "Plug");
        return categorias;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String cadastreBeat(Beat beat, Model model, RedirectAttributes attr){
        if (beat.getId() == null){
            beatsRepository.insert(beat);
        } else {
            beatsRepository.update(beat);
        }
        attr.addFlashAttribute("mensagem", "Beat cadastrado com sucesso!");
        return "redirect:/beats";
    }

    @RequestMapping("/{id}")
    public ModelAndView busqueBeat(@PathVariable("id") Integer id, ModelAndView model) {
        model.setViewName("beats/list-beat");
        Beat beat = beatsRepository.findById(id);
        model.addObject("beat", Collections.singleton(beat));
        return model;
    }

    @RequestMapping(value = "/edite/{id}")
    public ModelAndView editeBeat(@PathVariable("id") Integer id, ModelAndView model) {
        model.setViewName("beats/cadastro-beat");
        Beat beat = beatsRepository.findById(id);
        model.addObject("beat", beat);
        return model;
    }

    @RequestMapping(method = { RequestMethod.GET })
    public ModelAndView listeBeatsCadastrados(ModelAndView model) {
        model.setViewName("beats/list-beat");
        model.addObject("menu", "listar");
        model.addObject("beats", beatsRepository.findAll());
        return model;
    }
}