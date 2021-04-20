package me.gavin.brimstone;

import java.lang.reflect.Method;

/**
 * 
 * @author Gav06
 * <p>
 * Small class to store variables relating to the event bus
 */
public class ListenerData {

	/**
	 * <p>
	 * annotation (used for sorting by event priority)
	 */
	private final Listener annotation;

	/**
	 * <p>
	 * method object (used for invoking)
	 */
	private final Method method;

	/**
	 * <p>
	 * object representing the event that the method is listening for
	 */
	private final Class<?> event;

	/**
	 * <p>
	 * parent object of listener
	 */
	private final Object parent;

	public ListenerData(Method method, Class<?> event, Object parent) {
		this.method = method;
		this.annotation = method.getAnnotation(Listener.class);
		this.event = event;
		this.parent = parent;
	}
	
	/**
	 * Used for getting the annotation (for handling priorities)
	 * @return Annotation used in the event listener
	 */
	public Listener getAnnotation() {
		return annotation;
	}

	/**
	 * Used for invoking the method and getting various attributes
	 * @return Event listener method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * Used for checking if method should be invoked
	 * @return Event paramater in listener method
	 */
	public Class<?> getEvent() {
		return event;
	}

	/**
	 * Used for invoking the method, as an object is needed since it is non-static
	 * @return Parent object
	 */
	public Object getParent() {
		return parent;
	}

	/**
	 * toString method for debugging
	 */
	@Override
	public String toString() {
		return "method: " + method.getName() + " event: " + event.getName() + " parent: "
				+ parent.getClass().getName();
	}
}
