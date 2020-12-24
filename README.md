# Sliding Puzzle
A sliding puzzle consists of a frame of numbered NxM tiles in random order, with one tile missing.
The goal is to place the tiles in order, by making sliding moves that use the empty space.

## Description
The tiles in this puzzle, except for the empty space, are painted in one of three colors.
- ![#008000](https://via.placeholder.com/11/008000/000000?text=+) Green - which indicates a tile with a sliding cost of 1.
- ![#ff0000](https://via.placeholder.com/11/ff0000/000000?text=+) Red - which indicates a tile with a sliding cost of 30.
- ![#000000](https://via.placeholder.com/11/000000/000000?text=+) Black - which indicates a tile that cannot be moved.

If the color of a certain tile is neither red nor black, it is green by default.

This project offers 5 different search algorithms for solving the puzzle, two of which are categorized under the uninformed search class, while the others are categorized under the informed search class.
- For the uninformed search algorithms, the goal is to find a sequence of primitive steps, with a **minimal number of moves** that will lead us from the initial state to the goal state.
- For the informed search algorithms, the goal is to find a sequence of primitive steps, with a **minimal cost of moves** that will lead us from the initial state to the goal state.

## Installation
- Fill in the attached input file, according to the given format.
- Open your IDE.
- Run the project via Execute class.
- Check the results in the output file.

For more inputs, please open the attached inputs folder.

## License
[MIT](https://choosealicense.com/licenses/mit/)