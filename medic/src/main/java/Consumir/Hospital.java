package Consumir;
import dieguito.medic.Medic;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

public class Hospital {
    public static void main(String[] args) {
        Medic medic = new Medic("Azul de Amarelo Mendoza",1);
        Medic medic1 = new Medic("Amarelo de Vermelho Antonioli",1);
        Medic medic2 = new Medic("Azul de Verde Lima",0);
        Medic medic3 = new Medic("Vermelho de Azul Lima",2);
        /*
        addMedic(medic);
        addMedic(medic2);
        addMedic(medic2);
        addMedic(medic3);
        getMedic("Azul de Amarelo Mendoza");

        bonusSalario(10,"Azul de Amarelo Mendoza");

         */
        getMedic();
        deleteMedic("Azul de Amarelo Mendoza");

    }

    static public void getMedic(){

        String url = "http://localhost:8080/medic";


        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);


        if (responseEntity.getStatusCode().is2xxSuccessful()) {

            String responseBody = responseEntity.getBody();
            System.out.println("Resposta do serviço:");
            System.out.println(responseBody);
        } else {
            System.out.println("Falha na solicitação. Código de resposta: " + responseEntity.getStatusCodeValue());
        }

    }
    static public Medic getMedic(String nome){

        String url = "http://localhost:8080/medic/getMedic";

        RestTemplate restTemplate = new RestTemplate();

        Medic medic = restTemplate.getForObject(url,Medic.class,nome);

        if (medic != null) {
            return medic;
        } else {
            System.out.println("Médico não encontrado para o nome: " + nome);
            return null;
        }

    }
    static public void addMedic(Medic medic){

        String url = "http://localhost:8080/medic/addMedic";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<Medic> requestEntity = new HttpEntity<>(medic, headers);


        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);


        if (responseEntity.getStatusCode().is2xxSuccessful()) {

            String responseBody = responseEntity.getBody();
            System.out.println("Resposta do serviço:");
            System.out.println(responseBody);
        } else {
            System.out.println("Falha na solicitação. Código de resposta: " + responseEntity.getStatusCodeValue());
        }

    }
    static public void attMedic( Medic medicUpdate, String nome){

        String url = "http://localhost:8080/medic/attMedic";

        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Medic> requestEntity = new HttpEntity<>(medicUpdate, headers);


        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class, nome);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            System.out.println("Resposta do serviço:");
            System.out.println(responseBody);
        } else {
            System.out.println("Falha na solicitação. Código de resposta: " + responseEntity.getStatusCodeValue());
        }
    }
    static  public void deleteMedic(String nome){
        {
            String url = "http://localhost:8080/medic/deleteMedic";

            RestTemplate restTemplate = new RestTemplate();


            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class, nome);


            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();
                System.out.println("Resposta do serviço:");
                System.out.println(responseBody);
            } else {
                System.out.println("Falha na solicitação DELETE. Código de resposta: " + responseEntity.getStatusCodeValue());
            }
        }
    }
    static public void bonusSalario(int atendimentos, String nome) {

        String url = "http://localhost:8080/medic/bonusSalario";


        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        ResponseEntity<String> getResponseEntity = restTemplate.getForEntity(url, String.class, atendimentos,nome);



        if (getResponseEntity.getStatusCode().is2xxSuccessful()) {

            String responseBody = getResponseEntity.getBody();
            System.out.println( "Resposta do serviço:");
            System.out.println(responseBody);
        } else {
            System.out.println("Falha na solicitação PATCH. Código de resposta: " + getResponseEntity.getStatusCodeValue());
        }

    }
}
