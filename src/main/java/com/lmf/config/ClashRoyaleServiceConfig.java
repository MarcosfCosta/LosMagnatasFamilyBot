package com.lmf.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ComponentScan({"com.lmf.config"})
@PropertySource({"classpath:lmfbot-default.properties"})
public class ClashRoyaleServiceConfig {

    @Value("${lmfbot.crapi.baseUrl:https://api.clashroyale.com/v1/}")
    private String baseUrl;

    @Value("${lmfbot.crapi.apiKey:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImQ0ZWY5YjY0LWZjZTAtNDE5MS1hOThmLWI0ZWZlZWNhYTJkOCIsImlhdCI6MTY1NTY3MzIwNiwic3ViIjoiZGV2ZWxvcGVyL2Y0MzVjNWNkLWZjMjEtOTExYS1kNGQzLTZmMDRkNGQzYzU1OCIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyI5NC4xMzIuMjI3LjM3Il0sInR5cGUiOiJjbGllbnQifV19._x-TzN79P7W0TAwpTLLbO48_909oxUGdxrm8oXT3bBmiE5-yJ6aUa-JiP0xqXhil18JWZTD4Cz9DiPfJqqzH3A\n}")
    private String apiKey;
}
