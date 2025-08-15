package com.example.tts;

import java.nio.file.Path;

public interface TtsProvider {
	/**
	 * Synthesize given text into a speech audio file.
	 *
	 * @param text text to synthesize
	 * @param voiceName provider-specific voice name/id (e.g., Azure voice name, Google voice, or ElevenLabs voiceId)
	 * @param languageCode BCP-47 language code when applicable (e.g., en-US)
	 * @param audioFormat output audio MIME or file extension hint (e.g., "audio/mpeg" or "mp3")
	 * @param outputFile target file path to write audio bytes to (parent directories must exist)
	 * @throws Exception on API or IO errors
	 */
	void synthesizeToFile(String text,
	                     String voiceName,
	                     String languageCode,
	                     String audioFormat,
	                     Path outputFile) throws Exception;
}
