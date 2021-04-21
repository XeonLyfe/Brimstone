# Brimstone
Simple and fast event system using reflections

last event system that i'm making (I promise)

Has support for event priorities as seen:

```java
@Listener(priority = 100)
public void onEvent(Object o) {
	// do stuff
}
```

# Usage / Speed

This isn't a very great test, but the output of this can be seen below:

```java
public class Test {

	public static void main(String[] args) {
		new Test();
	}
	
	EventBus eventSys;
	
	int count = 0;
	
	public Test() {
		eventSys = new EventBus();
		
		eventSys.register(this);
		
		long runningTotal = 0L;
		
		long lowestTime = Long.MAX_VALUE;
		long highestTime = Long.MIN_VALUE;
		
		for (int i = 0; i < 100; i++) {
			long fireTime = getEventFireTime();
			if (fireTime < lowestTime)
				lowestTime = fireTime;
			else if (fireTime > highestTime)
				highestTime = fireTime;
			
			runningTotal += fireTime;
		}
		
		double average = runningTotal / 100;
		System.out.println("shortest time: " + lowestTime + "ms longest time: " + highestTime + "ms");
		System.out.println("average time elapsed per " + (count / 100) + " events: " + average + "ms");
		System.out.println("total time elapsed over " + count + " events: " + runningTotal + "ms");
	}
	
	@Listener
	public void testListener(Object event) {
	}
	
	public long getEventFireTime() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			eventSys.dispatch(new Object());
			count++;
		}
		
		return System.currentTimeMillis() - startTime;
	}
}
```
Output (results may vary):
```
shortest time: 0ms longest time: 8ms
average time elapsed per 10000 events: 1.0ms
total time elapsed over 1000000 events: 130ms
```

# Adding to build

Add to repositories
```gradle
repositories {
	maven { url 'https://jitpack.io' }
}
```

Add as a dependency
```gradle
dependencies {
	implementation 'com.github.Gav06:Brimstone:1.1'
}
