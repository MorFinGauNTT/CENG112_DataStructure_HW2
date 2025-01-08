package CENG112_HW2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HotelReservationSystem {

    public static void main(String[] args) {
        // Create stacks for each room type
        ArrayStack<Room> singleRooms = new ArrayStack<>(5);
        ArrayStack<Room> doubleRooms = new ArrayStack<>(5);
        ArrayStack<Room> suiteRooms = new ArrayStack<>(5);
        ArrayStack<Room> deluxeRooms = new ArrayStack<>(5);

        // Define ArrayList instances for available and unavailable rooms
        ArrayList<Room> availableRooms = new ArrayList<>();
        ArrayList<Room> unavailableRooms = new ArrayList<>();

        // Create rooms and add them to their respective stacks
        int roomNumber = 20;

        for (int i = 19; i >= 0; i--) {
            String roomType;
            switch (i / 5) {
                case 0:
                    roomType = "Single";
                    singleRooms.push(new Room(roomNumber, roomType, true));
                    break;
                case 1:
                    roomType = "Double";
                    doubleRooms.push(new Room(roomNumber, roomType, true));
                    break;
                case 2:
                    roomType = "Suite";
                    suiteRooms.push(new Room(roomNumber, roomType, true));
                    break;
                case 3:
                    roomType = "Deluxe";
                    deluxeRooms.push(new Room(roomNumber, roomType, true));
                    break;
            }
            roomNumber--;
        }

        String filePath = "resources/reservations.txt"; // Update this path as necessary

        // Create waiting lines for each room type
        ArrayQueue<String> singleWaitingLine = new ArrayQueue<>(10);
        ArrayQueue<String> doubleWaitingLine = new ArrayQueue<>(10);
        ArrayQueue<String> suiteWaitingLine = new ArrayQueue<>(10);
        ArrayQueue<String> deluxeWaitingLine = new ArrayQueue<>(10);

        // Read reservations from file and populate waiting lines
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int reservationId = Integer.parseInt(data[0]);
                String customerName = data[1];
                String roomType = data[2].trim();

                switch (roomType) {
                    case "Single":
                        singleWaitingLine.enqueue(customerName);
                        break;
                    case "Double":
                        doubleWaitingLine.enqueue(customerName);
                        break;
                    case "Suite":
                        suiteWaitingLine.enqueue(customerName);
                        break;
                    case "Deluxe":
                        deluxeWaitingLine.enqueue(customerName);
                        break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Manage waiting reservations according to customer requests
        ArrayList<Room> bookedRooms = new ArrayList<>();
     

        // Print piles of room for each room type
        System.out.println("***BEFORE READING AND PROCESSING RESERVATION.TXT***");
        System.out.println("Single Rooms:");
        displayStack("Single Rooms", singleRooms);

        System.out.println("Double Rooms:");
        displayStack("Double Rooms", doubleRooms);

        System.out.println("Suite Rooms:");
        displayStack("Suite Rooms", suiteRooms);

        System.out.println("Deluxe Rooms:");
        displayStack("Deluxe Rooms", deluxeRooms);
        processWaitingLines(singleRooms, doubleRooms, suiteRooms, deluxeRooms,
                singleWaitingLine, doubleWaitingLine, suiteWaitingLine, deluxeWaitingLine,
                bookedRooms);

        // Print waiting lines for each room type
        System.out.println("***AFTER READING AND PROCESSING RESERVATION.TXT***");
        System.out.println("Single Waiting Line:");
        printQueue(singleWaitingLine);

        System.out.println("Double Waiting Line:");
        printQueue(doubleWaitingLine);

        System.out.println("Suite Waiting Line:");
        printQueue(suiteWaitingLine);

        System.out.println("Deluxe Waiting Line:");
        printQueue(deluxeWaitingLine);

        // Call the method to make odd-numbered rooms available
        makeOddNumberedRoomsAvailable( singleRooms, doubleRooms, suiteRooms,deluxeRooms, bookedRooms);

        // Now you can display the stacks with updated availability
        System.out.println("***AFTER MAKING ODD-NUMBERED ROOMS AVAILABLE***");
        displayStack("Single Rooms", singleRooms);
        displayStack("Double Rooms", doubleRooms);
        displayStack("Suite Rooms", suiteRooms);
        displayStack("Deluxe Rooms", deluxeRooms);

        // Process waiting lines again for newly available rooms
        processWaitingLines(singleRooms, doubleRooms, suiteRooms, deluxeRooms,
                singleWaitingLine, doubleWaitingLine, suiteWaitingLine, deluxeWaitingLine,
                bookedRooms);

        System.out.println("***SITUATION AFTER PROCESSING WAITING LINE FOR ALL BOOKED ODD NUMBERED ROOMS AVAILABLE***");
        System.out.println("Single Waiting Line:");
        printQueue(singleWaitingLine);

        System.out.println("Double Waiting Line:");
        printQueue(doubleWaitingLine);

        System.out.println("Suite Waiting Line:");
        printQueue(suiteWaitingLine);

        System.out.println("Deluxe Waiting Line:");
        printQueue(deluxeWaitingLine);
        
        // Obtain available room list
        while (!singleRooms.isEmpty()) {
            Room room = singleRooms.pop();
            if (room.isAvailability()) {
                availableRooms.add(room);
            } else {
                unavailableRooms.add(room);
            }
        }

        // Iterate through doubleRooms stack
        while (!doubleRooms.isEmpty()) {
            Room room = doubleRooms.pop();
            if (room.isAvailability()) {
                availableRooms.add(room);
            } else {
                unavailableRooms.add(room);
            }
        }

        // Iterate through suiteRooms stack
        while (!suiteRooms.isEmpty()) {
            Room room = suiteRooms.pop();
            if (room.isAvailability()) {
                availableRooms.add(room);
            } else {
                unavailableRooms.add(room);
            }
        }

        // Iterate through deluxeRooms stack
        while (!deluxeRooms.isEmpty()) {
            Room room = deluxeRooms.pop();
            if (room.isAvailability()) {
                availableRooms.add(room);
            } else {
                unavailableRooms.add(room);
            }
        }
        // Obtain unavailable rooms list 
        int i = 1;
        ArrayList<Room> unavailableRooms2 = new ArrayList<>();

        while(i<bookedRooms.getLength()) {
        	i++;
        	if(!availableRooms.contains(bookedRooms.getEntry(i)) && !unavailableRooms2.contains(bookedRooms.getEntry(i))) {
        		unavailableRooms2.add(bookedRooms.getEntry(i));
        	}
        }

        System.out.println("***AVAILABLE ROOMS and UNAVAILABLE ROOMS***");
        // Display available and unavailable rooms
        displayRooms("Available Rooms", availableRooms);
        displayRooms("Unavailable Rooms", unavailableRooms2);
    }

    private static void processWaitingLine(ArrayStack<Room> roomStack, ArrayQueue<String> waitingLine,
                                           ArrayList<Room> bookedRooms) {
        ArrayStack<Room> tempRoomStack = new ArrayStack<>(5);
        ArrayQueue<String> tempWaitingLine = new ArrayQueue<>(10);

        // Process waiting line reservations
        while (!waitingLine.isEmpty()) {
            if (roomStack.isEmpty()) {
                break; // No more rooms available to book
            }

            Room room = roomStack.pop(); // Remove the room from the stack
            String customer = waitingLine.dequeue(); // Get the first customer in the waiting line

            // Check if the room is available
            if (room.isAvailability()) {
                room.setAvailability(false); // Book the room
                bookedRooms.add(room); // Add the room to the booked rooms list
                // Hold booked rooms in a temporary stack
            } else {
                tempWaitingLine.enqueue(customer); // Add the customer back to the waiting line
                tempRoomStack.push(room); // Add the room back to the temp stack
            }
        }

        // Restore the remaining rooms back to the room stack
        while (!tempRoomStack.isEmpty()) {
            roomStack.push(tempRoomStack.pop());
        }

        // Update the waiting line with the correct order of customers
        while (!tempWaitingLine.isEmpty()) {
            waitingLine.enqueue(tempWaitingLine.dequeue());
        }
    }

    private static void processWaitingLines(ArrayStack<Room> singleRooms, ArrayStack<Room> doubleRooms,
                                            ArrayStack<Room> suiteRooms, ArrayStack<Room> deluxeRooms,
                                            ArrayQueue<String> singleWaitingLine,
                                            ArrayQueue<String> doubleWaitingLine,
                                            ArrayQueue<String> suiteWaitingLine,
                                            ArrayQueue<String> deluxeWaitingLine,
                                            ArrayList<Room> bookedRooms) {
        // Process waiting lines and replace customers into available rooms
        processWaitingLine(singleRooms, singleWaitingLine, bookedRooms);
        processWaitingLine(doubleRooms, doubleWaitingLine, bookedRooms);
        processWaitingLine(suiteRooms, suiteWaitingLine, bookedRooms);
        processWaitingLine(deluxeRooms, deluxeWaitingLine, bookedRooms);
    }

    private static void printQueue(ArrayQueue<String> queue) {
        ArrayQueue<String> tempQueue = new ArrayQueue<>(queue.size());

        // Copy elements from the original queue to a temporary queue
        while (!queue.isEmpty()) {
            String element = queue.dequeue();
            System.out.println(element);
            tempQueue.enqueue(element);
        }

        // Restore the elements back to the original queue
        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }

        System.out.println("----------------------------");
    }

    private static void displayStack(String stackName, ArrayStack<Room> stack) {
        System.out.println("Pile: " + stackName);
        System.out.println("Top of Pile -> Bottom of Pile");

        ArrayStack<Room> tempStack = new ArrayStack<>(5);

        // Transfer rooms from original stack to temporary stack without changing availability
        while (!stack.isEmpty()) {
            Room room = stack.pop();
            String availability = room.isAvailability() ? "Available" : "Unavailable"; // Control room availability
            System.out.println(room.getRoomType() + " Room " + room.getRoomNumber() + " - Availability: " + availability);
            tempStack.push(room); // Push the room back to the temporary stack for restoration
        }

        // Restore the rooms back to the original stack from the temporary stack
       

        // Print the rooms in the original order with updated availability
        while (!stack.isEmpty()) {
            Room room = stack.pop();
            System.out.println(room.getRoomType() + " Room " + room.getRoomNumber() + " - Availability: " + room.isAvailability());
            tempStack.push(room); // Push the room back to the temporary stack for restoration
        }

        // Restore the rooms back to the original stack from the temporary stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop()); // Transfer rooms back to the original stack
        }

        System.out.println("----------------------------");
    }

    private static void makeOddNumberedRoomsAvailable(ArrayStack<Room> singleRooms, ArrayStack<Room> doubleRooms,
            ArrayStack<Room> suiteRooms, ArrayStack<Room> deluxeRooms,
            ArrayList<Room> bookedRooms) {
    	// Create a temporary list to hold rooms that are not being made available
    	ArrayList<Room> tempBookedRooms = new ArrayList<>();

    	for (int i = bookedRooms.getLength(); i > 0; i--) {
    		Room room = bookedRooms.getEntry(i ); // Get the room (note: ArrayList is 1-based index in your implementation)
    		if (room.getRoomNumber() % 2 != 0) { // Check if room number is odd
    			room.setAvailability(true); // Set availability to true for odd-numbered rooms
    			getRoomStack(room.getRoomType(), singleRooms, doubleRooms, suiteRooms, deluxeRooms).push(room);
    		} else {
    			tempBookedRooms.add(room); // Keep the room in the booked list if it's not an odd-numbered room
    		}
    	}

    	// Clear the bookedRooms list and add back only those that are still booked
    	/*bookedRooms.clear();
    	for (int i = 0; i < tempBookedRooms.getLength(); i++) {
    		bookedRooms.add(tempBookedRooms.getEntry(i + 1));*/
    	}
    

private static ArrayStack<Room> getRoomStack(String roomType, ArrayStack<Room> singleRooms, ArrayStack<Room> doubleRooms,
       ArrayStack<Room> suiteRooms, ArrayStack<Room> deluxeRooms) {
	switch (roomType) {
	case "Single":
		return singleRooms;
	case "Double":
		return doubleRooms;
	case "Suite":
		return suiteRooms;
	case "Deluxe":
		return deluxeRooms;
	default:
		throw new IllegalArgumentException("Invalid room type: " + roomType);
}
}

    // Method to display rooms in a list
    private static void displayRooms(String listName, ArrayList<Room> rooms) {
        System.out.println(listName + ":");
        if (rooms != null && !rooms.isEmpty()) {
            int index = 1; // Start index at 1
            while (index <= rooms.getLength()) { // Use getLength() instead of size()
                Room room = rooms.getEntry(index); // Use getEntry(index) instead of get(index)
                System.out.println(room.getRoomType() + " Room " + room.getRoomNumber());
                index++;
            }
            System.out.println("----------------------------");
        } else {
            System.out.println("No rooms to display.");
        }
    }
}
// G-04 300201015 Osman Altunbağ - 300201023 Zübeyr Almaho - 300201093 Yunus Emre Sapan
