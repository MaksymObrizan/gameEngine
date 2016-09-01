package intelligence;

import java.util.Random;

public interface StateEventListener {
	void onEventIdle(Random rand);
	void onEventWalk();
	void onEventTurn();
}
