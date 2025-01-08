# CENG112_DataStructure_HW2
Coding homework 2 of CENG112 Course at Izmir Institute of Technology Spring 24'

# Hotel Reservation System
This Java application implements a hotel reservation system that allows users to reserve rooms based on room type (Single, Double, Suite, Deluxe). The application simulates a reservation system where rooms can be booked, managed, and processed using various data structures such as Stacks, Queues, Lists, and Generics.

# Features
Room Management:
Room Types: Single, Double, Suite, Deluxe
Room Availability: Each room is marked as available or booked.
Reservation Management:
Reservation Information: Each reservation contains a Reservation ID, Customer Name, and Room Type.
Reservation Queue: For each room type, a waiting line is maintained. If a room is not available, the reservation is added to the waiting line.
Room Booking Process:
Initial Room Setup:

Create 5 rooms for each room type (Single, Double, Suite, Deluxe).
Each room has a unique room number, type, and availability status (Initially available).
Waiting Line Management:

Reservations are read from a reservations.txt file and added to the waiting line if the room is not available.
Room Reservation Process:

Book rooms based on availability and manage waiting lines when rooms become available.
Post-Processing:

After booking all reservations, odd-numbered rooms are marked as available again, and waiting line reservations are processed accordingly.
Final Room Status:

Manage lists of available and unavailable rooms after all reservations are processed.
# Data Structures Used
Stacks: Used to manage room piles for each room type, with rooms having the smallest ID at the top.
Queues: Used to manage waiting lines for reservations of each room type.
Lists: Used to store final lists of booked and available rooms.
Generics: Used to create flexible and reusable methods for managing rooms and reservations.
# Operations
Step 1: Initialize rooms for each type with availability status.
Step 2: Create piles for each room type and manage room allocation based on availability.
Step 3: Read and process reservations from reservations.txt.
Step 4: Re-assign rooms to reservations in the waiting line when odd-numbered rooms become available.
Step 5: Display the final room status after processing all reservations.
# Output
Room Piles: Display four room piles (Single, Double, Suite, Deluxe) before and after processing reservations.
Waiting Lines: Show four reservation queues (Single, Double, Suite, Deluxe) before and after processing waiting line reservations.
Room Status: Display two lists of rooms: Unavailable Rooms and Available Rooms after all reservations are processed.
