# Project Overview: Knowledge Hub (Document Q&A System)

## 📌 Objective
Knowledge Hub is a Document Question-Answering (Q&A) application built with **Spring Boot** and **Java 21**. It enables users to upload/provide documents and ask questions, receiving answers derived **strictly** from the content of the provided document.

---

## 🎯 Core Requirements & Features

1. **Document Ingestion & Text Processing**
   - Support uploading and reading documents (e.g., PDF, TXT, DOCX, Markdown).
   - Extract text content, slice into chunks, and index for retrieval.

2. **Document-Restricted Q&A (RAG / Strict Grounding)**
   - Allow users to query the document using natural language.
   - Retrieve context chunks relevant to the user's question.
   - Constrain AI responses so that answers are grounded **only** in the document content (preventing hallucination or external knowledge leakage).

3. **Web API & User Interface**
   - Provide REST API endpoints for document upload, processing status, and asking questions.
   - Interactive UI for uploading files and chatting with documents.

---

## 🛠️ Technology Stack
- **Language**: Java 21
- **Framework**: Spring Boot (`spring-boot-starter-web`)
- **Build Tool**: Maven (`mvnw`)
- **Architecture**: Retrieval-Augmented Generation (RAG) / Document Indexing & Vector Search
