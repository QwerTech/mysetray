package ru.muzetray.zsearch.services;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.muzetray.zsearch.zapi.ZApi;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchByArtistService {

    private static final String apkSecret = d("llf7116f22c");
    private ZApi api;

    private RestTemplate requestTemplate;

    public static String d(String paramString) {
        int j = 0;
        StringBuilder localStringBuilder = new StringBuilder();
        byte[] bytes = paramString.toLowerCase().getBytes();
        int k = bytes.length;
        int i = 0;
        while (i < k) {
            localStringBuilder.append(a("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(bytes[i]), 5));
            i += 1;
        }
        localStringBuilder.setLength(localStringBuilder.length() - localStringBuilder.length() % 6);
        k = localStringBuilder.length();
        StringBuilder builder = new StringBuilder();
        i = j;
        while (i < k) {
            builder.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(Integer.parseInt(localStringBuilder.substring(i, i + 6), 2)));
            i += 6;
        }
        return builder.toString();
    }

    private static String a(int paramInt1, int paramInt2) {
        String str = "000000" + Integer.toBinaryString(paramInt1);
        return str.substring(str.length() - paramInt2);
    }

    @SneakyThrows
    public void downloadFile(String url) {
        URL website = new URL(url);
        try (InputStream in = website.openStream()) {
            File file = File.createTempFile("asd", ".mp3");
            Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public List<String> search() {
        getToken();
        return Collections.emptyList();
    }

    public String getToken() {
        String token = requestTemplate.getForObject("https://api.zaycev.net/external/hello", HelloResponse.class).token;
        String hash = b(token + apkSecret);
        JsonNode node = requestTemplate.getForObject("https://api.zaycev.net/external/auth?code={code}&hash={hash}", JsonNode.class, new HashMap<String, String>() {{
            put("code", token);
            put("hash", hash);
        }});
        if (node.has("error"))
            throw new RuntimeException(node.toString());
        return node.get("token").asText();
    }

    public JsonNode getOptions() {
        return api.options();
    }

    @SneakyThrows
    private final String b(String paramString) {
        Object localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest) localObject).update(paramString.getBytes());
        byte[] digest = ((MessageDigest) localObject).digest();
        StringBuilder localStringBuffer = new StringBuilder();
        int i = 0;
        while (i < digest.length) {
            for (paramString = Integer.toHexString(digest[i] & 0xFF); paramString.length() < 2; paramString = "0" + paramString) {
            }
            localStringBuffer.append(paramString);
            i += 1;
        }
        paramString = localStringBuffer.toString();
        return paramString;
    }

    @Data
    private static class HelloResponse {
        private String token;
    }
}
