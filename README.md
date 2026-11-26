 <div align="center">
  <h1>🎲 Data Structures</h1>

  <h3>A Java Implementation of Fundamental Data Structures and Algorithms</h3>

  <p>
    <em>"Understanding how things work is the first step toward mastering them."</em>
  </p>

  <p>
    <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
    <img src="https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
    <img src="https://img.shields.io/badge/Testing-JUnit%205-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit 5" />
    <img src="https://img.shields.io/badge/Coverage-JaCoCo-orange?style=for-the-badge" alt="JaCoCo" />
    <img src="https://img.shields.io/badge/License-MIT-brightgreen?style=for-the-badge" alt="MIT License" />
  </p>
</div>

<hr />

<div align="justify">
  <h2>📖 About the Project</h2>

  <p>
    <strong>Data Structures</strong> is a Java project developed as part of my journey to understand fundamental data structures, algorithms, and the principles behind efficient data organization and manipulation.
  </p>

  <p>
    Although Java provides a comprehensive Collections Framework with ready-to-use implementations, this project focuses on building classic data structures from scratch to gain a deeper understanding of their internal mechanisms, behaviors, and trade-offs.
  </p>

  <p>
    The repository covers different approaches to organizing and accessing data, including arrays, linked structures, stacks, queues, sets, hash tables, trees, and graphs. It also explores fundamental searching and sorting algorithms.
  </p>

  <p>
    Each implementation serves as a practical exercise in understanding how data structures operate, how their underlying algorithms work, and how different implementation choices affect performance and memory usage.
  </p>

  <blockquote>
    <em>Learning data structures is not just about knowing how to use them, but understanding how they work under the hood.</em>
  </blockquote>
</div>

<hr />

<div align="justify">
  <h2>✨ Features</h2>

  <ul>
    <li><strong>Custom Data Structures:</strong> Implements fundamental data structures from scratch without relying on their standard Java Collections Framework implementations.</li>
    <li><strong>Multiple Implementations:</strong> Explores different approaches to the same abstract data structure, such as array-based and linked implementations.</li>
    <li><strong>Searching Algorithms:</strong> Includes fundamental techniques for locating elements in collections.</li>
    <li><strong>Sorting Algorithms:</strong> Implements classic sorting techniques and explores their different approaches.</li>
    <li><strong>Generic Implementations:</strong> Uses Java generics to support reusable and type-safe data structures.</li>
    <li><strong>Automated Testing:</strong> Provides unit tests for the implemented data structures and supporting utilities.</li>
    <li><strong>Code Coverage Analysis:</strong> Integrates JaCoCo to measure test coverage and identify untested portions of the codebase.</li>
  </ul>
</div>

<hr />

