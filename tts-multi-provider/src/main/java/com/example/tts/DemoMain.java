package com.example.tts;

import com.example.tts.providers.AzureTtsProvider;
import com.example.tts.providers.ElevenLabsTtsProvider;
import com.example.tts.providers.GoogleTtsProvider;

import java.nio.file.Files;
import java.nio.file.Path;

public class DemoMain {
	public static void main(String[] args) throws Exception {
		String text = "Hello, this is a demo for multi-provider Text-to-Speech.";
		Path outDir = Path.of("./out");
		Files.createDirectories(outDir);

		// Azure
		// String azureKey = System.getenv("AZURE_SPEECH_KEY");
		// String azureRegion = System.getenv("AZURE_SPEECH_REGION");
		// TtsProvider azure = new AzureTtsProvider(azureKey, azureRegion);
		// azure.synthesizeToFile(text, "en-US-JennyNeural", "en-US", "mp3", outDir.resolve("azure.mp3"));

		// Google
		TtsProvider google = new GoogleTtsProvider();
		google.synthesizeToFile(text, "en-US-Neural2-A", "en-US", "mp3", outDir.resolve("google.mp3"));

		// ElevenLabs (voiceName is the voiceId)
		// String elevenApiKey = System.getenv("ELEVENLABS_API_KEY");
		// String elevenVoiceId = System.getenv().getOrDefault("ELEVENLABS_VOICE_ID", "");
		// TtsProvider eleven = new ElevenLabsTtsProvider(elevenApiKey, elevenVoiceId.isBlank() ? null : elevenVoiceId);
		// eleven.synthesizeToFile(text, elevenVoiceId, "en-US", "mp3", outDir.resolve("elevenlabs.mp3"));

		System.out.println("Synthesis complete. Files written to " + outDir.toAbsolutePath());
	}
}
