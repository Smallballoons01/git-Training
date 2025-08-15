## Multi-Provider Text-to-Speech (Java)

This sample provides a unified interface to synthesize speech using:
- Azure Speech Services (via REST)
- Google Cloud Text-to-Speech (official client)
- ElevenLabs API (v1)

### Requirements
- Java 17+
- Maven 3.8+
- Network access to the provider APIs

### Credentials
- Azure Speech Services (REST):
  - Env: `AZURE_SPEECH_KEY` and `AZURE_SPEECH_REGION` (e.g., `eastus`).
- Google Cloud TTS:
  - Install `gcloud` or create a Service Account key JSON.
  - Set `GOOGLE_APPLICATION_CREDENTIALS=/absolute/path/to/key.json`.
- ElevenLabs:
  - Env: `ELEVENLABS_API_KEY`.

### Build
```bash
cd /Users/pengxinguang/培训/tts-multi-provider
mvn -q -DskipTests package
```

### Run demo
```bash
AZURE_SPEECH_KEY=... AZURE_SPEECH_REGION=eastus \
ELEVENLABS_API_KEY=... \
GOOGLE_APPLICATION_CREDENTIALS=/abs/path/key.json \
java -cp target/tts-multi-provider-1.0.0.jar:$(mvn -q -Dexec.classpathScope=runtime -Dexec.classpathMode=repo -DskipTests -Dexpression=project.build.outputDirectory -DforceStdout --non-recursive -Dexec.executable=echo -Dexec.args='%classpath' org.codehaus.mojo:exec-maven-plugin:3.2.0:exec) com.example.tts.DemoMain
```

### Custom Voices
- Azure: set `voiceName` (e.g., `en-US-JennyNeural`) and audio format.
- Google: set `languageCode` and `voiceName` (e.g., `en-US` + `en-US-Neural2-A`).
- ElevenLabs: set `voiceId` (from your ElevenLabs account) and optional `voiceSettings` (stability, similarity).

See `com.example.tts.DemoMain` for usage.

