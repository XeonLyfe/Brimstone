package me.gavin.brimstone.events;

/**
 * 
 * @author Gav06
 * @since 4/20/2021
 * 
 * <p>
 * Example implementation of what an event class would look like
 *
 */
public class Event {

	/* Used for event timings (pre and post) */
	private final EventEra era;

	/* Used for cancellations (duh) */
	private boolean cancelled;

	/**
	 * Class constructor o_o
	 * 
	 * @param era When the event takes place
	 */
	public Event(EventEra era) {
		this.era = era;
	}

	/* check if event is cancelled */
	public boolean isCancelled() {
		return cancelled;
	}

	/* cancel the event */
	public void cancel() {
		cancelled = true;
	}

	/* un-cancel the event */
	public void restore() {
		cancelled = false;
	}

	/* get event era */
	public EventEra getEra() {
		return era;
	}
}
