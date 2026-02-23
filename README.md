# Sudoku (Java)

This repository contains my Java-based Sudoku implementation designed to run in the console.

The project was paused due to a significant logical issue encountered during the number generation phase, likely caused by limitations in the mathematical approach or the need for a more advanced algorithmic solution.

## Overview

The application generates a Sudoku grid directly in the console environment.

### Core Components

- **Main**  
  Entry point of the application. Runs and initializes the program.

- **Grid**  
  Responsible for:
  - Creating the visual representation of the Sudoku board
  - Managing cell references and structure

- **Gene**  
  Handles the generation logic by attempting to fill the grid with a valid Sudoku pattern.

## Known Issue

During the final stage of generation (typically when placing digits **6, 7, 8, and 9**), the logic begins to fail.

### Problem Description

- The generation algorithm struggles to complete the last few placements.
- The current mathematical approach is insufficient for resolving late-stage conflicts.
- The system likely requires:
  - A revised mathematical strategy, or
  - A more advanced algorithmic solution (e.g., improved constraint handling or backtracking mechanics)

## Status

Development is currently paused due to the unresolved generation logic issue.
