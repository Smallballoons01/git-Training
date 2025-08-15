package com.example.tts.providers;

import com.example.tts.TtsProvider;
import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;

import java.nio.file.Files;
import java.nio.file.Path;

public class GoogleTtsProvider implements TtsProvider {
	@Override
	public void synthesizeToFile(String text, String voiceName, String languageCode, String audioFormat, Path outputFile) throws Exception {
		try (TextToSpeechClient client = TextToSpeechClient.create()) {
			SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

			String lang = (languageCode == null || languageCode.isBlank()) ? "en-US" : languageCode;
			VoiceSelectionParams.Builder voiceBuilder = VoiceSelectionParams.newBuilder().setLanguageCode(lang);
			if (voiceName != null && !voiceName.isBlank()) {
				voiceBuilder.setName(voiceName);
			}

			AudioEncoding encoding = mapGoogleEncoding(audioFormat);
			AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(encoding).build();

			var response = client.synthesizeSpeech(input, voiceBuilder.build(), audioConfig);
			byte[] audio = response.getAudioContent().toByteArray();
			Files.write(outputFile, audio);
		}
	}

	private static AudioEncoding mapGoogleEncoding(String audioFormat) {
		String fmt = audioFormat == null ? "" : audioFormat.toLowerCase();
		switch (fmt) {
			case "mp3":
			case "audio/mpeg":
				return AudioEncoding.MP3;
			case "ogg":
			case "audio/ogg":
				return AudioEncoding.OGG_OPUS;
			case "wav":
			case "audio/wav":
				return AudioEncoding.LINEAR16;
			default:
				return AudioEncoding.MP3;
		}
	}
}
