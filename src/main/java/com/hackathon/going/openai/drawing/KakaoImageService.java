package com.hackathon.going.openai.drawing;

import com.hackathon.going.domain.pin.dto.PinDto;
import com.hackathon.going.domain.pinImage.constant.PinImgStatus;
import com.hackathon.going.domain.pinImage.entity.PinImage;
import com.hackathon.going.domain.pinImage.repository.PinImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoImageService {

    private final PinImageRepository pinImageRepository;
    private final String KAKAO_KARLO_REQUEST_URI = "https://api.kakaobrain.com/v1/inference/karlo/t2i";
    private final RestTemplate restTemplate;
    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    private static String extractFirstImageBase64(String response) {
        // JSON 파싱 및 첫 번째 이미지의 Base64 데이터 추출
        // 파싱 라이브러리를 이용하여 JSON 을 파싱하는 것이 좋음
        String base64Start = "\"image\":\"";
        int startIdx = response.indexOf(base64Start) + base64Start.length();
        int endIdx = response.indexOf("\"", startIdx);
        return response.substring(startIdx, endIdx);
    }

    public void create(PinDto pin, String en) throws Exception {
        String response = requestCreateImage(en);
        String firstImageBase64 = extractFirstImageBase64(response);
        base64ToImage(pin, firstImageBase64);
    }

    public String requestCreateImage(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = " {\"prompt\": {\"text\": \"" + text + "\", \"batch_size\": 1}}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(KAKAO_KARLO_REQUEST_URI, requestEntity, String.class);
        return response.getBody();
    }

    // Base64 디코딩 및 변환
    public void base64ToImage(PinDto pin, String base64String) throws Exception {
        byte[] data = DatatypeConverter.parseBase64Binary(base64String);
        String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
        String path = "images" + File.separator + "pin";
        File file = new File(path);

        if (!file.exists()) {
            boolean wasSuccessful = file.mkdirs();
        }

        String fileName = UUID.randomUUID() + ".png";
        file = new File(absolutePath + path + File.separator + fileName);

        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        outputStream.write(data);

        file.setReadable(true);
        file.setWritable(true);

        pinImageRepository.save(PinImage.builder()
                .id(pin.getId())
                .url(absolutePath + path + File.separator + fileName)
                .status(PinImgStatus.CREATED)
                .build());
    }
}
