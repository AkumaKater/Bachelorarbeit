		Pac-Man OpenAIGym Interface



	Voraussetzungen
Python (bevorzugt 3.7)
//Unter neueren Python Versionen kann es zu Problemen kommen, da manche
//Versionen nicht r�ckw�rtskompatibel sind und einige Schl�sselw�rter entfernen

	Hilfreiche Werkzeuge
-IDE f�r Python (z.B. PyCharm)

	About OpenAIGym
https://www.gymlibrary.dev/



	Installation
1. OpenAIGym herunterladen.
z.B. direkt �ber die Webseite https://github.com/openai/gym


2. �ber die Kommandozeile (cmd.exe unter Windows) in das heruntergeladene gym Verzeichnis wechseln.
Befehl: cd pfadZuGym


3. OpenAIGym installieren und ben�tigte Libraries herunterladen.
Befehl: pip install -e .

Das Leerzeichen und der Punkt am Ende sind wichtig, um alles zu installieren.

Solle das Kommando pip nicht gefunden werden, kann entweder der direkte Pfad zu pip genutzt werden:
z.B.
 C:\Users\BENUTZERNAME\AppData\Local\Programs\Python\Python37-32\Scripts\pip.exe install -e .

oder z.B. unter Windows die Umgebungsvariablen angepasst werden:

Explorer -> Dieser PC rechtsklicken -> Eigenschaften ->
Erweiterte Systemeinstellungen -> Umgebungsvariablen

Hier die Variable Path bearbeiten und den Python-Pfad hinzuf�gen.
z.B. C:\...\Python\Python37-32\


4. Pacman in OpenAIGym installieren.
 pip install -e pfadZumGymPacmanEnvironment
//Der Ordner gym-pacman-environment neben dieser Readme


5. Die gew�nschte Anzahl Spiele einstellen in PacmanAgent.py Zeile 7


6. Den Python Pacman-Agenten starten.
z.B. �ber eine IDE oder �ber die Kommandozeile mit
 python pfadZumGymPacmanEnvironment/PacmanAgent.py



Nun spielt der Python Agent die gew�nschte Anzahl an Spielen.

Die meisten Stellen im Python Code m�ssen nicht ge�ndert werden.
Ausgenommen hiervon sind die mit TODO gekennzeichneten Stellen in
PacmanAgent.py.




