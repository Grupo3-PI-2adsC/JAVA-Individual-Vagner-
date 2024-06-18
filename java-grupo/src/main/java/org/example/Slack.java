package org.example;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Slack {
    private static final String WEBHOOK_URL = "https://hooks.slack.com/services/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";


    public static void sendSlackAlert(String componente, String hostname, Long uso) {
        var logger = LoggerFactory.getLogger("slack-alert-service");
        try {
            // Obtendo data e hora atual
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String date = now.format(dateFormatter);
            String time = now.format(timeFormatter);
            String usoStr = uso.toString();

            String message = String.format(
                    ":warning: *Alerta:*\nComponente %s do hostname %s está acima do limite estabelecido com %s em uso!\nData: %s\nHora: %s",
                    componente, hostname, usoStr, date, time
            );
            String payload = String.format("{\"text\": \"%s\"}", message);

            URL url = new URL(WEBHOOK_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                logger.info("Alerta enviado para o Slack com sucesso!");
            } else {
                logger.error("Erro ao enviar alerta para o Slack: Código de resposta {}", responseCode);
            }

        } catch (IOException e) {
            logger.error("Erro ao enviar alerta para o Slack: {}", e.getMessage(), e);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.error("Erro ao esperar antes de enviar o próximo alerta: {}", e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
    public static void sendSlackAlert(String componente, String hostname, Double uso) {
        var logger = LoggerFactory.getLogger("slack-alert-service");
        try {
            // Obtendo data e hora atual
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String date = now.format(dateFormatter);
            String time = now.format(timeFormatter);

            String message = String.format(
                    ":warning: *Alerta:*\nComponente do hostname %s está acima do limite estabelecido com %.2f%% em uso!\nData: %s\nHora: %s",
                    componente, hostname, uso, date, time
            );
            String payload = String.format("{\"text\": \"%s\"}", message);

            URL url = new URL(WEBHOOK_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                logger.info("Alerta enviado para o Slack com sucesso!");
            } else {
                logger.error("Erro ao enviar alerta para o Slack: Código de resposta {}", responseCode);
            }

        } catch (IOException e) {
            logger.error("Erro ao enviar alerta para o Slack: {}", e.getMessage(), e);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.error("Erro ao esperar antes de enviar o próximo alerta: {}", e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}
