# object-oriented-programming-exam

//✅ Spiegazione dei Componenti
model/ – Contiene i dati e la logica:

Book è la classe dei libri

BookFactory applica il pattern Factory

BookCategory struttura i generi (Composite)

BookIterator per iterare i libri

controller/ – Coordina azioni tra GUI e modello

view/ – Interfaccia grafica Swing

utils/ – Utilità per file e log

exceptions/ – Gestione errori con sicurezza

test/ – Test unitari (JUnit)


# 📚 BookManager – Progetto Finale Java SE

## ✅ Descrizione

BookManager è un'applicazione Java SE con interfaccia grafica che consente di gestire una libreria personale. Gli utenti possono aggiungere, rimuovere, visualizzare e salvare libri con titolo, autore, anno e genere. I dati vengono salvati su file e possono essere ricaricati.

---

## 🧠 Funzionalità

- Inserimento di libri con validazione dei dati
- Visualizzazione elenco dei libri
- Rimozione libro selezionato
- Salvataggio e caricamento da file
- Interfaccia grafica in Swing
- Gestione sicura delle eccezioni
- Logging degli eventi e degli errori
- Test unitari con JUnit

---

## 🛠 Tecnologie e Pattern Utilizzati

| Categoria               | Elemento                                 | Giustificazione                                                                                                                                     |
|------------------------|------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| **Design Pattern**     | Factory                                   | Utilizzato per creare oggetti `Book` in modo centralizzato.                                                                                         |
|                        | Composite                                 | Utilizzato nella classe `BookCategory` per gestire categorie gerarchiche.                                                                           |
|                        | Iterator                                  | Classe `BookIterator` per iterare in sicurezza sui libri.                                                                                           |
|                        | Exception Shielding                       | Gestione eccezioni in `FileManager`, GUI e controller: evita crash e stack trace.                                                                   |
| **Core Technologies**  | Java Collections & Generics               | `List<Book>`, `DefaultListModel<Book>`, gestione dei dati generici.                                                                                 |
|                        | Java I/O                                  | Serializzazione di oggetti `Book` in `FileManager.java`.                                                                                             |
|                        | Logging                                   | Log centralizzato via `LoggerUtil.java` per eventi e errori.                                                                                        |
|                        | JUnit Testing                             | Test automatizzati per controller e funzionalità base (`BookTest.java`).                                                                            |

---

## 🔐 Sicurezza

- Nessun dato sensibile hardcoded
- Tutti gli input sono validati (es. l'anno è parsato in `int`)
- Tutte le eccezioni sono catturate e loggate (Exception Shielding)
- L'app non crasha in caso di errore: feedback all’utente tramite GUI

---

## ▶️ Esecuzione

### 🧩 Requisiti

- Java SE 11+
- JDK configurato nel PATH
- IDE (consigliato: IntelliJ IDEA, Eclipse) o CLI

### 🚀 Compilazione ed esecuzione

```bash
javac -d out -cp src src/Main.java
java -cp out Main
