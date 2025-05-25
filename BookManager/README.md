# 📚 BookManager Java SE Project

## ✅ Overview

BookManager è un'applicazione Java SE con interfaccia grafica Swing che consente la gestione di una libreria personale. L'utente può:

* Aggiungere e rimuovere libri
* Ordinare i libri per titolo, autore o anno
* Salvare e caricare i dati da file
* Esportare l'elenco in CSV

Ogni libro è rappresentato da titolo, autore, anno e genere.

---

## 🤖 Technologies and Design Patterns

### Core Requirements

| Tecnologie / Pattern       | Motivazione                                                                   |
| -------------------------- | ----------------------------------------------------------------------------- |
| **Factory**                | `BookFactory` centralizza la creazione dei libri                              |
| **Composite**              | `BookCategory` consente gerarchie di categorie e sottocategorie               |
| **Iterator**               | `BookIterator` per iterare in modo controllato sulla lista                    |
| **Exception Shielding**    | Eccezioni come `BookException` schermano input invalidi e notificano l'utente |
| **Collections + Generics** | Uso estensivo di `List<Book>`                                                 |
| **Java I/O**               | `FileManager` usa `java.nio.file.*` per lettura/scrittura                     |
| **Logging**                | `LoggerUtil` centralizza la gestione log                                      |
| **JUnit Testing**          | Test unitari su controller, modello e categorie                               |

### Advanced

| Feature                 | Motivazione                                                                   |
| ----------------------- | ----------------------------------------------------------------------------- |
| **Strategy**            | `SortStrategy` per gestire ordinamenti flessibili                             |
| **Observer**            | `BookManagerGUI` si aggiorna automaticamente a ogni modifica                  |
| **Thread (Async Load)** | Esecuzione asincrona sia di caricamento che salvataggio con `ExecutorService` |
| **Stream API**          | Filtering e conteggi reattivi                                                 |

---

## ⚡ Setup & Execution

### Requisiti

* JDK 17+
* Maven

### Compilazione

```bash
cd BookManager
mvn clean install
```

### Esecuzione

```bash
mvn exec:java -Dexec.mainClass="Main"
```

Oppure:

```bash
java -cp target/classes Main
```

---

## 🖌️ UML Diagrams

* `src/uml/class-diagram1.png`
* `src/uml/architecture-diagram1.png`

---

## ❌ Limitazioni & Future Work

* Parser `.txt` migliorabile con formati strutturati (JSON, XML)
* Aggiunta supporto a ricerca full-text
* Test per componenti GUI

---

## 🤝 Contributori

* \[SALVATORE DI MARIA] – autore principale

---

## ✏️ Note Finali

Il progetto segue principi SOLID, MVC e design pattern per garantire modularità e manutenibilità.
