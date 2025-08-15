package com.example.tts.providers;

import com.example.tts.TtsProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ElevenLabsTtsProvider implements TtsProvider {
	private static final MediaType JSON = MediaType.parse("application/json");

	private final OkHttpClient http;
	private final ObjectMapper mapper;
	private final String apiKey;
	private final String defaultVoiceId;

	public ElevenLabsTtsProvider(String apiKey, String defaultVoiceId) {
		this.apiKey = Objects.requireNonNull(apiKey, "apiKey");
		this.defaultVoiceId = defaultVoiceId; // may be null if always provided in call
		this.http = new OkHttpClient.Builder().callTimeout(Duration.ofSeconds(60)).build();
		this.mapper = new ObjectMapper();
	}

	@Override
	public void synthesizeToFile(String text, String voiceName, String languageCode, String audioFormat, Path outputFile) throws Exception {
		String voiceId = (voiceName != null && !voiceName.isBlank()) ? voiceName : defaultVoiceId;
		if (voiceId == null || voiceId.isBlank()) {
			throw new IllegalArgumentException("ElevenLabs voiceId must be provided (as voiceName) or configured in provider");
		}

		String endpoint = String.format("https://api.elevenlabs.io/v1/text-to-speech/%s", voiceId);

		Map<String, Object> payload = new HashMap<>();
		payload.put("text", text);
		payload.put("model_id", "eleven_monolingual_v1");
		Map<String, Object> voiceSettings = new HashMap<>();
		voiceSettings.put("stability", 0.5);
		voiceSettings.put("similarity_boost", 0.75);
		payload.put("voice_settings", voiceSettings);

		Request request = new Request.Builder()
				.url(endpoint)
				.addHeader("xi-api-key", apiKey)
				.addHeader("Accept", mapElevenLabsAccept(audioFormat))
				.post(RequestBody.create(mapper.writeValueAsString(payload), JSON))
				.build();

		try (Response response = http.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				String body = response.body() != null ? response.body().string() : "";
				throw new IOException("ElevenLabs TTS failed: HTTP " + response.code() + " - " + body);
			}
			byte[] audio = response.body().bytes();
			Files.write(outputFile, audio);
		}
	}

	private static String mapElevenLabsAccept(String audioFormat) {
		String fmt = audioFormat == null ? "" : audioFormat.toLowerCase();
		switch (fmt) {
			case "mp3":
			case "audio/mpeg":
				return "audio/mpeg";
			case "wav":
			case "audio/wav":
				return "audio/wav";
			case "ogg":
			case "audio/ogg":
				return "audio/ogg";
			default:
				return "audio/mpeg";
		}
	}
}
