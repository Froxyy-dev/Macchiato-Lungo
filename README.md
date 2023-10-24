# Macchiato-Lungo

Project written for Object-Oriented Programming course at University of Warsaw (2nd semester).

## Task Description

In this task, you are required to implement a debugger and program execution environment for Macchiato 1.1, a simple programming language. Programs in Macchiato consist of a single block, and you will be creating classes to represent its instructions. Your solution should allow for program execution and debugging.

### Debugging Features

Your solution should provide two modes of program execution:

1. **Execution without Debugging**: The program runs from start to finish, handling errors but not invoking the debugger.

2. **Execution with Debugging**: The program pauses immediately before executing the first instruction, awaiting commands from standard input for debugging.

### Debugger Commands

The debugger supports the following commands:

- `c(ontinue)`: Resumes program execution. If the program has already finished, it displays an appropriate message.

- `s(tep) <number>`: Executes the program for the specified number of steps. A step is defined as the execution of a single instruction, including nested instructions. After completing the specified steps, it displays the instruction to be executed next. If the program ends before reaching the specified number of steps, it displays a relevant message.

- `d(isplay) <number>`: Displays the current variable values. The parameter indicates how many levels of higher blocks' variable values should be displayed. The command `d 0` displays the current variable values. If the provided number exceeds the nesting level of the current instruction, it displays an appropriate message.

- `e(xit)`: Aborts program execution and terminates the debugger. No final variable values are displayed.

### Macchiato Language Details

- Variables in Macchiato have one-letter names (English alphabet, 'a' to 'z') and are of integer type (like Java).

- Macchiato programs can include the following instructions:
  - **Block**: A block contains variable declarations and a sequence of instructions. Declarations are visible from their point of declaration to the end of the block.
  - **For Loop**: A loop that iterates over instructions a certain number of times.
  - **Conditional Statement (if)**: If-else constructs.
  - **Assignment**: Assigning values to variables.
  - **Print**: Printing the value of an expression to standard output.

### Procedural Extension

Macchiato 1.1 introduces procedures. Procedures act as functions with zero or more parameters, returning no values. They can be declared and invoked. The parameters are passed by value. The declarations are similar to variable declarations, and procedures are visible in the block they are declared in. If the same procedure name is declared within the same block, it results in an error.

## Usage

To run Macchiato programs and debug them, you can use the provided classes and functionalities. An example program is included in the `main` function.

## Tests

This project should include JUnit tests for each Macchiato language construct to ensure it functions correctly.
