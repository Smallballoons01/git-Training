package com.example.tts.providers;

import com.example.tts.TtsProvider;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Objects;

public class AzureTtsProvider implements TtsProvider {
	private final OkHttpClient http;
	private final String subscriptionKey;
	private final String region;

	public AzureTtsProvider(String subscriptionKey, String region) {
		this.subscriptionKey = Objects.requireNonNull(subscriptionKey, "subscriptionKey");
		this.region = Objects.requireNonNull(region, "region");
		this.http = new OkHttpClient.Builder()
				.callTimeout(Duration.ofSeconds(60))
				.build();
	}

	@Override
	public void synthesizeToFile(String text, String voiceName, String languageCode, String audioFormat, Path outputFile) throws Exception {
		String endpoint = String.format("https://%s.tts.speech.microsoft.com/cognitiveservices/v1", region);

		String voice = voiceName != null && !voiceName.isBlank() ? voiceName : "en-US-JennyNeural";
		String formatHeader = mapAzureFormatHeader(audioFormat);

		String ssml = buildSsml(text, voice, languageCode);
		Request request = new Request.Builder()
				.url(endpoint)
				.addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
				.addHeader("Content-Type", "application/ssml+xml")
				.addHeader("X-Microsoft-OutputFormat", formatHeader)
				.addHeader("User-Agent", "tts-multi-provider/1.0")
				.post(RequestBody.create(ssml, MediaType.parse("application/ssml+xml")))
				.build();

		try (Response response = http.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Azure TTS failed: HTTP " + response.code() + " - " + (response.body() != null ? response.body().string() : ""));
			}
			byte[] audio = response.body().bytes();
			Files.write(outputFile, audio);
		}
	}

	private static String mapAzureFormatHeader(String audioFormat) {
		String fmt = audioFormat == null ? "" : audioFormat.toLowerCase();
		switch (fmt) {
			case "mp3":
			case "audio/mpeg":
				return "audio-48khz-192kbitrate-mono-mp3";
			case "wav":
			case "audio/wav":
				return "riff-48khz-16bit-mono-pcm";
			case "ogg":
			case "audio/ogg":
				return "ogg-48khz-16bit-mono-opus";
			default:
				return "audio-48khz-192kbitrate-mono-mp3";
		}
	}

	private static String buildSsml(String text, String voiceName, String languageCode) {
		String lang = (languageCode == null || languageCode.isBlank()) ? "en-US" : languageCode;
		String safeText = text == null ? "" : text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
		return "<speak version=\"1.0\" xml:lang=\"" + lang + "\">" +
				"<voice name=\"" + voiceName + "\">" + safeText + "</voice>" +
				"</speak>";
	}
}
