# Wie programmiert man ein Convolutional Neural Network?

Dieser spannenden Frage möchte ich in meiner Bachelorarbeit auf den Grund gehen.
Das hier ist das Repository, in welchen sich der Code und die ausgeschriebene Bachelorarbeit befinden.
Das Projekt basiert auf meinen Ergebnissen aus der Projektarbeit, und setzt diese sozusagen fort:

## Wie programmiert man ein einfaches Neuronales Netzwerk?

Diese Frage habe ich in meiner Projektarbeit behandelt. 
In diesem Repository lässt sich alles nachvollziehen:
https://github.com/AkumaKater/Projektarbeit

## Die Bachelorarbeit

Die Bachelorarbeit befindet sich im PDF Format in diesem Ordner, unter dem namen "Bachelorarbeit.pdf".

## Der Code

Im src Ordner befindet sich das Projekt:
- CCN
- FFNetwork

CNN enthält das neue Convolutional Network. Hier ist das Ergebniss meiner Praktischen Arbeit.

FFNetwork ist au meiner Projektarbeit kopiert, und wurde verwendet, um bestimmte Änderungen vorzunehmen. 
Dazu gehören vor allem Tests zur verwalltung der Forwart- und Backpropagation. Im Ursprünglichen Projekt hat die NeuralNetwork Klasse die Aus und eingaben der Schichten verwaltet. Um aber eine Liste zu ermöglichen, in der mehrere verschiedene Schichten enthalten sind, wurde im neuen Projekt eine Abstracte Klasse Layer erstellt, und da ist es eleganter, wenn die Schichten ihre Ergebnisse einfach weiterreichen. Um diesen Ansatz zu testen habe ich die Alte Klasse layer und die Alte Klasse Neural network angepasst, und in diesem Projekt mit eingebracht, um meine Arbeitsweise besser nachvollziehen zu können.