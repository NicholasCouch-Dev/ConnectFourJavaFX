# 🟡 Connect Four JavaFX
**A modern, animated JavaFX version of the classic Connect Four game — complete with falling pieces, score tracking, and a polished user interface.**  

---

## 🖼️ Preview
*(Optional — add a screenshot here once you push your project)*  
![Connect Four JavaFX Screenshot](screenshot.png)

---

## 🚀 Features
✅ JavaFX UI — rich, interactive interface  
✅ Animated Gameplay — smooth falling piece animation  
✅ Scoreboard — tracks wins for Player X and Player O  
✅ Smart Win Detection — diagonal, horizontal, vertical logic  
✅ Reset Button — instant new match  
✅ Clean MVC Structure — organized classes for scalability  

---

## 🧠 How It Works
- **GameBoard.java:** creates the 6×7 grid using JavaFX `GridPane`.  
- **GameLogic.java:** handles win checking and board resets.  
- **Main.java:** initializes the JavaFX app and displays the stage.  
- **Scoreboard.java:** keeps track of player wins.  

---

## ⚙️ Requirements
- Java JDK 17+ (Java 24 recommended)  
- JavaFX SDK 25.0.1  
- IntelliJ IDEA  

---

## 🧩 Setup Instructions

### 🪜 Step 1 — Download JavaFX SDK
1. Visit [https://openjfx.io](https://openjfx.io).  
2. Download **JavaFX SDK 25.0.1** for your OS.  
3. Unzip to: `C:\Users\Nicholas\Desktop\JavaFX\javafx-sdk-25.0.1`  

### ⚙️ Step 2 — Configure IntelliJ
1. Go to **File → Project Structure → Libraries → + → Java**  
2. Select your JavaFX `lib` folder.  
3. In **Run → Edit Configurations → VM Options**, add:  
   ```
   --module-path "C:\Users\Nicholas\Desktop\JavaFX\javafx-sdk-25.0.1\lib" --add-modules javafx.controls,javafx.fxml
   ```

### ▶️ Step 3 — Run the App
Run the `Main` class — enjoy smooth, animated Connect Four gameplay! 🎮

---

## 🏗️ Project Structure
```
ConnectFourJavaFX/
├── src/
│   ├── com/example/connectfourjavafx/
│   │   ├── Main.java
│   │   ├── GameBoard.java
│   │   ├── GameLogic.java
│   │   └── Scoreboard.java
├── .gitignore
├── README.md
└── pom.xml (optional, if you add Maven later)
```

---

## 🧰 Tech Stack
| Tool | Description |
|------|--------------|
| **JavaFX** | GUI framework |
| **Java 24** | Core language |
| **IntelliJ IDEA** | IDE used |
| **Git / GitHub** | Version control |

---

## 🧠 Future Improvements
- 🎵 Add sound effects  
- 🤖 Add AI opponent  
- 💾 Save player stats  
- 🎨 Add color themes  

---

## 👨‍💻 Author
**Nicholas Couch**  
🚀 Java Software Engineer in progress  
📫 [GitHub: NicholasCouch-Dev](https://github.com/NicholasCouch-Dev)
