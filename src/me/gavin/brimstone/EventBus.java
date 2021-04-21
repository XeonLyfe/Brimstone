package me.gavin.brimstone;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/**
 * 
 * @author Gav06
 * @since 4/20/2021
 *
 *        <p>
 *        Main event system class to handle everything
 * 
 */

@SuppressWarnings("deprecation")
public class EventBus {

	/**
	 * List of listeners used by the event bus
	 */
	private final HashSet<ListenerData> listenerMap = new HashSet<>();

	/**
	 * Value deciding if actions are printed or not
	 */
	private boolean logging = false;

	/**
	 * Register a class to receive events
	 * 
	 * @param listenerObject class to register to listen for events
	 * 
	 */
	public void register(final Object listenerObject) {
		for (final Method method : listenerObject.getClass().getDeclaredMethods()) {
			if (!method.isAccessible()) {
				method.setAccessible(true);
			}

			if (method.isAnnotationPresent(Listener.class)) {
				if (method.getParameters().length > 0) {
					ListenerData data = new ListenerData(method, method.getParameters()[0].getType(), listenerObject);
					listenerMap.add(data);
					if (logging) {
						System.out.println("added data - " + data);
					}
				}
			}
		}
	}

	/**
	 * Unregister a registered class from receiving events
	 * 
	 * @param listenerObject class to from event bus
	 */
	public void unregister(final Object listenerObject) {
		for (ListenerData obj : listenerMap) {
			// Have be careful here with ConcurrentModificationExceptions
			if (obj.getParent() == listenerObject.getClass()) {
				listenerMap.remove(obj);
				if (logging) {
					System.out.println("removed data - " + obj);
				}
				return;
			}
		}
	}

	/**
	 * Invoke listeners
	 * 
	 * @param eventObject calls listeners with this event object specified
	 */
	public void dispatch(final Object eventObject) {
		// temporary list to store data to be handled
		final ArrayList<ListenerData> tempDataList = new ArrayList<>();

		// finding methods to invoke to put in temp list
		for (final ListenerData data : listenerMap) {
			if (data.getEvent() == eventObject.getClass()) {
				tempDataList.add(data);
			}
		}

		// sorting by priority
		tempDataList.sort(Comparator.comparing(data -> -data.getAnnotation().priority()));

		// invoking the methods
		for (final ListenerData data : tempDataList) {
			try {
				data.getMethod().invoke(data.getParent(), eventObject);
				if (logging) {
					System.out.println("called invoked event - " + data);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Set if there is debug logging or not
	 * 
	 * @param flag
	 */
	public void setDebugLogging(final boolean flag) {
		logging = flag;
	}
	
	/**
	 * 
	 * Clears all registered objects from the map
	 * 
	 */
	public void clearMap() {
		listenerMap.clear();
	}

}
