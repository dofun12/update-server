package org.lemanoman.updateserver.struct;

import com.fasterxml.jackson.databind.ObjectMapper;

public  abstract class DefaultController {
    final ObjectMapper mapper = new ObjectMapper();
    public String sendResponse(Resposta resposta){
        try {
            return mapper.writeValueAsString(resposta);
        }catch (Exception ex){
            ex.printStackTrace();
            return "{'sucesso':false}";
        }
    }
}
