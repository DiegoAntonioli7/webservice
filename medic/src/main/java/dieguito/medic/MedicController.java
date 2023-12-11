package dieguito.medic;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path="/medic")
public class MedicController {
    private ArrayList<Medic> medicos = new ArrayList<>();
    @GetMapping
    public String getMedics() {
        try {
            return medicos.toString();
        }catch (Exception e){
            return "Erro: " + e.getMessage() + " impediu de exibir os medicos";
        }
    }

    @GetMapping(path="/{nome}")
    public Medic getMedic(@PathVariable String nome){
        Medic medic = new Medic();
        medic.setNome(nome);
        return medicos.get(medicos.indexOf(medic));
    }

    @PostMapping(path="/{nome}")
    public String addMedic(@RequestBody Medic medic){
        try {
            if(medic.getIdx_especilizacao() == 0){
                medic.setSalario(10000.00);
            }
            if(medic.getIdx_especilizacao() == 1){
                medic.setSalario(6000.00);
            }
            if(medic.getIdx_especilizacao() == 2){
                medic.setSalario(3000.00);
            }
            medicos.add(medic);
            return "Adcionado com sucesso";
        }catch (Exception e){
            return "Erro: "+ e.getMessage() +" ao tentar adcionar médico";
        }
    }

    @PutMapping(path="/{nome}")
    public String attMedic(@RequestBody Medic medicUpdate,@PathVariable String nome){
        try{
            Medic medic = new Medic();
            medic.setNome(nome);
            medicos.set(medicos.indexOf(medic),medicUpdate);
            return "Sucesso na atualização";
        }catch (Exception e){
            return "Erro: "+e.getMessage()+" ao tentar atualizar médico";
        }
    }
    @DeleteMapping(path="/{nome}")
    public String deleteMedic(@PathVariable String nome){
        try {
            Medic medic = new Medic();
            medic.setNome(nome);
            medicos.remove(medicos.indexOf(medic));
            return "Sucesso na remoção";
        }catch (Exception e){
            return "Erro: "+e.getMessage()+" ao tentar deletar médico";
        }
    }


    @PatchMapping(path="/{nome}")
    public String bonusSalario(@PathVariable int atendimentos,@PathVariable String nome){
        try{
            Medic medicTemp = new Medic();
            medicTemp.setNome(nome);
            medicTemp = medicos.get(medicos.indexOf(medicTemp));
            medicTemp.setSalario(medicTemp.getSalario() + 100*atendimentos);
            attMedic(medicTemp,medicTemp.getNome());
            return "Salário modificado com sucesso";
        }catch (Exception e){
            return "Erro: "+e.getMessage()+ " ao tentar modificar o salário";
        }
    }


}
