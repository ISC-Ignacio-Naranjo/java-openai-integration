# 🤖 Java + OpenAI Integration

This project demonstrates how to integrate the OpenAI API (ChatGPT) into a Spring Boot Java application using `RestTemplate`.

## 🚀 Technologies Used
- Java 17+
- Spring Boot 3.x
- OpenAI API (gpt-3.5-turbo)
- Maven
- Lombok

## 🧪 What It Does
Exposes a REST endpoint that receives a prompt and responds with AI-generated text from ChatGPT.

## 📦 Project Structure

```
com.JING.openai
├── controller
│   └── OpenAiController.java
├── dto
│   └── OpenAiRequest.java
├── service
│   └── OpenAiService.java
```

## 🔧 How to Run

1. Clone the repository:

```bash
git clone https://github.com/ISC-Ignacio-Naranjo/java-openai-integration.git
cd java-openai-integration
```

2. Add your OpenAI API key to `src/main/resources/application.properties`:

```
openai.api.key=sk-xxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

3. Run the application:

```bash
./mvnw spring-boot:run
```

4. Send a POST request:

```bash
curl -X POST http://localhost:8080/api/openai -H "Content-Type: application/json" -d '{"prompt": "What is Spring Boot?"}'
```

## 📚 Related Content

This project supports a technical talk:
**"How to integrate AI into backend systems: A practical guide for Java developers."**

You’ll also find a demo article on LinkedIn and a short video on Reels/TikTok (coming soon).

## 📄 License

MIT
