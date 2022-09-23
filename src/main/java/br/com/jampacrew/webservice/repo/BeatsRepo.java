package br.com.jampacrew.webservice.repo;
import br.com.jampacrew.webservice.model.Beat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("application")
public class BeatsRepo {
    private Map<Integer, Beat> repositorio = new HashMap<Integer, Beat>();
    
    public Beat findById(Integer id) {
        return repositorio.get(id);
    }

    public void insert(Beat Beat) {
        Integer id = getMaxId();
        Beat.setId(id);
        repositorio.put(id, Beat);
    }
    
    public void update(Beat Beat) {
        repositorio.put(Beat.getId(), Beat);
    }

    public List<Beat> findAll() {
        List<Beat> Beats = repositorio 
            .values().stream().collect(Collectors.toList());
        return Beats;
    }

    private Integer getMaxId() {
        List<Beat> Beats = findAll();
        if (Beats == null || Beats.isEmpty())
            return 1;
        else
            return Beats.size() + 1;
    }

}
