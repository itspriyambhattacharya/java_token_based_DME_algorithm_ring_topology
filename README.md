# Token-Based Mutual Exclusion in Ring Topology

## Overview

This project demonstrates the implementation of a **Token-Based Mutual Exclusion** algorithm in a **Ring Topology**, developed using Java threads. The core objective is to ensure mutual exclusion in a distributed system simulation, where multiple processes are organized in a logical ring, and only the token-holding process is allowed to enter the **Critical Section (CS)**.

The mutual exclusion mechanism is crucial in concurrent systems where multiple processes require exclusive access to shared resources.

---

## Features

- Each process is represented as an individual thread (`Process` class).
- Logical ring formation with `n` processes.
- Only the process holding the token can enter its Critical Section.
- Queue-based request handling to maintain order and fairness.
- Token passing after CS execution or when not needed.
- Simulated critical section using sleep delay to represent process execution.

---

## Classes

### 1. `Process` Class

- Represents a process in the ring.
- Maintains process ID, token possession, queue of requesters, and reference to the next process.
- Key methods:
  - `requestToken(int requesterId)`: Initiates a request to enter CS.
  - `enterCriticalSection()`: Simulates CS execution.
  - `exitCriticalSection()`: Handles token passing post CS execution.
  - `checkTokenAndEnterCS()`: Decides whether to enter CS or pass token.

### 2. `Main` Class

- Takes input from the user:
  - Number of processes.
  - Process IDs.
- Forms the ring topology.
- Assigns the token to a random process.
- Starts each process (thread) in the ring.

---

## How It Works

1. **Initialization**:

   - User provides number of processes and their IDs.
   - Processes are linked in a ring: `p1 → p2 → ... → pn → p1`.
   - One process is randomly assigned the token.

2. **Execution**:

   - Each process starts and tries to request the token.
   - The token is passed along the ring until the requester receives it.
   - The requester enters the Critical Section and then releases the token.
   - Token is passed to the next process, and queued requests are forwarded.

3. **Queue Handling**:
   - Each process maintains a queue of requesters.
   - Ensures **fair** access to the CS based on request arrival order.

---

## Sample Output (Simulation)

```txt
Enter number of processes: 5
Enter Process ID 1: 101
Enter Process ID 2: 102
Enter Process ID 3: 103
Enter Process ID 4: 104
Enter Process ID 5: 105

Process 101 requesting token...
Process 103 requesting token...
Process 104 requesting token...
Process 102 requesting token...
Process 105 requesting token...
Process 104 has token. Processing request from 101
Process 104 is ENTERING critical section...
Process 104 is EXITING critical section.
Process 105 has token. Processing request from 103
Process 105 is ENTERING critical section...
...
```

---

## Compilation & Execution

### Prerequisites

- Java JDK 8 or higher
- Command-line terminal (or any Java IDE)

### Steps

1. Save the files as `Process.java` and `Main.java`.

2. Compile:

   ```bash
   javac Main.java
   ```

3. Run:

   ```bash
   java Main
   ```

---

## Code Structure

```bash
project-root/
├── Main.java           # Main driver class to initiate the ring and start processes
└── Process.java        # Process class with token passing and CS management logic
```

---

## Use Cases

- Distributed systems simulation in academic settings.
- Demonstrating mutual exclusion algorithms using multithreading.
- Teaching concurrent programming principles.

---

## Limitations

- Processes are simulated on a single machine using threads (not true distributed nodes).
- Random delays are used for CS simulation, not actual computation.

---

## Future Enhancements

- Visual representation of the ring and token passing.
- Incorporate process failure handling and recovery.
- Extend to real distributed environments using sockets or RPC.

---

## 🛠 Contributing Guidelines

If you would like to contribute or experiment with this repository, please **do not push changes directly to the `main` branch**.

Instead, follow the proper GitHub workflow using **branches** and **pull requests**.

### ✅ Steps to Contribute

#### 1. Fork the Repository

Start by forking this repository to your own GitHub account.

- Click on the **Fork** button at the top-right corner of the [repository page](https://github.com/itspriyambhattacharya/MSc-SEM-2-ADM-Assignment-2.git).
- This will create a copy under your GitHub profile.

#### 2. Clone the Forked Repository

Once forked, clone it to your local machine:

```bash
git clone https://github.com/itspriyambhattacharya/MSc-SEM-2-ADM-Assignment-2.git
cd MSc-SEM-2-ADM-Assignment-2
```

#### 3. Create a New Branch

Always create a new branch to make changes. Avoid working directly on main:

```bash
git checkout -b your-feature-name
```

#### 4. Make Your Changes

Edit files, add new content, or update existing logic as needed.

#### 5. Stage and Commit Your Changes

```bash
git add .
git commit -m "Describe your changes here"
```

#### 6. Push to Your Fork

```bash
git push origin your-feature-name
```

#### 7. Create a Pull Request

- Go to your fork on GitHub.
- Click the "Compare & pull request" button.
- Provide a meaningful title and description of your changes.
- Submit the pull request.

The repository maintainer will review your changes and merge them if appropriate.

---

## 🔐 Branch Protection

The main branch is protected. Any updates must be reviewed through pull requests.

---

## ❓ Need Help?

If you face any issues while contributing or have questions regarding Git operations, feel free to raise an issue in this repository.

---

## Author

**Priyam Bhattacharya**  
_M.Sc. Computer Science, University of Calcutta_  
_GitHub: [itspriyambhattacharya](https://github.com/itspriyambhattacharya)_

---

## License

This project is released under the MIT License.
