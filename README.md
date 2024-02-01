# Wie programmiert man ein einfaches Neuronales Netzwerk

Das hier ist meine Projektarbeit. Das Thema ist "Wie Programmiert man ein einfaches Neuronales Netzwerk".

## Die Projektarbeit

Die Projektarbeit befindet sich im PDF Format in diesem Ordner, unter dem namen "Projektarbeit.pdf".

## Der Code

Im src Ordner befinden sich zwei Projekte:

- FFNetwork
- FFNetworkNoBiasNoBatch

Im Ordner FFNetwork befindet sich der Code für das Netzwerk. Die Klasse "Main" enthält alle Notwendigen Anweisungen, um das netztwerk zu Starten.

Um das Netzwerk zu Configurieren, muss man die Datei "config.json" anpassen.
Bitte Achten Sie darauf, das die Datei keine Leerzeichen um die Gleichheitszeichen herum haben darf.
In der config Datei sind die letzten zwei Einträge die Namen der Dateien, in die die Ergebnisse des netzwerkes geschrieben werden. Dabei handelt es sich um HTML Dateien, die im Browser geöffnet werden können.

Das zweite Projekt, "FFNetworkNoBiasNoBatch", enthält ein Netzwerk ohne Biases und Batches. Wenn Interesse besteht, diesen Code zu starten, dann muss der Packege Import in der Klasse "src/MNISTReader/MNISTHTML.java" angepasst werden.
Anstatt dem "import FFNetworkNoBiasNoBatch.\*;", muss dann "import FFNetwork.\*;" auskommentiert werden.

## Anmerkung

Falls Interesse daran besteht, das Repository zu sehen, in welchem diese Projektarbeit erstellt wurde, dann folgen Sie diesem Link:
https://github.com/AkumaKater/NNLayers
