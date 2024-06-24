package dev.gwozdz.contactfinder.service;


import dev.gwozdz.contactfinder.model.openai.ChatRequest;
import dev.gwozdz.contactfinder.model.openai.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OpenAIClient {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;
    @Value("${openai.api.url}")
    private String apiUrl;

    public String filterProperUnitsFromSearchResults(String searchResults, String unitType) {
        ChatRequest request = new ChatRequest(model);
        List<Message> messages = request.getMessages();
            messages.add(new Message("system", "Nie dodawaj żadnego tekstu, ani wytłumaczeń. Usuń "));
            messages.add(new Message("user", "Poniżej są pary nazwa i adres url. Odfiltruj jedynie te typu " +
                    unitType +":\n" + searchResults));
//        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

//        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
//            return null;
//        }

        // return the first response
//        return response.getChoices().get(0).getMessage().getContent();
        return """
            Kontakt - Serwis Ośrodka Kultury Kraków-Nowa Huta | https://krakownh.pl/kontakt
            Kontakt - Nowohuckie Centrum Kultury Kraków | https://nck.krakow.pl/kontakt/
            Kontakt - Centrum Kultury Podgórza | https://www.ckpodgorza.pl/kontakt
            Kontakt - Ośrodek Ruczaj | https://ruczaj.ckpodgorza.pl/kontakt
            Kontakt - Ośrodek Kultury Norwida | https://okn.edu.pl/strona-2556-kontakt.html
            Kontakt – Ośrodek Kultury Krakowiacy | https://krakowiacy.art.pl/kontakt/
        """;
    }


//    private final String URL = "https://api.openai.com/v1/completions";
//
//    public  void chatGPT(String text) throws Exception {
//
//        HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();
//
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setRequestProperty("Authorization", "Bearer YOUR-API-KEY");
//
//        JSONObject data = new JSONObject();
//        data.put("model", "text-davinci-003");
//        data.put("prompt", text);
//        data.put("max_tokens", 4000);
//        data.put("temperature", 1.0);
//
//        con.setDoOutput(true);
//        con.getOutputStream().write(data.toString().getBytes());
//
//        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
//                .reduce((a, b) -> a + b).get();
//
//        System.out.println(new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
//  }
}
