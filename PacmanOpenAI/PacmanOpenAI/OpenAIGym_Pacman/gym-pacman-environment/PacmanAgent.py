import gym
from gym import logger

# TODO set the desired number of games to play
episode_count = 1

# Set to False to disable that information about the current state of the game are printed out on the console
# Be aware that the gameworld is printed transposed to the console, to avoid mapping the coordinates and actions
printState = True

# TODO Set this to the desired level
old_level = [
    ["#", "#", "#", "#", "#"],
    ["#", " ", " ", " ", "#"],
    ["#", "P", "*", "*", "#"],
    ["#", " ", " ", " ", "#"],
    ["#", " ", " ", " ", "#"],
    ["#", "*", "R", "H", "#"],
    ["#", "#", "#", "#", "#"]
]

level = [
    ["#", "#", "#", "#", "#", "#", "#"],
    ["#", "*", "*", "P", "*", "*", "#"],
    ["*", "*", "#", "*", "#", "*", "*"],
    ["#", "*", "*", "*", "*", "*", "#"],
    ["#", "#", "*", "#", "*", "#", "#"],
    ["#", "H", "*", "*", "*", "R", "#"],
    ["#", "#", "#", "*", "#", "#", "#"]
]

levels = [
    ["#", "#", "#", "#", "#", "#", "#"],
    ["#", "*", "*", "*", "*", "*", "#"],
    ["#", "*", "#", "*", "#", "*", "#"],
    ["#", "P", "*", "*", "*", "H", "#"],
    ["#", "*", "#", "*", "#", "*", "#"],
    ["#", "*", "*", "*", "*", "*", "#"],
    ["#", "#", "#", "#", "#", "#", "#"]
]


# You can set this to False to change the agent's observation to Box from OpenAIGym - see also PacmanEnv.py
# Otherwise a 2D array of tileTypes will be used
usingSimpleObservations = False

# Defines all possible types of tiles in the game and how they are printed out on the console
# Should not be changed unless you want to change the rules of the game
tileTypes = {
    "empty": " ",
    "wall": "#",
    "dot": "*",
    "pacman": "P",
    "ghost_rnd": "R",
    "ghost_hunter": "H"
}

# Will be automatically set to True by the PacmanAgent if it is used and should not be set manually
usingPythonAgent = False


class PacmanAgent(gym.Wrapper):
    # Set the class attribute
    global usingPythonAgent
    usingPythonAgent = True

    def __init__(self, env_name='gym_pacman_environment:pacman-python-v0'):
        """
        """
        super(PacmanAgent, self).__init__(gym.make(env_name))
        self.env_name = env_name
        self.action_space = self.env.action_space

    def act(self, action=None):
        """
        Determine the next action of the agent
        :return: The agent's next action
        """

        # By Default the agent chooses the next action randomly out of all possible actions (0, 1, 2, 3)
        # 0 = up, 1 = left, 2 = right, 3 = down
        # Convert the action
        if action == 0:
            action = "GO_NORTH"
        elif action == 1:
            action = "GO_WEST"
        elif action == 2:
            action = "GO_EAST"
        elif action == 3:
            action = "GO_SOUTH"
        else:
            raise Exception("Invalid Action:", action)

        return action

    def step(self, action):
        """
        Execute one time step within the environment
        :param action: The action to be executed
        :return: observation, reward, done, info
        """
        return self.env.step(self.act(action=action))

    def reset(self):
        """
        Resets the state of the environment and returns an initial observation.
        :return: observation (object): the initial observation of the space.
        """
        return self.env.reset()


if __name__ == '__main__':
    # Can also be set to logger.WARN or logger.DEBUG to print out more information during the game
    logger.set_level(logger.INFO)

    # Select which gym-environment to run
    env = PacmanAgent()

    # Execute all episodes by reseting the game and play it until it is over
    for i in range(episode_count):
        observation = env.reset()
        reward = 0
        done = False

        while True:
            # Determine the agent's next action based on the current observation and reward and execute it
            env.render()
            # TODO better action selection
            action = env.action_space.sample()
            observation, reward, done, debug = env.step(action)

            if done:
                break

    env.close()
