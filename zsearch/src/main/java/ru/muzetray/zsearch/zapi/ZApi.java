package ru.muzetray.zsearch.zapi;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "zapi", configuration = FeignZapiConfiguration.class, url = "${zapi.url}")
public interface ZApi {
    @RequestMapping(method = RequestMethod.GET, value = "/options")
    JsonNode options();

    @RequestMapping(method = RequestMethod.GET, value = "/autocomplete")
    JsonNode autocomplete(@RequestParam("query") String query);

    @RequestMapping(method = RequestMethod.GET, value = "/artist/{id}")
    JsonNode artist(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/track/{id}")
    JsonNode track(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/track/{id}/play")
    TrackPlayResponse trackPlay(@PathVariable("id") Long id);

    @Data
    @NoArgsConstructor
    class TrackPlayResponse {
        public Boolean downloadEnabled;
        public Boolean playEnabled;
        public String rbtUrl;
        public String url;
    }
}
