package intelligence;

import java.util.Random;

import org.lwjgl.util.vector.Vector2f;

import terrain.Terrain;

public enum State implements StateEventListener{

	idleState{
		@Override
		public void onEventIdle(Random rand) {
			System.out.print("\nState: idle");
			float x = rand.nextFloat() * Terrain.SIZE/4 *-1;
			float z = rand.nextFloat() * Terrain.SIZE/4 *-1;
//			target = new Vector2f(x,z);
//		
//			state = CubeState.turnToTarget;
		}

		@Override
		public void onEventWalk() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onEventTurn() {
			// TODO Auto-generated method stub
			
		}
	},
	runState{
		@Override
		public void onEventIdle(Random rand) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onEventWalk() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onEventTurn() {
			// TODO Auto-generated method stub
			
		}
	},
	
	turnState{
		@Override
		public void onEventIdle(Random rand) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onEventWalk() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onEventTurn() {
			// TODO Auto-generated method stub
			
		}
	}
	
	
}
