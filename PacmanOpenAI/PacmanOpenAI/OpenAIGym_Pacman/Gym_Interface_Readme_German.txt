		Pac-Man OpenAIGym Interface



	Voraussetzungen
Python (bevorzugt 3.7)
//Unter neueren Python Versionen kann es zu Problemen kommen, da manche
//Versionen nicht rückwärtskompatibel sind und einige Schlüsselwörter entfernen

	Hilfreiche Werkzeuge
-IDE für Python (z.B. PyCharm)

	About OpenAIGym
https://www.gymlibrary.dev/



	Installation
1. OpenAIGym herunterladen.
z.B. direkt über die Webseite https://github.com/openai/gym


2. Über die Kommandozeile (cmd.exe unter Windows) in das heruntergeladene gym Verzeichnis wechseln.
Befehl: cd pfadZuGym


3. OpenAIGym installieren und benötigte Libraries herunterladen.
Befehl: pip install -e .

Das Leerzeichen und der Punkt am Ende sind wichtig, um alles zu installieren.

Solle das Kommando pip nicht gefunden werden, kann entweder der direkte Pfad zu pip genutzt werden:
z.B.
 C:\Users\BENUTZERNAME\AppData\Local\Programs\Python\Python37-32\Scripts\pip.exe install -e .

oder z.B. unter Windows die Umgebungsvariablen angepasst werden:

Explorer -> Dieser PC rechtsklicken -> Eigenschaften ->
Erweiterte Systemeinstellungen -> Umgebungsvariablen

Hier die Variable Path bearbeiten und den Python-Pfad hinzufügen.
z.B. C:\...\Python\Python37-32\


4. Pacman in OpenAIGym installieren.
 pip install -e pfadZumGymPacmanEnvironment
//Der Ordner gym-pacman-environment neben dieser Readme


5. Die gewünschte Anzahl Spiele einstellen in PacmanAgent.py Zeile 7


6. Den Python Pacman-Agenten starten.
z.B. über eine IDE oder über die Kommandozeile mit
 python pfadZumGymPacmanEnvironment/PacmanAgent.py



Nun spielt der Python Agent die gewünschte Anzahl an Spielen.

Die meisten Stellen im Python Code müssen nicht geändert werden.
Ausgenommen hiervon sind die mit TODO gekennzeichneten Stellen in
PacmanAgent.py.




