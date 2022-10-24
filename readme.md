Project for 2nd quarter in Java Rush university - **"Animal island" simulation.**
Author: Sasha Ivanilov 

**How to run it**
 - Run Runner class.

**How it works**
 - Simulation generates a game field (Cell[][]) randomly filled with animals and plants. Animals can eat each other, reproduce and move from one cell to another. ConsoleView provides summary regarding total number of creatures and brief overview for each cell. Each iteration lasts for 1 week.

**Settings**
- Main settings are extracted to "GameSettings" in main folder of the project.
  - ROWS and COLUMNS (default 10, 10): a size of the game field.
  - ITERATION_SPEED_IN_MILLIS (default 500): amount of milliseconds between prints of summary in console. It doesn't affect game results.
  - MIN_CHANCE_OF_SPAWN (default 0.25): minimal Dice.random() result for an animal to be born in the cell.
  - MAX_PLANT_MASS_IN_CELL (default 75000): max plant mass in kilos in the cell. 
  - PLANT_GROWTH_TEMPO (default 1.03): determines speed of plant growth. 1.03 means +3% each week. 
  - BASIC_TERRAIN_CHANCE, MOUNTAINS_CHANCE (default 0.8 (80%), 0.95 (15%)): cell has a terrain type - normal, river or mountain. Certain creatures can't pass through certain terrain types. These numbers mean minimal Dice.random() result to assign certain terrain type to a cell.
  - MIN_CHANCE_OF_NEW_POPULATION (default 0.95): minimal Dice.random() result for plants to spawn again in a plantless cell.
  - CARRION_DIVIDER (default 5) and CARRION_DECREASE_AT_THE_END_OF_ITERATION_MULTIPLIER (default 3.0): certain carnivores can eat leftovers from other carnivore's pray. The more the divider, the less carrion a cell stores. The more the decrease tempo, the faster unused carrion terminates.

- Parameters of exact creatures are not exported in settings. Please, change them in class constructors.

**Key features**
- Animals assign to cells randomly. Each simulation is unique. 
- Animals eat plants, or each other, or eat carrion. 
- Animals move from cell to cell each iteration and don't come back to departure cell within one iteration. Some animals don't move at all. 
- Reproduction is simplified: all animals can find mates, female animals can get pregnant and give birth certain weeks after. Birds may "get pregnant" as well but that means they are "nesting". Bird can't move if nesting at the moment.
- Detail view of game field is different for <=10 columns and 10-20 columns. Detail view for 20+ columns is not supported but the simulation should work fine for large game fields (if your computer has enough resources due to lack of optimization).

P.S. This is my first attempt to make project with multithreading. I hope you have fun as I did ^__^
