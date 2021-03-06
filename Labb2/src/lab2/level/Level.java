
package lab2.level;

import java.util.ArrayList;
import java.util.Observable;

public class Level extends Observable {

	ArrayList<Room> rooms = new ArrayList<>();
	Room currentRoom = null;
	boolean locationSet = false;

	public boolean place(Room r, int x, int y) {
		if (locationSet == true) {
			System.out.println("Spawn already set");
			return false;
		} else {
			r.x = x;
			r.y = y;

			for (Room room : rooms) {
				// l1 = TopLeft 1, r1 = BotRight 1
				// l2 = TopLeft 1, r2 = BotRight 2
				Point l1 = new Point(x, y);
				Point r1 = new Point(x + r.dx, y - r.dx);
				Point l2 = new Point(room.x, room.y);
				Point r2 = new Point(room.x + room.dx, room.y - room.dx);
				if (overlap(l1, r1, l2, r2)) {
					System.out.println("Room overlap");
					return false;
				}
			}
			rooms.add(r);
			System.out.println("Room placed");
			return true;
		}
	}

	private boolean overlap(Point l1, Point r1, Point l2, Point r2) {

		// if one rectangle is on left side of other
		if (l1.x >= r2.x || l2.x >= r1.x) {
			return false;
		}

		// if one rectangle is above other
		if (r1.y >= l2.y || r2.y >= l1.y) {
			return false;
		}

		return true;
	}

	 class Point {
		private int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public void firstLocation(Room r) {
		if (r != null) {
			currentRoom = r;
			locationSet = true;
		}
	}

	void moveNorth() {
		if (currentRoom.north != null) {
			currentRoom = currentRoom.north;
			setChanged();
			notifyObservers();
		}
	}

	void moveSouth() {
		if (currentRoom.south != null) {
			currentRoom = currentRoom.south;
			setChanged();
			notifyObservers();
		}
	}

	void moveEast() {
		if (currentRoom.east != null) {
			currentRoom = currentRoom.east;
			setChanged();
			notifyObservers();
		}
	}

	void moveWest() {
		if (currentRoom.west != null) {
			currentRoom = currentRoom.west;
			setChanged();
			notifyObservers();
		}

	}

}