<div align="justify">
  <h2>🧩 Implemented Data Structures</h2>

  <p>
    The project explores different categories of data structures, including linear collections, associative structures, hierarchical structures, and graph representations.
  </p>

  <h3>Linear Data Structures</h3>

  <p>
    Linear data structures organize elements sequentially, allowing them to be traversed in a specific order.
  </p>

  <ul>
    <li><strong>ArrayList:</strong> A dynamic array that automatically resizes when necessary, providing efficient indexed access and iteration.</li>
    <li><strong>SinglyLinkedList:</strong> A linked structure in which each node references the next element, allowing flexible insertion and removal.</li>
    <li><strong>DoublyLinkedList:</strong> A linked structure in which each node maintains references to both the previous and next elements, enabling bidirectional traversal.</li>
  </ul>

  <h3>Stacks</h3>

  <p>
    Stacks follow the <strong>LIFO (Last In, First Out)</strong> principle, where the last element inserted is the first one removed.
  </p>

  <ul>
    <li><strong>ArrayStack:</strong> A stack implemented using an array to store its elements.</li>
    <li><strong>LinkedStack:</strong> A stack implemented using linked nodes, allowing dynamic growth without requiring array resizing.</li>
  </ul>

  <h3>Queues</h3>

  <p>
    Queues generally follow the <strong>FIFO (First In, First Out)</strong> principle, where the first element inserted is the first one removed.
  </p>

  <ul>
    <li><strong>ArrayQueue:</strong> A queue implemented using an array to manage its elements.</li>
    <li><strong>LinkedQueue:</strong> A queue implemented using linked nodes, allowing dynamic insertion and removal.</li>
    <li><strong>CircularQueue:</strong> A queue that uses a circular arrangement to reuse available array positions.</li>
    <li><strong>PriorityQueue:</strong> A queue that processes elements according to their priority rather than their insertion order.</li>
  </ul>

  <h3>Sets</h3>

  <p>
    Sets represent collections of unique elements, preventing duplicate values.
  </p>

  <ul>
    <li><strong>ArraySet:</strong> A set implemented using an array to store unique elements.</li>
    <li><strong>LinkedSet:</strong> A set implemented using a linked structure to maintain unique elements.</li>
    <li><strong>HashSet:</strong> A set that uses hashing to organize elements and provide efficient average-time operations.</li>
  </ul>

  <h3>Maps and Hash Tables</h3>

  <p>
    Associative data structures organize information through key-value relationships, allowing values to be retrieved using their corresponding keys.
  </p>

  <ul>
    <li><strong>Hashtable:</strong> A hash-based key-value structure that uses hashing to organize and retrieve entries efficiently.</li>
  </ul>

  <h3>Trees</h3>

  <p>
    Trees are hierarchical data structures that organize elements through parent-child relationships.
  </p>

  <ul>
    <li><strong>BinaryTree:</strong> A hierarchical structure in which each node can have at most two children.</li>
    <li><strong>BinarySearchTree:</strong> A binary tree that maintains an ordering property, placing smaller values in the left subtree and larger values in the right subtree.</li>
    <li><strong>AVLTree:</strong> A self-balancing binary search tree that maintains height balance through rotations to ensure efficient operations.</li>
  </ul>

  <h3>Graphs</h3>

  <p>
    Graphs represent relationships between entities through vertices and edges.
  </p>

  <ul>
    <li><strong>Graph:</strong> An abstraction for representing relationships and connections between vertices.</li>
    <li><strong>AdjacencyMatrix:</strong> A graph representation that uses a two-dimensional matrix to store connections between vertices.</li>
    <li><strong>AdjacencyList:</strong> A graph representation in which each vertex maintains a collection of its adjacent vertices.</li>
  </ul>
</div>

<hr />

<div align="justify">
  <h2>🔃 Implemented Sorting Algorithms</h2>

  <p>
    The project includes classic sorting algorithms that demonstrate different approaches to ordering elements.
  </p>

  <ul>
    <li><strong>Selection Sort:</strong> Repeatedly selects the smallest element from the unsorted portion and places it in its correct position.</li>
    <li><strong>Bubble Sort:</strong> Compares adjacent elements and swaps them whenever they are out of order, progressively sorting the collection.</li>
    <li><strong>Insertion Sort:</strong> Builds a sorted portion of the collection by inserting each element into its appropriate position.</li>
    <li><strong>Merge Sort:</strong> Uses a divide-and-conquer approach, recursively splitting the collection and merging sorted portions.</li>
    <li><strong>Quick Sort:</strong> Uses a pivot to partition the collection into smaller portions, recursively sorting each partition.</li>
  </ul>
</div>

<hr />

<div align="justify">
  <h2>🔍 Implemented Search Algorithms</h2>

  <p>
    Searching algorithms provide different approaches to locating elements within a collection.
  </p>

  <ul>
    <li><strong>Sequential Search:</strong> Iterates through the collection one element at a time until the target is found or the collection is exhausted.</li>
    <li><strong>Binary Search:</strong> Efficiently searches sorted collections by repeatedly dividing the search interval in half.</li>
  </ul>
</div>

<hr />

