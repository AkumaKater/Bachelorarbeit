		Pac-Man OpenAIGym Interface



	Requirements
Python (3.7 recommended)
//Newer Python versions might lead to exceptions, since some newer versions are not backwards-compatible and remove some keywords

	Useful Tools
-IDE for Python (z.B. PyCharm)

	About OpenAIGym
https://www.gymlibrary.dev/



	Installation
1. Download OpenAIGym.
e.g. directly via the website https://github.com/openai/gym


2. Use the commandline/terminal (cmd.exe on Windows) to switch to the downloaded gym directory.
Command: cd pathToGym


3. Install OpenAIGym and the required libraries.
Command: pip install -e .

The whitespace and the dot are needed to install everything.

If the command pip can not be found, then the direct path to pip can be used:
e.g.
 C:\Users\USERNAME\AppData\Local\Programs\Python\Python37-32\Scripts\pip.exe install -e .

4. Install Pacman in OpenAIGym.
 pip install -e pathToGymPacmanEnvironment
//The directory gym-pacman-environment next to this readme


5. Set the desired number of games in PacmanAgent.py line 7


6. Start the Python Pacman agent.
e.g. via an IDE or via the commandline/terminal via
 python pathToGymPacmanEnvironment/PacmanAgent.py


Now the Python agent plays the desired number of games.

The Python code shouldn't mostly be changed. Excepted are the parts that are marked with TODO in PacmanAgent.py.




