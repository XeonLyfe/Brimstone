# Brimstone
Simple and fast event system using reflections

Has support for event priorities as seen:

```java
@Listener(priority = 100)
public void onEvent(Object o) {
	// do stuff
}
```

# Usage

```java
public class Test {

	public static void main(String[] args) {
		new Test();
	}
	
	EventBus eventSys;
	
	public Test() {
		eventSys = new EventBus();
		
		eventSys.register(this);
		
		eventSys.dispatch("Hello world!");
	}
	
	@Listener
	public void testListener(String str) {
		System.out.println(str);
	}
}
```
Output (results may vary):
```
Hello world!
```

# Adding to build

Add to repositories
```gradle
repositories {
	maven { url 'https://beriidevelopment.github.io/mvn/' }
}
```

Add as a dependency
```gradle
dependencies {
	implementation 'me.gavin:Brimstone:1.2.1'
}
```
