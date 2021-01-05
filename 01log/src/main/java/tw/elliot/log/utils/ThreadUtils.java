package tw.elliot.log.utils;

import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.ProfilerRegistry;
import org.springframework.util.StopWatch;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class ThreadUtils {
	private static ThreadLocal<StopWatch> THREAD_STOP_WATCH = new ThreadLocal<>();

	private static ThreadLocal<Profiler> THREAD_PROFILER = new ThreadLocal<>();
	private static ThreadLocal<AtomicInteger> THREAD_AI = new ThreadLocal<>();
	public static Profiler getProfiler() {

		Profiler profiler = THREAD_PROFILER.get();
		return profiler;
	}

	public static void initProfiler(String id) {
		THREAD_AI.set(new AtomicInteger(0));

		Profiler profiler = new Profiler(id);


		THREAD_PROFILER.set(profiler);
		profiler.start(THREAD_AI.get().getAndIncrement() +" - "+ id);
	}

	public static void finishProfiler() {
		Profiler profiler = THREAD_PROFILER.get();

		if (null != profiler) {
			profiler.stop();
		}
	}

	public static void startProfiler(String name) {
		Profiler profiler = getProfiler();
		profiler.start(THREAD_AI.get().getAndIncrement() +" - " + name);
	}

	public static void stopProfiler() {
		Profiler profiler = getProfiler();
		profiler.stop();
	}

	public static void removeSProfiler() {
		THREAD_PROFILER.set(null);
		THREAD_AI.set(null);
	}

	public static StopWatch getStopWatch() {

		StopWatch stopWatch = THREAD_STOP_WATCH.get();
		if (null == stopWatch) {
			stopWatch = new StopWatch();
			THREAD_STOP_WATCH.set(stopWatch);
		}

		return stopWatch;
	}

	public static void removeStopWatch() {
		if (null != THREAD_STOP_WATCH.get()) {
			THREAD_STOP_WATCH.set(null);
		}
	}

	public static void initWatch(String id) {
		StopWatch stopWatch = new StopWatch(id);
		THREAD_STOP_WATCH.set(stopWatch);
		stopWatch.start(id);
	}

	public static void finishWatch() {
		StopWatch stopWatch = THREAD_STOP_WATCH.get();

		if (null != stopWatch && stopWatch.isRunning()) {
			stopWatch.stop();
		}
	}
	public static void startWatch(String name) {
		StopWatch stopWatch = getStopWatch();

		if (stopWatch.isRunning()) {
			stopWatch.stop();
		}

		stopWatch.start(name);
	}

	public static void stopWatch() {
		StopWatch stopWatch = getStopWatch();
		if (stopWatch.isRunning()) {
			stopWatch.stop();
		}
	}


}
