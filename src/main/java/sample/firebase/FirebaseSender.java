package sample.firebase;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FirebaseSender extends FirebaseClient {



    public FirebaseSender(FirebaseConfig config) throws IOException {
        super(config);
    }

    public boolean envia(String device, String message) throws IOException {
        Message mensagem = new Message();
        mensagem.put("test", message);
        mapper.setSerializationInclusion(Include.NON_NULL);

        mensagem.setTo(device);
        String json = mapper.writeValueAsString(mensagem);
        Request request = criaRequisicaoParaPost(json);
        Response response = client.newCall(request).execute();
        if (!isValid(response)) {
            System.out.println("fail firebase " + response.body().string());
            response.close();
            return false;
        } else {
            System.out.println("resp firebase " + response.body().string());
            response.close();
            return true;
        }

    }

    @SuppressWarnings("unchecked")
    private boolean isValid(Response response) throws IOException {
        if (response.code() != 401) {
            String resp = response.body().string();
            List<LinkedHashMap<?, ?>> lista = (List<LinkedHashMap<?, ?>>) mapper.readValue(resp, Map.class)
                    .get("results");
            Map<?, ?> results = lista.get(0);
            if (results.containsKey(ERROR)) {
                String erro = (String) results.get(ERROR);
                if (erro.equals(NOT_REGISTERED) || erro.equals(INVALID_REGISTRATION)) {
                    return true;
                }
            }
        }
        return false;
    }

}
