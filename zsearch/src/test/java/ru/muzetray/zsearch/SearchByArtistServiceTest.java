package ru.muzetray.zsearch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.muzetray.zsearch.services.SearchByArtistService;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class SearchByArtistServiceTest {

    @Autowired
    private SearchByArtistService service;

    @Test
    public void getClientSecret2() throws NoSuchAlgorithmException {
        String clientSecret = service.d("KVSQpX9JWByf3Z4vh9SsydXgDP2JbL-3CPZrSLptaSd1YjojOZlk0Wx1s-NVTUAK9-A8EJdtgoqDWB3ddUWRm5cGoVp9-Ft_S2GR-8snKD4");
        assertThat(clientSecret).isEqualTo("wnfQgLZoLErwL6g_axTTTUkCcobXGLMRZS75Zozr3oC05kWNfd07Bngjpg2VRY2GgXYPaCPqSGarqki6YU278ZO6XJP4RLdNqZMqHFwv-25iH8M_R6rSna2CmnP5OuwgTuUundxiTWqI2Am5rHA2gbU8kbB9Ya0gRJ1mHhq_MpksW3R49Fm4VBDd6vYnNUWykibWmxzxvhRBhJ2dmiKJkw");
    }
}