<div align="justify">
  <h2>🧪 Unit Tests & Code Coverage</h2>

  <p>
    The project uses <strong>JUnit 5</strong> to implement automated unit tests, validating the behavior and correctness of the data structures and supporting utilities.
  </p>

  <p>
    Test coverage is measured using <strong>JaCoCo</strong>, providing insights into which portions of the codebase are exercised during automated testing.
  </p>

  <h3>Generating the Coverage Report</h3>

  <p>
    To execute the tests and generate the JaCoCo coverage report, run the following command from the project root directory:
  </p>

<pre>
./mvnw clean verify
</pre>

  <p>
    On Windows, use:
  </p>

<pre>
mvnw.cmd clean verify
</pre>

  <p>
    After the build completes successfully, the generated HTML report can be found at:
  </p>

<pre>
target/site/jacoco/index.html
</pre>

  <p>
    Open the HTML file in your browser to explore detailed coverage metrics, including instruction, branch, and line coverage where applicable.
  </p>

  <h3>📊 Coverage Report</h3>

  <p>
    The following screenshot illustrates the project's code coverage report generated with JaCoCo.
  </p>

  <p>
    <img src="https://imgur.com/wvZ8wmi.png" alt="Data Structures JaCoCo coverage report" width="100%" />
  </p>
</div>

<hr />

<div align="justify">
  <h2>🛠️ Technologies Used</h2>

  <table>
    <thead>
      <tr>
        <th align="left">Technology</th>
        <th align="left">Purpose</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a href="https://www.java.com/">Java</a></td>
        <td>Programming language used to implement the data structures and algorithms.</td>
      </tr>
      <tr>
        <td><a href="https://maven.apache.org/">Apache Maven</a></td>
        <td>Project build automation and dependency management.</td>
      </tr>
      <tr>
        <td><a href="https://junit.org/junit5/">JUnit 5</a></td>
        <td>Automated unit testing and verification of implementation behavior.</td>
      </tr>
      <tr>
        <td><a href="https://www.jacoco.org/jacoco/">JaCoCo</a></td>
        <td>Code coverage analysis and generation of HTML coverage reports.</td>
      </tr>
      <tr>
        <td><a href="https://git-scm.com/">Git</a></td>
        <td>Source code versioning and repository management.</td>
      </tr>
    </tbody>
  </table>
</div>

<hr />

<div align="justify">
  <h2>📂 Project Structure</h2>

  <p>
    The project follows a modular Java structure, organizing data structures into dedicated packages according to their underlying concepts and implementation approaches.
  </p>

<pre>
data-structures/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── dev/guedes/datastructures/
│   │           ├── util/
│   │           │   ├── collection/
│   │           │   │   ├── graph/
│   │           │   │   ├── internal/
│   │           │   │   │   ├── exception/
│   │           │   │   │   ├── nodes/
│   │           │   │   │   └── validation/
│   │           │   │   ├── list/
│   │           │   │   ├── map/
│   │           │   │   ├── queue/
│   │           │   │   ├── set/
│   │           │   │   ├── stack/
│   │           │   │   └── tree/
│   │           │   ├── Arrays.java
│   │           │   └── Objects.java
│   │           ├── Application.java
│   │           └── DataStructuresApplication.java
│   └── test/
│       └── java/
│           └── dev/guedes/datastructures/
│               ├── util/
│               │   ├── collection/
│               │   │   ├── graph/
│               │   │   ├── internal/
│               │   │   │   └── validation/
│               │   │   ├── list/
│               │   │   ├── map/
│               │   │   ├── queue/
│               │   │   ├── set/
│               │   │   ├── stack/
│               │   │   └── tree/
│               │   ├── ArraysTest.java
│               │   └── ObjectsTest.java
│               └── DataStructuresApplicationTests.java
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── LICENSE
</pre>

  <p>
    The <code>collection</code> package contains the core data structure implementations, organized into specialized subpackages:
  </p>

  <ul>
    <li><strong>graph:</strong> Graph abstractions and their adjacency-based representations.</li>
    <li><strong>internal:</strong> Internal implementation components, including nodes, validation logic, and exceptions.</li>
    <li><strong>list:</strong> Array-based and linked list implementations.</li>
    <li><strong>map:</strong> Key-value collection abstractions and hash table implementations.</li>
    <li><strong>queue:</strong> Different queue implementations and their common abstraction.</li>
    <li><strong>set:</strong> Array-based, linked, and hash-based set implementations.</li>
    <li><strong>stack:</strong> Array-based and linked stack implementations.</li>
    <li><strong>tree:</strong> Binary trees, binary search trees, and AVL trees.</li>
  </ul>

  <p>
    The <code>util</code> package also contains array-related and object-related utility classes.
  </p>

  <p>
    The <code>test</code> source set mirrors the main package organization, keeping automated tests close to their corresponding implementations and making the codebase easier to maintain and extend.
  </p>

  <p>
    Maven Wrapper scripts allow the project to be built without requiring a separate Maven installation.
  </p>
