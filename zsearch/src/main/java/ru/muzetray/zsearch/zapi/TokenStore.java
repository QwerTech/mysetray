package ru.muzetray.zsearch.zapi;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.muzetray.zsearch.services.SearchByArtistService;

@Service
public class TokenStore {
    private String token;

    private SearchByArtistService service;

    public TokenStore(SearchByArtistService service) {
        this.service = service;
    }

    public String getToken() {
        if (token != null)
            return token;
        token = service.getToken();
        return token;
    }
}
