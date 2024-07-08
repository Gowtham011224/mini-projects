# Route Planning Using Kruskal's Algorithm

## Project Description

This project involves implementing a route planning system using Kruskal's Algorithm. The system calculates the minimum spanning tree (MST) for a set of stations, thereby determining the most efficient way to connect all stations with the minimum total distance.

The main components of the project are:
- **C++ Code**: Implements the algorithm and handles input/output operations.
- **Case Study Report**: Provides an in-depth analysis of the project, including the problem statement, methodology, and results.

## Files

- `RRS.cpp`: The main C++ source code file for the project.
- `Report_CaseStudy.pdf`: A comprehensive report detailing the project.

## C++ Code Overview

The `RRS.cpp` file contains the following key sections:
1. **Structures**:
    - `Station`: Represents a station with a name and coordinates (x, y).
    - `WtEdge`: Represents a weighted edge between two stations.

2. **Functions**:
    - `findParent`: Helper function for the union-find algorithm.
    - `unionSets`: Merges two sets in the union-find algorithm.
    - `compareEdges`: Comparator for sorting edges by weight.
    - `kruskalMST`: Implements Kruskal's algorithm to compute the MST.

3. **Main Logic**:
    - Reads station data from a file.
    - Computes distances between stations and stores them as weighted edges.
    - Uses Kruskal's algorithm to compute the MST.
    - Outputs the MST edges and their total weight.

## Report Overview

The `Report_CaseStudy.pdf` contains detailed information about the project, including:

1. **Introduction**:
    - Background on route planning and the relevance of Kruskal's Algorithm.
2. **Problem Statement**:
    - The goal of finding the most efficient way to connect a set of stations.
3. **Methodology**:
    - Detailed steps on how the algorithm was implemented.
    - Explanation of the data structures and functions used.
4. **Results**:
    - Analysis of the computed MST.
    - Comparison with other possible solutions.
5. **Conclusion**:
    - Summary of findings and potential future improvements.

## How to Run the Code

1. **Prerequisites**:
    - A C++ compiler (e.g., g++).
2. **Compile the Code**:
    ```sh
    g++ RRS.cpp -o route_planning
    ```
3. **Run the Executable**:
    ```sh
    ./route_planning
    ```

## Usage

- Ensure that the input file containing station data is in the correct format.
- Run the program to generate the MST.
- The output will be displayed on the console and can also be redirected to a file if needed.

## Author

- **Gowtham Rajasekaran**

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---
