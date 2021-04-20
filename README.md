# Brimstone
Simple and fast event system using reflections

last event system that i'm making (I promise)

# Usage / Speed

(it can run this test in around 80-120 ms)
Probably not a great test, but still pretty good speed.

```java
public class Test {

	public static void main(String[] args) {
		new Test();
	}
	
	EventBus eventSys;
	
	public Test() {
		System.out.println("Creating event bus");
		eventSys = new EventBus();
		
		System.out.println("registering test class");
		eventSys.register(this);
		
		
		System.out.println("running tests");
		long runningTotal = 0L;
		
		for (int i = 0; i < 100; i++) {
			runningTotal += getEventFireTime();
		}
		
		System.out.println("time elapsed: " + runningTotal + "ms");
	}
	
	@Listener
	public void testListener(Object o) {
	}
	
	public long getEventFireTime() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			eventSys.dispatch(new Object());
		}
		
		return System.currentTimeMillis() - startTime;
	}
}
```

# Adding to build