</div>

<hr />

<div align="justify">
  <h2>🚀 Getting Started</h2>

  <p>
    You can explore the source code, build the project, and execute its automated tests locally.
  </p>

  <h3>Prerequisites</h3>

  <ul>
    <li>Java Development Kit (JDK) compatible with the project configuration.</li>
    <li>Git (to clone the repository).</li>
  </ul>

  <h3>Running from Source Code</h3>

  <h4>Steps</h4>

  <ol>
    <li>Clone the repository:</li>
  </ol>

<pre>
git clone &lt;repository-url&gt;
</pre>

  <ol start="2">
    <li>Navigate to the project directory:</li>
  </ol>

<pre>
cd data-structures
</pre>

  <ol start="3">
    <li>Build the project and execute the automated tests using Maven Wrapper.</li>
  </ol>

  <p><strong>Linux / macOS:</strong></p>

<pre>
./mvnw clean verify
</pre>

  <p><strong>Windows:</strong></p>

<pre>
mvnw.cmd clean verify
</pre>

  <p>
    Alternatively, you can import the project into a Java-compatible IDE, such as IntelliJ IDEA or Eclipse, and explore the implementations and their corresponding unit tests.
  </p>
</div>

<hr />

<div align="justify">
  <h2>📎 References & Resources</h2>

  <p>
    The following resources were used as references and learning materials throughout the development of this project.
  </p>

  <ul>
    <li><strong>Loiane Groner:</strong> <a href="https://www.youtube.com/playlist?list=PLGxZ4Rq3BOBrgumpzz-l8kFMw2DLERdxi" target="_blank" rel="noopener noreferrer">Java Data Structures and Algorithms Course</a>.</li>
    <li><strong>Programação Dinâmica:</strong> <a href="https://www.youtube.com/playlist?list=PL5TJqBvpXQv5Bb71AE5Cd_kB5rNsfU4Cp" target="_blank" rel="noopener noreferrer">Data Structures</a>.</li>
    <li><strong>Prof. Douglas Maioli:</strong> <a href="https://www.youtube.com/playlist?list=PLrOyM49ctTx_AMgNGQaic10qQJpTpXfn_" target="_blank" rel="noopener noreferrer">Data Structures</a>.</li>
    <li><strong><a href="https://www.bigocheatsheet.com/" target="_blank" rel="noopener noreferrer">Big-O Cheat Sheet</a>:</strong> A reference for understanding and comparing algorithmic time and space complexity.</li>
  </ul>
</div>

<hr />

<div align="justify">
  <h2>📄 License</h2>

  <p>
    This project is licensed under the <strong>MIT License</strong>.
  </p>

  <p>
    You are free to use, copy, modify, distribute, and share this project in accordance with the terms of the license.
  </p>

  <p>
    See the <a href="LICENSE">LICENSE</a> file for details.
  </p>
</div>